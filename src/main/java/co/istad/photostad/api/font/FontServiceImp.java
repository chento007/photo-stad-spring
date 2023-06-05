package co.istad.photostad.api.font;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FontServiceImp implements FontService{
    private final FontMapper fontMapper;
    @Override
    public List<Font> saveMultiple(List<Font> fontList) {
        for(Font font:fontList){
            fontMapper.save(font);
        }
        return fontList;
    }

    @Override
    public List<Font> findAll() {
        return fontMapper.selectAll();
    }
}
