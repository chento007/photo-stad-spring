package co.istad.photostad.api.certificatedownload;

import co.istad.photostad.api.certificatedownload.web.CertificateDownloadDto;
import co.istad.photostad.api.certificatedownload.web.CreateCertificateDownloadDto;
import co.istad.photostad.api.watermarkdownload.web.CreateWatermarkDownloadDto;
import com.github.pagehelper.PageInfo;

public interface CertificateDownloadService {
    CertificateDownloadDto insertCertificateDownload(CreateCertificateDownloadDto createCertificateDownloadDto);
    CertificateDownloadDto selectCertificateDownloadById(Integer id);
    CertificateDownloadDto updateCertificateDownloadById(Integer id, CreateCertificateDownloadDto createCertificateDownloadDto);
    PageInfo<CertificateDownloadDto> findAllCertificateDownload(int page,int limit);
    Integer deleteCertificateDownload(Integer id);
}

