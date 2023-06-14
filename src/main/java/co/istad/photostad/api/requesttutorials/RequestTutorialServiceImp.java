package co.istad.photostad.api.requesttutorials;

import co.istad.photostad.api.requesttutorials.web.ModifyRequestTutorialDto;
import co.istad.photostad.api.requesttutorials.web.RequestTutorialDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RequestTutorialServiceImp implements RequestTutorialService {
    private final RequestTutorialMapper requestTutorialMapper;
    private final RequestTutorialMapStruct requestTutorialMapStruct;

    @Override
    public RequestTutorialDto insertRequestTutorial(ModifyRequestTutorialDto modifyRequestTutorialDto) {
        RequestTutorial requestTutorial = requestTutorialMapStruct.modifyRequestTutorialDtoToRequestTutorial(modifyRequestTutorialDto);
        requestTutorial.setUuid(UUID.randomUUID().toString());
        if (requestTutorialMapper.insert(requestTutorial)) {
            return this.selectRequestTutorialById(requestTutorial.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Insert request tutorial is fail"
        );
    }

    @Override
    public RequestTutorialDto selectRequestTutorialById(Integer id) {
        RequestTutorial requestTutorial = requestTutorialMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Request Tutorial with id %d is not found", id)
                )
        );
        return requestTutorialMapStruct.requestTutorialToRequestTutorialDto(requestTutorial);
    }

    @Override
    public PageInfo<RequestTutorialDto> selectRequestTutorialAll(int page, int limit) {
        PageInfo<RequestTutorial> requestTutorialPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                requestTutorialMapper::findAll
        );
        return requestTutorialMapStruct.RequestTutorialPageInfoToRequestTutorialDtoPageInfo(requestTutorialPageInfo);
    }

    @Override
    public RequestTutorialDto updateRequestTutorialById(Integer id, ModifyRequestTutorialDto modifyRequestTutorialDto) {
        if (requestTutorialMapper.isRequestTutorialExits(id)) {
            RequestTutorial requestTutorial = requestTutorialMapStruct.modifyRequestTutorialDtoToRequestTutorial(modifyRequestTutorialDto);
            requestTutorial.setId(id);
            if (requestTutorialMapper.update(requestTutorial)) {
                return this.selectRequestTutorialById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Update request tutorial is fail"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Request Tutorial with id %d is not found", id)
        );
    }

    @Override
    public PageInfo<RequestTutorialDto> readRequestTutorial(int page, int limit, Boolean isRead) {
        System.out.println("is read: "+isRead);
        PageInfo<RequestTutorial> requestTutorialPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> requestTutorialMapper.findByIsRead(isRead)
        );
        return requestTutorialMapStruct.RequestTutorialPageInfoToRequestTutorialDtoPageInfo(requestTutorialPageInfo);
    }
}
