package ilknurdogan.servicefinder.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


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
    private double averageScore = 0;

    @Column
    private int totalComments = 0;

    @Column
    @NotBlank
    private String category;

    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projectList;

    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;


    public abstract String getName();

    public void updateAverageScore(int newScore) {
        double totalScore = (averageScore * totalComments) + newScore;
        totalComments++;
        this.averageScore = totalScore / totalComments;
    }
}
