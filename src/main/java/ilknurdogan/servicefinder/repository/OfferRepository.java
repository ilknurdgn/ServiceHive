package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findByServiceRequestId(Long serviceRequest_id);

}
