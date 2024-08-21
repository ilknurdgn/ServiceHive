package ilknurdogan.servicefinder.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OfferCreateDto {

    @NotNull(message = "Start date is mandatory")
    private LocalDate offerStartDate;

    @NotNull(message = "End date is mandatory")
    private LocalDate offerEndDate;

    @NotNull(message = "Price is mandatory")
    private Double price;

    private String offerDetails;

    @NotNull(message = "Service requestId is mandatory")
    private Long serviceRequestId;
}
