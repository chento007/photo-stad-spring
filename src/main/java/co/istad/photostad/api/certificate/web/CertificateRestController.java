package co.istad.photostad.api.certificate.web;

import co.istad.photostad.api.certificate.CertificateService;
import co.istad.photostad.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
public class CertificateRestController {
    private final CertificateService certificateService;
    @PostMapping
    BaseRest<?> insertCertificate(@RequestBody CreateCertificateDto createCertificateDto) {
        CertificateDto certificateDto= certificateService.insertCertificate(createCertificateDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been save success")
                .timestamp(LocalDateTime.now())
                .data(certificateDto)
                .build();
    }
    @GetMapping("/{id}")
    BaseRest<?> selectCertificateById(@PathVariable Integer id){
        CertificateDto certificateDto=certificateService.selectCertificateById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been found")
                .timestamp(LocalDateTime.now())
                .data(certificateDto)
                .build();
    }
}
