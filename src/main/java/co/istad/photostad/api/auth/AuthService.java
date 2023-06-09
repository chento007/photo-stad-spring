package co.istad.photostad.api.auth;

import co.istad.photostad.api.auth.web.AuthDto;
import co.istad.photostad.api.auth.web.LoginDto;
import co.istad.photostad.api.auth.web.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    void verify(String email);
    void checkVerify(String email,String verifiedCode);
    AuthDto login(LoginDto loginDto);
}
