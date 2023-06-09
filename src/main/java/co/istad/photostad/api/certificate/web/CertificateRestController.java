package co.istad.photostad.api.certificate.web;

import co.istad.photostad.api.certificate.CertificateService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
public class CertificateRestController {
    private final CertificateService certificateService;
    @PostMapping("/insert")
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

    @DeleteMapping("/{id}")
    BaseRest<?> deleteCertificateById(@PathVariable Integer id){
        Integer byId=certificateService.deleteCertificateById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been deleted success")
                .timestamp(LocalDateTime.now())
                .data(byId)
                .build();
    }

    @PutMapping("/{id}")
    BaseRest<?> updateStatusCertificatesById(@PathVariable Integer id,@RequestBody IsDeletedDto dto){
        Integer byId = certificateService.updateStatusCertificatesById(id, dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificate has been update status success")
                .timestamp(LocalDateTime.now())
                .data(byId)
                .build();
    }
//    @GetMapping
//    BaseRest<?> findAll(){
//        var getAll = certificateService.findAll();
//        return BaseRest.builder()
//                .status(true)
//                .code(HttpStatus.OK.value())
//                .message("Certificate has been find all success")
//                .timestamp(LocalDateTime.now())
//                .data(getAll)
//                .build();
//    }

    @GetMapping
    public BaseRest<?> findAll(@RequestParam(name = "page",required = false,defaultValue = "1")int page,
                                    @RequestParam(name = "limit",required = false,defaultValue = "20")int limit){
        PageInfo<CertificateDto> certificateDtoPageInfo = certificateService.findAll(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Certificates has been find successfully")
                .timestamp(LocalDateTime.now())
                .data(certificateDtoPageInfo)
                .build();
    }
}
