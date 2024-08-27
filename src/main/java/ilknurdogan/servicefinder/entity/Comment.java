package ilknurdogan.servicefinder.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1000)
    @NotBlank
    private String text;

    @Column
    @NotNull
    private LocalDate createdDate;

    @Column
    @NotNull
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "serviceProvider", nullable = false)
    private ServiceProvider serviceProvider;

    @OneToOne
    @JoinColumn(name = "serviceRequest", nullable = false)
    private ServiceRequest serviceRequest;

}
