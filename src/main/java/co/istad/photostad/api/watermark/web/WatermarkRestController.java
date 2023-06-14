package co.istad.photostad.api.watermark.web;

import co.istad.photostad.api.watermark.Watermark;
import co.istad.photostad.api.watermark.WatermarkService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/watermarks")
public class WatermarkRestController {
    private final WatermarkService watermarkService;

    @PostMapping("/download-zip")
    public BaseRest<?> downloadByZip(@Valid @RequestBody CreateWatermarkDto createWatermarkDto) {
        var resource = watermarkService.downloadByZip(createWatermarkDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("watermark has been download success")
                .timestamp(LocalDateTime.now())
                .data(resource)
                .build();
    }

    @PostMapping
    public BaseRest<?> insertWatermark(@Valid @RequestBody CreateWatermarkDto createWatermarkDto) {
        WatermarkDto watermarkDto = watermarkService.insertWatermark(createWatermarkDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("watermark has been insert success")
                .timestamp(LocalDateTime.now())
                .data(watermarkDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> findWatermarkById(@PathVariable("id") Integer id) {
        WatermarkDto watermarkDto = watermarkService.selectWatermarkById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("watermark has been found")
                .timestamp(LocalDateTime.now())
                .data(watermarkDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteWatermarkById(@PathVariable("id") Integer id) {
        Integer deletedId = watermarkService.deleteWatermarkById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("watermark has been delete success")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAll(@RequestParam(required = false, defaultValue = "1", name = "page") int page,
                               @RequestParam(required = false, defaultValue = "20", name = "limit") int limit) {
        PageInfo<WatermarkDto> watermarkDtoPageInfo = watermarkService.selectAllWatermark(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("watermark has been found success")
                .timestamp(LocalDateTime.now())
                .data(watermarkDtoPageInfo)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> update(@PathVariable("id") Integer id, @Valid @RequestBody CreateWatermarkDto createWatermarkDto) {
        WatermarkDto resultWatermark = watermarkService.updateWatermark(id, createWatermarkDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("watermark has been update success")
                .timestamp(LocalDateTime.now())
                .data(resultWatermark)
                .build();
    }

    @GetMapping("/daily-edit")
    public BaseRest<?> findDailyEditCount() {
        DailyEditCount dailyEditCount = watermarkService.dailyEdit();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("daily edit watermark and certificate has been found")
                .timestamp(LocalDateTime.now())
                .data(dailyEditCount)
                .build();
    }
}
