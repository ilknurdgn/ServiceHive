package ilknurdogan.servicefinder.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualProviderCreateDto {

    private String email;

    private String password;

    private String phoneNumber;

    private String profileImgUrl;

    private String serviceType;

    private String about;

    private String city;

    private String district;

    private int averageScore = 0;

    private String category;

    private String firstName;

    private String lastName;

    private String identityNumber;
}
