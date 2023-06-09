package co.istad.photostad.file.web;


import co.istad.photostad.base.BaseRest;
import co.istad.photostad.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {
    @Value("${file.server-path}")
    private String fileServerPath;
    private final FileService fileService;

    @PostMapping("/upload-file-base64")
    public BaseRest<?> uploadFileBase64(@RequestBody String image) {
        FileDto fileDto = fileService.uploadFileBase64(image);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been upload success")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart("file") MultipartFile file) {
        FileDto fileDto = fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been upload success")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping("/multiple")
    public BaseRest<?> uploadMultiple(@RequestPart("files") List<MultipartFile> files) {
        var result = fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been upload success")
                .timestamp(LocalDateTime.now())
                .data(result)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAll() {
        List<FileDto> resultFiles = fileService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been found success")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @GetMapping("/{name}")
    public BaseRest<?> findByName(@PathVariable("name") String name) {
        System.out.println("filename : " + name);
        FileDto resultFiles = fileService.findByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been found success")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @DeleteMapping("/{name}")
    public BaseRest<?> deleteByName(@PathVariable("name") String name) {
        String resultFiles = fileService.deleteByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been delete success")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @DeleteMapping
    public BaseRest<?> removeAllFiles() {
        boolean resultFiles = fileService.removeAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("All File has been delete success")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        return fileService.downloadFile(fileName);
    }

    @GetMapping("/downloadFilesZip")
    public ResponseEntity<Resource> compressImages() throws IOException {
        return fileService.compressImages();
    }


}
