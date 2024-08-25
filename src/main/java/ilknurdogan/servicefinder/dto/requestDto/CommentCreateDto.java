package ilknurdogan.servicefinder.dto.requestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {


    @NotNull(message = "Comment text is mandatory")
    private String text;

    @NotNull(message = "Score is mandatory")
    private Integer score;

    @NotNull(message = "Service request id is mandatory")
    private Long serviceRequestId;

}
