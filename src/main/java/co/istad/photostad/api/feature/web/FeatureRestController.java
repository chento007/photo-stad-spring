package co.istad.photostad.api.feature.web;

import co.istad.photostad.api.feature.FeatureService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/features")
public class FeatureRestController {
    private final FeatureService featureService;

    @PostMapping
    public BaseRest<?> insertFeature(@Valid @RequestBody FeatureDto featureDto) {
        FeatureDto resultInsertFeature = featureService.insertFeature(featureDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Feature has been insert success")
                .timestamp(LocalDateTime.now())
                .data(resultInsertFeature)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectFeatureById(@PathVariable("id") Integer id) {
        FeatureDto result = featureService.selectFeatureById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Feature has been found success")
                .timestamp(LocalDateTime.now())
                .data(result)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllFeature(
            @RequestParam(required = false, defaultValue = "0", name = "page") int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", name = "name") String name) {
        PageInfo<FeatureDto> result = featureService.selectAll(page, limit, name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Feature has been found success")
                .timestamp(LocalDateTime.now())
                .data(result)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> updateFeature(@PathVariable("id")Integer id,@Valid @RequestBody FeatureDto featureDto){
        FeatureDto resultUpdate=featureService.updateFeature(id,featureDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Feature has been update success")
                .timestamp(LocalDateTime.now())
                .data(resultUpdate)
                .build();
    }
}
