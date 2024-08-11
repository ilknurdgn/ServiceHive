package ilknurdogan.servicefinder.entity;


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

    @Column(name = "serviceType")
    @NotBlank
    private String serviceType;

    @Column(name = "about")
    @NotBlank
    private String about;

    @Column(name = "city")
    @NotBlank
    private String city;

    @Column(name = "district")
    @NotBlank
    private String district;

    @Column(name = "averageScore")
    private int averageScore = 0;

    @Column(name = "category")
    @NotBlank
    private String category;


}
