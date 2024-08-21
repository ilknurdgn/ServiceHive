package ilknurdogan.servicefinder.entities;


import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ServiceRequest")
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "serviceProvider")
    private ServiceProvider serviceProvider;

    @Column
    @NotBlank
    private String jobDescription;

    @Column
    @NotBlank
    private String urgency;

    @Column
    @NotBlank
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private ServiceRequestStatus status;

    private String phoneNumber;

    @Email
    private String email;


}
