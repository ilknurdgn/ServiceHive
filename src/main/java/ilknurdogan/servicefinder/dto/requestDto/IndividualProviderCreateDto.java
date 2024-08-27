package ilknurdogan.servicefinder.dto.requestDto;

import ilknurdogan.servicefinder.domain.ServiceProviderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualProviderCreateDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private String phoneNumber;

    private String profileImgUrl;

    @NotNull(message = "Service type is mandatory")
    private ServiceProviderType serviceProviderType;

    @NotBlank(message = "About is mandatory")
    private String about;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "District is mandatory")
    private String district;

    @NotNull(message = "District is mandatory")
    private String category;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Identity number is mandatory")
    private String identityNumber;
}
