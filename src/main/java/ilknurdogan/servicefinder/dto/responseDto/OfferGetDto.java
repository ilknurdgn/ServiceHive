package ilknurdogan.servicefinder.dto.responseDto;

import ilknurdogan.servicefinder.domain.OfferStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OfferGetDto {
    private Long id;

    private LocalDate offerStartDate;

    private LocalDate offerEndDate;

    private Double price;

    private String offerDetails;

    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

}
