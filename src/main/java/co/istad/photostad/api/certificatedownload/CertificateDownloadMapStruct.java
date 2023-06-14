package co.istad.photostad.api.certificatedownload;

import co.istad.photostad.api.certificatedownload.web.CertificateDownloadDto;
import co.istad.photostad.api.certificatedownload.web.CreateCertificateDownloadDto;
import co.istad.photostad.api.watermarkdownload.web.CreateWatermarkDownloadDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificateDownloadMapStruct {
    CertificateDownloadDto certificateDownloadToCertificateDownloadDto(CertificateDownload model);

    CertificateDownload createCertificateDownloadToCertificateDownload(CreateCertificateDownloadDto model);

    PageInfo<CertificateDownloadDto> pageInfoCertificateDownloadToPageInfoCertificateDownloadDto(PageInfo<CertificateDownload> model);
}
