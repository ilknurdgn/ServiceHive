package ilknurdogan.servicefinder.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectCreateDto {

    private String description;

    private List<String> projectImgUrl;

    @NotNull
    private Long serviceProviderId;
}
