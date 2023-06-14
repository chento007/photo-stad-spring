package co.istad.photostad.api.watermarkdownload;

import co.istad.photostad.api.watermark.web.WatermarkDto;
import co.istad.photostad.api.watermarkdownload.web.CreateWatermarkDownloadDto;
import co.istad.photostad.api.watermarkdownload.web.WatermarkDownloadDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatermarkDownloadMapStruct {
    WatermarkDownloadDto watermarkDownloadToWatermarkDownloadDto(WatermarkDownload model);
    WatermarkDownload createWatermarkDownloadDtoToWatermarkDownload(CreateWatermarkDownloadDto model);
    PageInfo<WatermarkDownloadDto> pageInnfoWatermarkToPageInfoWatermarkPageInfo(PageInfo<WatermarkDownload> model);
}
