package co.istad.photostad.api.feature;

import co.istad.photostad.api.feature.web.FeatureDto;
import com.github.pagehelper.PageInfo;

public interface FeatureService {
    FeatureDto insertFeature(FeatureDto featureDto);
    FeatureDto selectFeatureById(Integer id);
    PageInfo<FeatureDto> selectAll(int page,int limit,String name);
    FeatureDto updateFeature(Integer id,FeatureDto featureDto);
}
