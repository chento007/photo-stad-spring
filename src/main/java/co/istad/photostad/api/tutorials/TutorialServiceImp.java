package co.istad.photostad.api.tutorials;

import co.istad.photostad.api.tutorials.web.CreateTutorialDto;
import co.istad.photostad.api.tutorials.web.TutorialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TutorialServiceImp implements TutorialService {
    private final TutorialMapper tutorialMapper;
    private final TutorialMapStruct tutorialMapStruct;

    @Override
    public TutorialDto insertTutorial(CreateTutorialDto createTutorialDto) {
        Tutorial tutorial = tutorialMapStruct.createUserTutorialDtoToTutorial(createTutorialDto);
        tutorial.setUuid(UUID.randomUUID().toString());
        tutorial.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (tutorialMapper.insert(tutorial)) {
            return this.selectTutorialById(tutorial.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "create tutorial is fail"
        );
    }

    @Override
    public TutorialDto selectTutorialById(Integer id) {
        Tutorial tutorial = tutorialMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Tutorial with  id %d is not found", id)
                )
        );
        System.out.println(tutorial);
        return tutorialMapStruct.tutorialToTutorialDto(tutorial);
    }

}
