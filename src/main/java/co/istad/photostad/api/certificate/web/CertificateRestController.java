package co.istad.photostad.api.certificate.web;

import co.istad.photostad.api.certificate.CertificateService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
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
    public BaseRest<?> insertCertificate(@Valid @RequestBody CreateCertificateDto createCertificateDto) {
        CertificateDto certificateDto = certificateService.insertCertificate(createCertificateDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been save success")
                .timestamp(LocalDateTime.now())
                .data(certificateDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectCertificateById(@PathVariable Integer id) {
        CertificateDto certificateDto = certificateService.selectCertificateById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been found")
                .timestamp(LocalDateTime.now())
                .data(certificateDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteCertificateById(@PathVariable("id") Integer id) {
        Integer deletedId = certificateService.deleteCertificateById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been delete success")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllCertificate(@RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                          @RequestParam(required = false, defaultValue = "20", name = "limit") int limit) {
        PageInfo<CertificateDto> resultSearch = certificateService.selectAll(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been delete success")
                .timestamp(LocalDateTime.now())
                .data(resultSearch)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> updateCertificate(@PathVariable("id") Integer id, @Valid @RequestBody CreateCertificateDto createCertificateDto){
        CertificateDto result=certificateService.updateCertificate(id,createCertificateDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been update success")
                .timestamp(LocalDateTime.now())
                .data(result)
                .build();
    }
}
