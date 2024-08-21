package ilknurdogan.servicefinder.dto.responseDto;

import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestGetDto {

    private Long id;

    private String jobDescription;

    private String urgency;

    private ServiceRequestStatus status;

    private String customerName;

    private String serviceProviderName;

    private String address;

    private String phoneNumber;

    private String email;
}
