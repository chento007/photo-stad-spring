package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificateMapStruct {
    CertificateDto certificateToCertificateDto(Certificate model);
    Certificate createCertificateDtoToCertificate(CreateCertificateDto model);
    PageInfo<CertificateDto> certificatePageInfoToCertificatePageInfoDto(PageInfo<Certificate> model);
}
