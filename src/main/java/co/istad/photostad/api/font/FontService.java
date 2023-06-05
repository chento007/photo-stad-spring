package co.istad.photostad.api.font;

import java.util.List;

public interface FontService {
    List<Font> saveMultiple(List<Font> fontList);
    List<Font> findAll();
}
