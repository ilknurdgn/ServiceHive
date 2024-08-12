package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.ServiceProvider;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    List<ServiceProvider> findAll();

    Optional<ServiceProvider> findById(Long id);
}
