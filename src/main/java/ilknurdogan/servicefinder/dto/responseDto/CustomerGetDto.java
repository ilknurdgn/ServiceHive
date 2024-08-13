package ilknurdogan.servicefinder.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerGetDto {

    private  Long id;

    private String email;

    private String phoneNumber;

    private String profileImgUrl;

    private String firstName;

    private String lastName;
}
