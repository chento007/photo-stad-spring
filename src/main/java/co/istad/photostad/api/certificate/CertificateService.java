package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageInfo;

public interface CertificateService {
    CertificateDto insertCertificate(CreateCertificateDto createCertificateDto);

    CertificateDto selectCertificateById(Integer id);

    Integer deleteCertificateById(Integer id);

    PageInfo<CertificateDto> selectAll(int page, int limit);
    CertificateDto updateCertificate(Integer id,CreateCertificateDto certificateDto);
}
