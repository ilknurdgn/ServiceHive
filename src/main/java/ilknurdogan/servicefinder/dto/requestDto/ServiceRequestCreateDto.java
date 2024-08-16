package ilknurdogan.servicefinder.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestCreateDto {

    @NotNull(message = "Customer id is mandatory")
    private Long customerId;

    @NotNull(message = "Service provider id is mandatory")
    private Long serviceProviderId;

    @NotBlank(message = "Job description is mandatory")
    private String jobDescription;

    @NotBlank(message = "Urgency is mandatory")
    private String urgency;

    @NotBlank(message = "Adress is mandatory")
    private String address;


}
