package co.istad.photostad.api.watermarkdownload.web;

import co.istad.photostad.api.watermarkdownload.WatermarkDownloadService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.DeleteProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/watermark-downloads")
public class WatermarkDownloadRestController {
    private final WatermarkDownloadService watermarkDownloadService;

    @PostMapping
    public BaseRest<?> insertWatermarkDownload(@Valid @RequestBody CreateWatermarkDownloadDto createWatermarkDownloadDto) {
        WatermarkDownloadDto watermarkDownloadDto = watermarkDownloadService.insertWatermarkDownload(createWatermarkDownloadDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("WatermarkDownload has been insert success")
                .timestamp(LocalDateTime.now())
                .data(watermarkDownloadDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> findWatermarkDownloadById(@PathVariable("id") Integer id) {
        WatermarkDownloadDto watermarkDownloadDto = watermarkDownloadService.findWatermarkDownloadById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("WatermarkDownload has been found success")
                .timestamp(LocalDateTime.now())
                .data(watermarkDownloadDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllWatermarkDownload(@RequestParam(required = false, defaultValue = "1", name = "page") int page,
                                                @RequestParam(required = false, defaultValue = "20", name = "limit") int limit) {
        PageInfo<WatermarkDownloadDto> watermarkDownloadDtoPageInfo = watermarkDownloadService.findWatermarkDownloadAll(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("WatermarkDownload has been found success")
                .timestamp(LocalDateTime.now())
                .data(watermarkDownloadDtoPageInfo)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteWatermarkDownloadById(@PathVariable("id") Integer id) {
        Integer deleteIdResult = watermarkDownloadService.deleteWatermarkDownloadById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("WatermarkDownload has been delete success")
                .timestamp(LocalDateTime.now())
                .data(deleteIdResult)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateWatermarkDownloadById(@PathVariable("id") Integer id, @Valid @RequestBody CreateWatermarkDownloadDto createWatermarkDownloadDto) {
        WatermarkDownloadDto watermarkDownloadDto = watermarkDownloadService.updateWatermarkDownloadById(id, createWatermarkDownloadDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("WatermarkDownload has been update success")
                .timestamp(LocalDateTime.now())
                .data(watermarkDownloadDto)
                .build();
    }
}
