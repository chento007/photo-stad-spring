package co.istad.photostad.api.font.web;

import co.istad.photostad.api.font.Font;
import co.istad.photostad.api.font.FontService;
import co.istad.photostad.base.BaseRest;
import co.istad.photostad.base.FontBaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fonts")
@CrossOrigin(origins = "http://localhost:5173/")
public class FontRestController {
    private final FontService fontService;
    @PostMapping
    BaseRest<?> saveMultiple(@RequestBody List<Font> fonts) {
        List<Font> result = fontService.saveMultiple(fonts);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Font has been save success")
                .timestamp(LocalDateTime.now())
                .data(result)
                .build();
    }

    @GetMapping
    public FontBaseRest<?> findAll() {
        return FontBaseRest.builder()
                .fonts(fontService.findAll())
                .build();
    }
}
