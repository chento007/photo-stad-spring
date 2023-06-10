package co.istad.photostad.api.tutorials.web;

import co.istad.photostad.api.tutorials.TutorialService;
import co.istad.photostad.base.BaseRest;
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
    public BaseRest<?> insertTutorial(@RequestBody CreateTutorialDto createTutorialDto){
        TutorialDto tutorialDto=tutorialService.insertTutorial(createTutorialDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been insert success")
                .data(tutorialDto)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectTutorialById(@PathVariable Integer id){
        TutorialDto tutorialDto=tutorialService.selectTutorialById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("tutorial has been found success")
                .data(tutorialDto)
                .build();
    }
}
