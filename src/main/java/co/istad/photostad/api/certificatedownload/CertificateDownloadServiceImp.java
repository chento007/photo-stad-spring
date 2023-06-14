package co.istad.photostad.api.certificatedownload;

import co.istad.photostad.api.certificatedownload.web.CertificateDownloadDto;
import co.istad.photostad.api.certificatedownload.web.CreateCertificateDownloadDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CertificateDownloadServiceImp implements CertificateDownloadService {
    private final CertificateDownloadMapper certificateDownloadMapper;
    private final CertificateDownloadMapStruct certificateDownloadMapStruct;

    @Override
    public CertificateDownloadDto insertCertificateDownload(CreateCertificateDownloadDto createCertificateDownloadDto) {
        CertificateDownload certificateDownload = certificateDownloadMapStruct.createCertificateDownloadToCertificateDownload(createCertificateDownloadDto);
        certificateDownload.setUuid(UUID.randomUUID().toString());
        certificateDownload.setDownloadAt(new Timestamp(System.currentTimeMillis()));
        if (certificateDownloadMapper.insert(certificateDownload)) {
            return this.selectCertificateDownloadById(certificateDownload.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "insert certificate download is fail , try again"
        );
    }

    @Override
    public CertificateDownloadDto selectCertificateDownloadById(Integer id) {
        CertificateDownload certificateDownload = certificateDownloadMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Certificate Download with id %d is not found", id)
                )
        );
        return certificateDownloadMapStruct.certificateDownloadToCertificateDownloadDto(certificateDownload);
    }

    @Override
    public CertificateDownloadDto updateCertificateDownloadById(Integer id, CreateCertificateDownloadDto createWatermarkDownloadDto) {
        if (certificateDownloadMapper.isIdExist(id)) {
            CertificateDownload certificateDownload = certificateDownloadMapStruct.createCertificateDownloadToCertificateDownload(createWatermarkDownloadDto);
            certificateDownload.setId(id);
            if (certificateDownloadMapper.updateById(certificateDownload)) {
                return this.selectCertificateDownloadById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "update certificate download is fail , try again"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Certificate Download with id %d is not found", id)
        );
    }

    @Override
    public PageInfo<CertificateDownloadDto> findAllCertificateDownload(int page, int limit) {
        PageInfo<CertificateDownload> certificateDownloadPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> certificateDownloadMapper.findAll()
        );
        return certificateDownloadMapStruct.pageInfoCertificateDownloadToPageInfoCertificateDownloadDto(certificateDownloadPageInfo);
    }

    @Override
    public Integer deleteCertificateDownload(Integer id) {
        if (certificateDownloadMapper.isIdExist(id)) {
            if (certificateDownloadMapper.deletedById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "delete certificate download is fail , try again"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Certificate Download with id %d is not found", id)
        );
    }
}
