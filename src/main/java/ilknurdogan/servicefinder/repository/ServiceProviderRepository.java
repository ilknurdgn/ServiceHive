package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    List<ServiceProvider> findAll();
}
