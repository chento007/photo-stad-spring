package co.istad.photostad.api.tutorial;

import co.istad.photostad.api.tutorial.web.CreateTutorialDto;
import co.istad.photostad.api.tutorial.web.TutorialDto;
import co.istad.photostad.api.tutorial.web.UpdateTutorialDto;
import co.istad.photostad.api.tutorial.web.ViewTutorialDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<TutorialDto> selectAllTutorial(int page, int limit, String name) {
        PageInfo<Tutorial> tutorialPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> tutorialMapper.findAll(name)
        );
        if (!name.isEmpty() && tutorialMapper.findAll(name).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Tutorial with name %s is not found", name)
            );
        }
        return tutorialMapStruct.pageInfoTutorialToPageInfoTutorialDto(tutorialPageInfo);
    }

    @Override
    public Integer deleteTutorialById(Integer id) {
        if (tutorialMapper.isTutorialIdExits(id)) {
            if (tutorialMapper.deleteTutorialById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "tutorial delete is fail "
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Tutorial with  id %d is not found", id)
        );
    }

    @Override
    public TutorialDto updateTutorialById(Integer id, UpdateTutorialDto updateTutorialDto) {
        if (tutorialMapper.isTutorialIdExits(id)) {
            Tutorial tutorial = tutorialMapStruct.updateTutorialDtoToTutorial(updateTutorialDto);
            tutorial.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            tutorial.setId(id);
            if (tutorialMapper.updateTutorialById(tutorial)) {
                return this.selectTutorialById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "tutorial update is fail "
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Tutorial with  id %d is not found", id)
        );
    }

    @Override
    public ViewTutorialDto selectViewTutorial() {
        return new ViewTutorialDto(tutorialMapper.selectCountByViewCount());
    }

}
