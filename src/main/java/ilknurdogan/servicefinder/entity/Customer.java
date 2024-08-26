package ilknurdogan.servicefinder.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public String getName(){
        return firstName + " " + lastName;
    }

}
