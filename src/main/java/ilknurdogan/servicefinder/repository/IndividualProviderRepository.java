package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.IndividualProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualProviderRepository extends CrudRepository<IndividualProvider, Long> {

}
