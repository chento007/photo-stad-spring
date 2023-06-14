package co.istad.photostad.api.watermark;

import co.istad.photostad.api.watermark.web.CreateWatermarkDto;
import co.istad.photostad.api.watermark.web.WatermarkDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatermarkMapStruct {
    WatermarkDto watermarkToWatermarkDto(Watermark model);

    Watermark createWatermarkDtoToWatermark(CreateWatermarkDto model);

    PageInfo<WatermarkDto> watermarkPageinfoToWatermarkDtoPageInfo(PageInfo<Watermark> model);
}
