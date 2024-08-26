package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entity.CompanyProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProviderRepository extends JpaRepository<CompanyProvider, Long> {
    boolean existsByEmail(String email);
}
