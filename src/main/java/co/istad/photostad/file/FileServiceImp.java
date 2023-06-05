package co.istad.photostad.file;

import co.istad.photostad.file.web.FileDto;
import co.istad.photostad.util.FileUtil;
import jakarta.xml.bind.DatatypeConverter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileServiceImp implements FileService {
    private FileUtil fileUtil;
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String baseUrl;
    @Value("${file.download-url}")
    private String fileDownloadUrl;
    @Autowired
    private ResourceLoader resourceLoader;
    private final ExecutorService executor = Executors.newFixedThreadPool(100);

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> fileDtoList = new ArrayList<>();
        for (MultipartFile file : files) {
            executor.submit(() -> fileDtoList.add(fileUtil.upload(file)));
        }
        return fileDtoList;
    }

    @Override
    public List<FileDto> findAll() {
        File file = new File(fileServerPath);
        if (Objects.requireNonNull(file.list()).length > 0) {
            List<File> fileList = new ArrayList<>(List.of(Objects.requireNonNull(file.listFiles())));
            List<FileDto> resultList = new ArrayList<>();
            for (File f : fileList) {
                resultList.add(
                        FileDto.builder()
                                .name(f.getName())
                                .url(baseUrl + f.getName())
                                .downloadUrl(fileDownloadUrl + f.getName())
                                .extension(fileUtil.getExtensionFile(f))
                                .size(f.length())
                                .build()
                );
            }
            return resultList;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No file in server"
        );
    }

    @Override
    public FileDto findByName(String fileName) {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath + fileName);
        if (resource.exists()) {
            try {
                return FileDto.builder()
                        .name(resource.getFilename())
                        .url(baseUrl + resource.getFilename())
                        .downloadUrl(fileDownloadUrl + resource.getFilename())
                        .extension(fileUtil.getExtensionFile(resource.getFile()))
                        .size(resource.contentLength())
                        .build();
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "the resource cannot be resolved as absolute file path"
                );
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("file with name %s search not found", fileName)
        );
    }

    @Override
    public String deleteByName(String filename) {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath + filename);
        if (resource.exists()) {
            try {
                // check make sure delete success
                boolean delete = resource.getFile().delete();
                if (delete) {
                    return resource.getFilename();
                } else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "delete not success please !!"
                    );
                }
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "the resource cannot be resolved as absolute file path"
                );
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("file with name %s search not found", filename)
        );
    }

    @Override
    public boolean removeAllFiles() {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath);
        try {
            File folder = ResourceUtils.getFile(resource.getURL());
            FileUtils.cleanDirectory(folder);
            return true;
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "delete all files fail ... ! because directory doesn't exist or resource not available"
            );
        }
    }

    @Override
    public FileDto uploadFileBase64(String  image) {
        String[] record = image.split(",");
        String extension = fileUtil.getExtensionBase64(record[0]);
        String fileImage = record[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(fileImage);
        String fileName = String.format("%s.%s", UUID.randomUUID(), extension);
        try {
            Path path = Paths.get(fileServerPath + fileName);
            Files.write(path, imageBytes);
            return FileDto.builder()
                    .name(fileName)
                    .url(baseUrl + fileName)
                    .downloadUrl(fileDownloadUrl + path.getFileName())
                    .extension(extension)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload file failed , please try again"
            );
        }
    }
}