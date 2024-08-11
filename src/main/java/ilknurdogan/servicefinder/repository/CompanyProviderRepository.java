package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.CompanyProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProviderRepository extends CrudRepository<CompanyProvider, Long> {
}
