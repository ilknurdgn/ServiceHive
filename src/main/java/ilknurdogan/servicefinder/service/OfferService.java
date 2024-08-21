package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.domain.OfferStatus;
import ilknurdogan.servicefinder.dto.requestDto.OfferCreateDto;
import ilknurdogan.servicefinder.entities.Offer;
import ilknurdogan.servicefinder.entities.ServiceRequest;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.OfferRepository;
import ilknurdogan.servicefinder.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final OfferRepository offerRepository;

    public void createOffer(OfferCreateDto offerCreateDto) {
        try{
            LocalDate offerStartDate = offerCreateDto.getOfferStartDate();
            LocalDate offerEndDate = offerCreateDto.getOfferEndDate();
            Double price = offerCreateDto.getPrice();
            String offerDetails = offerCreateDto.getOfferDetails();
            LocalDate createdDate = LocalDate.now();

            Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(offerCreateDto.getServiceRequestId());
            if(optionalServiceRequest.isEmpty()){
                throw new NotFoundException("Service request not found!");
            }

            Offer offer = Offer.builder()
                    .offerStartDate(offerStartDate)
                    .offerEndDate(offerEndDate)
                    .price(price)
                    .offerDetails(offerDetails)
                    .createdDate(createdDate)
                    .offerStatus(OfferStatus.PENDING)
                    .serviceRequest(optionalServiceRequest.get())
                    .build();

            offerRepository.save(offer);
        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }

    }
}
