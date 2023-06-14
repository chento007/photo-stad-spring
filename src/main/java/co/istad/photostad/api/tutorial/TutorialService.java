package co.istad.photostad.api.tutorial;

import co.istad.photostad.api.tutorial.web.CreateTutorialDto;
import co.istad.photostad.api.tutorial.web.TutorialDto;
import co.istad.photostad.api.tutorial.web.UpdateTutorialDto;
import co.istad.photostad.api.tutorial.web.ViewTutorialDto;
import com.github.pagehelper.PageInfo;

public interface TutorialService {
    /**
     * insert tutorial
     *
     * @param createTutorialDto : is data need to insert and require
     * @return TutorialDto use to response that necessary to response
     */
    TutorialDto insertTutorial(CreateTutorialDto createTutorialDto);

    /**
     * search tutorial by id
     *
     * @param id : need id from client then search it databases
     * @return TutorialDto  use to response that necessary to response
     */
    TutorialDto selectTutorialById(Integer id);

    /**
     * use to select all tutorial from database and response with  pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @param name  : is required false if it has value will search from it , but if not search normal
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<TutorialDto> selectAllTutorial(int page, int limit, String name);

    /**
     * use to delete tutorial by id, and it just updates status from false to true
     *
     * @param id is required to search before delete
     * @return id they deleted
     */
    Integer deleteTutorialById(Integer id);

    /**
     * update tutoial in some field of tutorial
     *
     * @param updateTutorialDto : data need to update
     * @return :TutorialDto use to response that necessary to response
     */
    TutorialDto updateTutorialById(Integer id, UpdateTutorialDto updateTutorialDto);
    ViewTutorialDto selectViewTutorial();
}
