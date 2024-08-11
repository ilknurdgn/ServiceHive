package ilknurdogan.servicefinder.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ServiceProvider")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ServiceProvider extends User{

    @Column
    @NotBlank
    private String serviceType;

    @Column
    @NotBlank
    private String about;

    @Column
    @NotBlank
    private String city;

    @Column
    @NotBlank
    private String district;

    @Column
    private int averageScore = 0;

    @Column
    @NotBlank
    private String category;


}
