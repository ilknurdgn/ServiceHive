package ilknurdogan.servicefinder.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestGetDto {

    private Long id;

    private String jobDescription;

    private String urgency;

    private String status;

    private String customerName;

    private String address;

    private String phoneNumber;

    private String email;
}
