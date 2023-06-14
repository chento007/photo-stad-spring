package co.istad.photostad.api.certificatedownload.web;

import co.istad.photostad.api.certificatedownload.CertificateDownload;
import co.istad.photostad.api.certificatedownload.CertificateDownloadService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/certificate-downloads")
public class CertificateDownloadRestController {
    private final CertificateDownloadService certificateDownloadService;

    @PostMapping
    public BaseRest<?> insertCertificateDownload(@Valid @RequestBody CreateCertificateDownloadDto createCertificateDownloadDto) {
        CertificateDownloadDto certificateDownloadDto = certificateDownloadService.insertCertificateDownload(createCertificateDownloadDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("CertificateDownload has been insert success")
                .timestamp(LocalDateTime.now())
                .data(certificateDownloadDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectCertificateDownloadById(@PathVariable("id") Integer id) {
        CertificateDownloadDto certificateDownloadDto = certificateDownloadService.selectCertificateDownloadById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("CertificateDownload has been found success")
                .timestamp(LocalDateTime.now())
                .data(certificateDownloadDto)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateCertificateDownloadById(@PathVariable("id") Integer id, @Valid @RequestBody CreateCertificateDownloadDto createCertificateDownloadDto) {
        CertificateDownloadDto certificateDownloadDto = certificateDownloadService.updateCertificateDownloadById(id, createCertificateDownloadDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("CertificateDownload has been update success")
                .timestamp(LocalDateTime.now())
                .data(certificateDownloadDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> selectAllCertificateDownload(@RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                                    @RequestParam(required = false, defaultValue = "20", name = "limit") int limit) {
        PageInfo<CertificateDownloadDto> certificateDownloadDtoPageInfo=certificateDownloadService.findAllCertificateDownload(page,limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("CertificateDownload has been found success")
                .timestamp(LocalDateTime.now())
                .data(certificateDownloadDtoPageInfo)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteCertificateDownloadById(@PathVariable("id") Integer id) {
        Integer deleteId= certificateDownloadService.deleteCertificateDownload(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("CertificateDownload has been delete success")
                .timestamp(LocalDateTime.now())
                .data(deleteId)
                .build();
    }
}
