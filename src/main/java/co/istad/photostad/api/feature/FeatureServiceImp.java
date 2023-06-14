package co.istad.photostad.api.feature;

import co.istad.photostad.api.feature.web.FeatureDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class FeatureServiceImp implements FeatureService {
    private final FeatureMapper featureMapper;
    private final FeatureMapStruct featureMapStruct;

    @Override
    public FeatureDto insertFeature(FeatureDto featureDto) {
        Feature feature = featureMapStruct.featureDtoToFeature(featureDto);
        if (featureMapper.insert(feature)) {
            return this.selectFeatureById(feature.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "insert feature is fail"
        );
    }

    @Override
    public FeatureDto selectFeatureById(Integer id) {
        Feature feature = featureMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("feature with %d is not found", id)
                )
        );
        return featureMapStruct.featureToFeatureDto(feature);
    }

    @Override
    public PageInfo<FeatureDto> selectAll(int page, int limit, String name) {
        PageInfo<Feature> featurePageInfo = PageHelper.offsetPage(page, limit).doSelectPageInfo(
                () -> featureMapper.findAll(name)
        );
        if (!name.isEmpty() && featureMapper.findAll(name).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("feature with name %s is not found", name)
            );
        }
        return featureMapStruct.featurePageInfoToFeaturePageInfoDto(featurePageInfo);
    }

    @Override
    public FeatureDto updateFeature(Integer id, FeatureDto featureDto) {
        if (featureMapper.isFeatureIdExist(id)) {
            Feature featureUpdate = featureMapStruct.featureDtoToFeature(featureDto);
            featureUpdate.setId(id);
            if (featureMapper.update(featureUpdate)) {
                return this.selectFeatureById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("feature with %d is update fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("feature with %d is not found", id)
        );
    }
}
