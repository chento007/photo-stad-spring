package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;

public interface CertificateService {
    CertificateDto insertCertificate(CreateCertificateDto createCertificateDto);
    CertificateDto selectCertificateById(Integer id);
    Integer deleteCertificateById(Integer id);
}
