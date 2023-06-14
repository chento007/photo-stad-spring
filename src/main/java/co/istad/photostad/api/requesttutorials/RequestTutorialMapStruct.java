package co.istad.photostad.api.requesttutorials;

import co.istad.photostad.api.requesttutorials.web.ModifyRequestTutorialDto;
import co.istad.photostad.api.requesttutorials.web.RequestTutorialDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestTutorialMapStruct {
    RequestTutorial modifyRequestTutorialDtoToRequestTutorial(ModifyRequestTutorialDto model);
    RequestTutorialDto requestTutorialToRequestTutorialDto(RequestTutorial model);
    PageInfo<RequestTutorialDto> RequestTutorialPageInfoToRequestTutorialDtoPageInfo(PageInfo<RequestTutorial> model);
}
