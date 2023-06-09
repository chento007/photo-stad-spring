package co.istad.photostad.api.image;

import co.istad.photostad.api.image.web.ImageDto;
import co.istad.photostad.api.image.web.ModifyImageDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {
    private final ImageMapper imageMapper;
    private final ImageMapStruct imageMapStruct;

    @Override
    public ImageDto insertImage(ModifyImageDto modifyImageDto) {
        Image image = imageMapStruct.createImageDtoToImage(modifyImageDto);
        image.setUuid(UUID.randomUUID().toString());
        if (imageMapper.insert(image)) {
            return imageMapStruct.imageToImageDto(image);
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Inserting image has been fail ..."
        );
    }

    @Override
    public ImageDto selectById(Integer id) {
        Image image = imageMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Image with id : %d is not found", id)
                )
        );
        return imageMapStruct.imageToImageDto(image);
    }

    @Override
    public Integer deleteById(Integer id) {
        if (imageMapper.isIdExits(id)) {
            if (imageMapper.deleteById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Delete image has been fail"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Image with %d is not found", id)
        );
    }

    @Override
    public ImageDto updateImage(Integer id, ModifyImageDto modifyImageDto) {
        if (imageMapper.isIdExits((id))) {
            Image image = imageMapStruct.createImageDtoToImage(modifyImageDto);
            image.setId(id);
            if (imageMapper.updateById(image)) {
                return imageMapStruct.imageToImageDto(image);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Update Image has been fail"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                String.format("Image with  id :  %d is not found", id)
        );
    }

    @Override
    public PageInfo<ImageDto> selectAllImage(int page, int limit, String type) {
        PageInfo<Image> imagePageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> imageMapper.findAll(type)
        );
        if (!type.isEmpty() && imageMapper.findAll(type).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Image with Type : %s is not found",type)
            );
        }
        return imageMapStruct.imagePageInfoToImagePageInfoDto(imagePageInfo);
    }
}
