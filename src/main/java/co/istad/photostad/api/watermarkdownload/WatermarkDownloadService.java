package co.istad.photostad.api.watermarkdownload;

import co.istad.photostad.api.watermarkdownload.web.CreateWatermarkDownloadDto;
import co.istad.photostad.api.watermarkdownload.web.WatermarkDownloadDto;
import com.github.pagehelper.PageInfo;

public interface WatermarkDownloadService {
    WatermarkDownloadDto insertWatermarkDownload(CreateWatermarkDownloadDto createWatermarkDownloadDto);
    WatermarkDownloadDto findWatermarkDownloadById(Integer id);
    PageInfo<WatermarkDownloadDto> findWatermarkDownloadAll(int page,int limit);
    Integer deleteWatermarkDownloadById(Integer id);
    WatermarkDownloadDto updateWatermarkDownloadById(Integer id, CreateWatermarkDownloadDto createWatermarkDownloadDto);
}
