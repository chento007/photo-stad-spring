package co.istad.photostad.api.watermark.web;

import co.istad.photostad.api.watermark.WatermarkService;
import co.istad.photostad.api.json.Design;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watermark")
public class WatermarkRestController {
    private final WatermarkService watermarkService;
    @PostMapping
    void compress(@RequestBody Design design){
        watermarkService.compress(design);
    }
}
