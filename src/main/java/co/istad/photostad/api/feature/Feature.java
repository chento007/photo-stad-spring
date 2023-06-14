package co.istad.photostad.api.feature;

import co.istad.photostad.api.json.Design;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Feature {
    private Integer id;
    private String name;
    private Design sample;
}
