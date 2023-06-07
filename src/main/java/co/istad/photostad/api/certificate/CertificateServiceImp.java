package co.istad.photostad.api.certificate;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.certificate.web.CreateCertificateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateServiceImp implements CertificateService{
    private final CertificateMapper certificateMapper;
    private final CertificateMapStruct certificateMapStrut;
    @Override
    public CertificateDto insertCertificate(CreateCertificateDto createCertificateDto) {
        Certificate certificate=certificateMapStrut.createCertificateDtoToCertificate(createCertificateDto);
        certificate.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        certificate.setCreatedBy(1);
        certificate.setUuid(UUID.randomUUID().toString());
        certificate.setIsDeleted(false);
        if(certificateMapper.insert(certificate)){
            return certificateMapStrut.certificateToCertificateDto(certificate);
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "insert certificate has been fail !"
        );
    }

    @Override
    public CertificateDto selectCertificateById(Integer id) {
        Certificate certificate=certificateMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("certificate with %d hasn't found ",id)
                )
        );
        return certificateMapStrut.certificateToCertificateDto(certificate);
    }

    @Override
    public Integer deleteCertificateById(Integer id) {
        boolean isIdExit= certificateMapper.isIdExist(id);
        if(isIdExit){
            certificateMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("certificate with %d hasn't found",id)
        );
    }
}
