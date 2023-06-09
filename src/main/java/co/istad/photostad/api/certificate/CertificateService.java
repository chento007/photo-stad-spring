package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CertificateService {
    CertificateDto insertCertificate(CreateCertificateDto createCertificateDto);
    CertificateDto selectCertificateById(Integer id);
    Integer deleteCertificateById(Integer id);
    Integer updateStatusCertificatesById(Integer id,boolean status);
//    List<CertificateDto> findAll();
    PageInfo<CertificateDto> findAll(int page, int limit);
}
