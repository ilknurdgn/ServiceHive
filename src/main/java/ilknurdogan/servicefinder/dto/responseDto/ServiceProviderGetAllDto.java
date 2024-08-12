package ilknurdogan.servicefinder.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProviderGetAllDto {
    private Long id;

    private String email;

    private String phoneNumber;

    private String profileImgUrl;

    private String serviceType;

    private String about;

    private String city;

    private String district;

    private int averageScore;

    private String category;

    private String firstName;

    private String lastName;

    private String identityNumber;

    private String companyName ;

    private String companyNumber;
}
