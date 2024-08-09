package ilknurdogan.servicefinder.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "IndividualProvider")
@Data
@EqualsAndHashCode(callSuper = true)
public class IndividualProvider extends ServiceProvider{

    @Column(name = "firstName")
    @NotBlank
    private String firstName;

    @Column(name = "lastName")
    @NotBlank
    private String lastName;

    @Column(name = "identityNumber")
    @NotBlank
    private String identityNumber;
}
