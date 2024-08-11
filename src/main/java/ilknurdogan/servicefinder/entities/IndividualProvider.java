package ilknurdogan.servicefinder.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class IndividualProvider extends ServiceProvider{

    @Column
    @NotBlank
    private String firstName;

    @Column
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    private String identityNumber;


}
