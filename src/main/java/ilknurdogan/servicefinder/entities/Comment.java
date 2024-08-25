package ilknurdogan.servicefinder.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    private String text;

    @Column
    @NotNull
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "serviceProvider", nullable = false)
    private ServiceProvider serviceProvider;

}
