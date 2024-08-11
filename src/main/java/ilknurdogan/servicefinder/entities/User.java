package ilknurdogan.servicefinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String password;

    @Column
    private String phoneNumber;

    @Column
    @NotBlank
    private String profileImgUrl;


}
