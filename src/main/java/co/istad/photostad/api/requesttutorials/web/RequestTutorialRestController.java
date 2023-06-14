package co.istad.photostad.api.requesttutorials.web;

import co.istad.photostad.api.requesttutorials.RequestTutorialService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/request-tutorials")
public class RequestTutorialRestController {
    private final RequestTutorialService requestTutorialService;

    @GetMapping
    public BaseRest<?> findAll(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                               @RequestParam(required = false, name = "limit", defaultValue = "20") int limit) {
        PageInfo<RequestTutorialDto> requestTutorialDtoPageInfo = requestTutorialService.selectRequestTutorialAll(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("request tutorial has been found success")
                .data(requestTutorialDtoPageInfo)
                .build();
    }

    @PostMapping
    public BaseRest<?> insertTutorial(@RequestBody ModifyRequestTutorialDto modifyRequestTutorialDto) {
        RequestTutorialDto requestTutorialDto = requestTutorialService.insertRequestTutorial(modifyRequestTutorialDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("request tutorial has been insert success")
                .data(requestTutorialDto)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateRequestsTutorialById(@PathVariable("id") Integer id, @RequestBody ModifyRequestTutorialDto modifyRequestTutorialDto) {
        RequestTutorialDto requestTutorialDto = requestTutorialService.updateRequestTutorialById(id, modifyRequestTutorialDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("request tutorial has been update success")
                .data(requestTutorialDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectRequestTutorialByID(@PathVariable("id") Integer id) {
        RequestTutorialDto requestTutorialDto = requestTutorialService.selectRequestTutorialById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("request tutorial has been found success")
                .data(requestTutorialDto)
                .build();
    }

    @GetMapping("/is-read")
    public BaseRest<?> selectRequestTutorialByIsRead(@RequestParam(required = false, name = "page", defaultValue = "1") int page,
                                                     @RequestParam(required = false, name = "limit", defaultValue = "20") int limit,
                                                     @RequestParam(name = "isRead", defaultValue = "false") Boolean isRead) {
        PageInfo<RequestTutorialDto> requestTutorialDtoPageInfo = requestTutorialService.readRequestTutorial(page, limit, isRead);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("request tutorial has been found success")
                .data(requestTutorialDtoPageInfo)
                .build();
    }

}
