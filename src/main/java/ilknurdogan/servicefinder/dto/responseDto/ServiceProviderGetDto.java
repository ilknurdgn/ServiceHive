package ilknurdogan.servicefinder.dto.responseDto;

import ilknurdogan.servicefinder.domain.CategoryType;
import ilknurdogan.servicefinder.domain.ServiceProviderType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProviderGetDto {
    private Long id;

    private String email;

    private String phoneNumber;

    private String profileImgUrl;

    private ServiceProviderType serviceProviderType;

    private String about;

    private String city;

    private String district;

    private double averageScore;

    private Long totalComments;

    private CategoryType category;

    private String firstName;

    private String lastName;

    private String identityNumber;

    private String companyName ;

    private String companyNumber;
}
