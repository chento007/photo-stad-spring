package co.istad.photostad.api.tutorials;

import co.istad.photostad.api.tutorials.web.CreateTutorialDto;
import co.istad.photostad.api.tutorials.web.TutorialDto;
import co.istad.photostad.api.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TutorialMapStruct {
    @Mapping(source = "thumbnail", target = "thumbnail.id")
    Tutorial createUserTutorialDtoToTutorial(CreateTutorialDto model);
    TutorialDto tutorialToTutorialDto(Tutorial model);
}
