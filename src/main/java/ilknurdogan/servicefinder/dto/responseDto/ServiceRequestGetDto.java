package ilknurdogan.servicefinder.dto.responseDto;

import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import ilknurdogan.servicefinder.entity.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    private List<Offer> offer;
}
