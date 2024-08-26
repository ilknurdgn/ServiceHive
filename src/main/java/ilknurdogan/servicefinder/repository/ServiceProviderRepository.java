package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    List<ServiceProvider> findAll();

    Optional<ServiceProvider> findById(Long id);

    boolean existsById(Long id);

    @Override
    void deleteById(Long id);

    List<ServiceProvider> findByCategoryAndCityAndDistrict(String category, String city, String district);

    List<ServiceProvider> findByCategoryAndCity(String category, String city);

    List<ServiceProvider> findByCategory(String category);

}
