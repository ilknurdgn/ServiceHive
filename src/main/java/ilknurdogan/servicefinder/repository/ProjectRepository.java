package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByServiceProvider_Id(Long serviceProviderId);
}
