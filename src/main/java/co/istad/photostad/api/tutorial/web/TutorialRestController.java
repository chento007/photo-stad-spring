package co.istad.photostad.api.tutorial.web;

import co.istad.photostad.api.tutorial.TutorialService;
import co.istad.photostad.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tutorials")
public class TutorialRestController {
    private final TutorialService tutorialService;

    @PostMapping
    public BaseRest<?> insertTutorial(@Valid @RequestBody CreateTutorialDto createTutorialDto) {
        TutorialDto tutorialDto = tutorialService.insertTutorial(createTutorialDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been insert success")
                .data(tutorialDto)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectTutorialById(@PathVariable Integer id) {
        TutorialDto tutorialDto = tutorialService.selectTutorialById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been found success")
                .data(tutorialDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> selectAllTutorial(
            @RequestParam(required = false, defaultValue = "1", name = "page") int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", name = "name") String name) {
        PageInfo<TutorialDto> findAll = tutorialService.selectAllTutorial(page, limit, name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been found success")
                .data(findAll)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteById(@PathVariable("id") Integer id) {
        Integer deletedId = tutorialService.deleteTutorialById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been delete success")
                .data(deletedId)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateById(@PathVariable("id") Integer id,@Valid @RequestBody UpdateTutorialDto updateTutorialDto) {
        TutorialDto tutorialDto = tutorialService.updateTutorialById(id, updateTutorialDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been update success")
                .data(tutorialDto)
                .build();
    }
    @GetMapping("/tutorial-count")
    public BaseRest<?> selectCountTutorial() {
        ViewTutorialDto viewTutorialDto = tutorialService.selectViewTutorial();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been found success")
                .data(viewTutorialDto)
                .build();
    }
}
