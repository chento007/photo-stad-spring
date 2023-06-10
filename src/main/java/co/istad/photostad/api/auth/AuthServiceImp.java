package co.istad.photostad.api.auth;

import co.istad.photostad.api.auth.web.AuthDto;
import co.istad.photostad.api.auth.web.LoginDto;
import co.istad.photostad.api.auth.web.RegisterDto;
import co.istad.photostad.api.user.User;
import co.istad.photostad.util.FormatUtil;
import co.istad.photostad.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final AuthMapper authMapper;
    private final AuthMapStruct authMapStruct;
    private final PasswordEncoder encoder;
    private final MailUtil mailUtil;
    private final DaoAuthenticationProvider authenticationProvider;
    @Value("${spring.mail.username}")
    private String appEmail;
    private final JwtEncoder jwtEncoder;
    private final FormatUtil formatUtil;

    @Transactional
    @Override
    public String register(RegisterDto registerDto) {
        User user = authMapStruct.registerDtoToUser(registerDto);
        user.setIsVerified(false);
        user.setPassword(encoder.encode(registerDto.password()));
        user.setUuid(UUID.randomUUID().toString());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (authMapper.register(user)) {
            for (Integer role : registerDto.roleIds()) { // create user roles
                if (authMapper.createUserRole(user.getId(), role)) {
                    return registerDto.email();
                }
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "create user has been fail"
                );
            }
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "register has been fail"
        );
    }

    @Override
    public void verify(String email) {
        User user = authMapper.findEmail(email).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("email with %s is not found !", email)
                )
        );
        String verifiedCode = UUID.randomUUID().toString();
        if (authMapper.updateVerifiedCode(email, verifiedCode)) {
            user.setVerifiedCode(verifiedCode);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "User can not be verified !!"
            );
        }

        MailUtil.Meta<?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(appEmail)
                .subject("Account Verification")
                .templateUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.send(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "mail is been failed to send"
            );
        }
    }

    @Override
    public void checkVerify(String email, String verifiedCode) {
        User user = authMapper.selectByEmailAndVerifiedCode(email, verifiedCode).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email Search not found")
        );
        System.out.println(user);
        if (!user.getIsVerified()) {
            authMapper.verify(email, verifiedCode);
        }
    }

    @Override
    public AuthDto login(LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        authentication = authenticationProvider.authenticate(authentication);
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> !auth.startsWith("ROLE_"))
                .collect(Collectors.joining(" "));
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
        //update login in at
        User user = authMapper.selectUserByEmailToUpdateLoginAt(loginDto.email()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with email %s is not found", loginDto.email())
                )
        );
        user.setLoggedInAt(new Timestamp(System.currentTimeMillis()));
        authMapper.updateLoginAt(user);
        return new AuthDto(accessToken);
    }
}
