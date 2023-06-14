package co.istad.photostad.api.feature;

import co.istad.photostad.api.feature.web.FeatureDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureMapStruct {
    FeatureDto featureToFeatureDto(Feature model);
    Feature featureDtoToFeature(FeatureDto model);
    PageInfo<FeatureDto> featurePageInfoToFeaturePageInfoDto(PageInfo<Feature> model);
}
