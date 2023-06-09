package co.istad.photostad.api.image;

import co.istad.photostad.api.image.web.ImageDto;
import co.istad.photostad.api.image.web.ModifyImageDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ImageService {
    ImageDto insertImage(ModifyImageDto modifyImageDto);
    ImageDto selectById(Integer id);
    Integer deleteById(Integer id);
    ImageDto updateImage(Integer id,ModifyImageDto modifyImageDto);
    PageInfo<ImageDto> selectAllImage(int page,int limit,String type);

}
