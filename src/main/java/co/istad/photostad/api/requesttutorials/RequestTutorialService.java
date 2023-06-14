package co.istad.photostad.api.requesttutorials;

import co.istad.photostad.api.requesttutorials.web.ModifyRequestTutorialDto;
import co.istad.photostad.api.requesttutorials.web.RequestTutorialDto;
import com.github.pagehelper.PageInfo;

public interface RequestTutorialService {
    RequestTutorialDto insertRequestTutorial(ModifyRequestTutorialDto modifyRequestTutorialDto);
    RequestTutorialDto selectRequestTutorialById(Integer id);
    PageInfo<RequestTutorialDto> selectRequestTutorialAll(int page,int limit);
    RequestTutorialDto updateRequestTutorialById(Integer id,ModifyRequestTutorialDto modifyRequestTutorialDto);
    PageInfo<RequestTutorialDto> readRequestTutorial(int page,int limit,Boolean isRead);
}
