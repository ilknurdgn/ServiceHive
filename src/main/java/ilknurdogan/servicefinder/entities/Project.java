package ilknurdogan.servicefinder.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "Project")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String description;

    @ElementCollection
    @Column
    private List<String> projectImgUrl;

    @ManyToOne
    @JoinColumn(name = "ServiceProvider")
    private ServiceProvider serviceProvider;

}
