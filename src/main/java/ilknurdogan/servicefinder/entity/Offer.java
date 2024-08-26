package ilknurdogan.servicefinder.entity;

import ilknurdogan.servicefinder.domain.OfferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Offer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private LocalDate offerStartDate;

    @Column
    @NotNull
    private LocalDate offerEndDate;

    @Column
    @NotNull
    private Double price;

    @Column
    private String offerDetails;

    @Column
    @NotNull
    private LocalDate createdDate;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    @ManyToOne
    @JoinColumn(name = "serviceRequest")
    private ServiceRequest serviceRequest;




}
