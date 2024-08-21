package ilknurdogan.servicefinder.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyProvider extends ServiceProvider{

    @Column
    @NotBlank
    private String companyName ;

    @Column
    @NotBlank
    private String companyNumber;

    @Override
    public String getName() {
        return companyName;
    }
}
