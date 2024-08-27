package ilknurdogan.servicefinder.entity;


import ilknurdogan.servicefinder.domain.CategoryType;
import ilknurdogan.servicefinder.domain.ServiceProviderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Enumerated(EnumType.STRING)
    private ServiceProviderType serviceProviderType;

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
    private Long totalComments = 0L;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoryType category;

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
