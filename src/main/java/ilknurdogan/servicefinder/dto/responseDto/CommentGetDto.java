package ilknurdogan.servicefinder.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentGetDto {

    private Long id;

    private String text;

    private Integer score;

    private LocalDate createdDate;

    private String customerProfileImgUrl;

    private String customerName;


}
