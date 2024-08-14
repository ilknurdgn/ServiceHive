package ilknurdogan.servicefinder.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectGetDto {

    private String description;

    private List<String> projectImgUrl;
}
