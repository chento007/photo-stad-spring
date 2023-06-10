package co.istad.photostad.api.tutorials;

import co.istad.photostad.api.tutorials.web.CreateTutorialDto;
import co.istad.photostad.api.tutorials.web.TutorialDto;

public interface TutorialService {
    TutorialDto insertTutorial(CreateTutorialDto createTutorialDto);
    TutorialDto selectTutorialById(Integer id);
}
