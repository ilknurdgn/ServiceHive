package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findAllByServiceProvider_Id(Long serviceProviderId);
}
