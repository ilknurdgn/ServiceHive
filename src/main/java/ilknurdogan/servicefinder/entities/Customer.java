package ilknurdogan.servicefinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Customer extends User{

    @Column
    @NotBlank
    private String firstName;

    @Column
    @NotBlank
    private String lastName;

}
