package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificateMapStruct {
    CertificateDto certificateToCertificateDto(Certificate model);
    Certificate createCertificateDtoToCertificate(CreateCertificateDto model);
}
