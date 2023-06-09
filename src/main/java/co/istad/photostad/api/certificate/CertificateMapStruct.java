package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapStruct {
    CertificateDto certificateToCertificateDto(Certificate model);
    Certificate createCertificateDtoToCertificate(CreateCertificateDto model);
    List<CertificateDto> toDtoList(List<Certificate> model);
    PageInfo<CertificateDto> certificatesPageInfoToCertificatesDtoPageInfo(PageInfo<Certificate> certificatePageInfo);
}
