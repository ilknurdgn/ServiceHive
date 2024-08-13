package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.IndividualProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualProviderRepository extends JpaRepository<IndividualProvider, Long> {

    boolean existsByEmail(String email);
}
