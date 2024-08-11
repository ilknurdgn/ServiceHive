package ilknurdogan.servicefinder.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyProvider extends ServiceProvider{

    @Column(name = "companyName")
    @NotBlank
    private String companyName ;

    @Column(name = "companyNumber")
    @NotBlank
    private String companyNumber;
}
