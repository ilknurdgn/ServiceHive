package ilknurdogan.servicefinder.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
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
