package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.domain.OfferStatus;
import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import ilknurdogan.servicefinder.dto.requestDto.OfferCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.OfferGetDto;
import ilknurdogan.servicefinder.entities.Offer;
import ilknurdogan.servicefinder.entities.ServiceRequest;
import ilknurdogan.servicefinder.exception.BadRequestException;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.OfferRepository;
import ilknurdogan.servicefinder.repository.ServiceRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    // CREATE OFFER
    @Transactional
    public void createOffer(OfferCreateDto offerCreateDto) {

            LocalDate offerStartDate = offerCreateDto.getOfferStartDate();
            LocalDate offerEndDate = offerCreateDto.getOfferEndDate();

            if(offerStartDate.isAfter(offerEndDate)){
                throw new BadRequestException("Start date cannot be after end date!");
            }

            if(offerCreateDto.getPrice()< 0){
                throw new BadRequestException("A negative price cannot be entered.");
            }

            Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(offerCreateDto.getServiceRequestId());
            if (optionalServiceRequest.isEmpty()) {
                throw new NotFoundException("Service request not found!");
            }

            Optional<Offer> offerOptional = offerRepository.findByServiceRequestId(offerCreateDto.getServiceRequestId());
            if (offerOptional.isPresent()){
                throw new BadRequestException("This service request already has an offer!");
            }

            Offer offer = Offer.builder()
                    .offerStartDate(offerStartDate)
                    .offerEndDate(offerEndDate)
                    .price(offerCreateDto.getPrice())
                    .offerDetails(offerCreateDto.getOfferDetails())
                    .createdDate(LocalDate.now())
                    .offerStatus(OfferStatus.PENDING)
                    .serviceRequest(optionalServiceRequest.get())
                    .build();

            offerRepository.save(offer);

    }

    // APPROVE OFFER
    @Transactional
    public void approveOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new NotFoundException("Offer not found!"));

        if (!offer.getOfferStatus().equals(OfferStatus.PENDING)) {
            throw new BadRequestException("Only pending offers can be approved!");
        }

            offer.setOfferStatus(OfferStatus.APPROVED);
            offerRepository.save(offer);

            ServiceRequest serviceRequest = offer.getServiceRequest();
            serviceRequest.setStatus(ServiceRequestStatus.APPROVED);
            serviceRequestRepository.save(serviceRequest);

    }

    // REJECT OFFER
    @Transactional
    public void rejectOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new NotFoundException("Offer not found"));

        if(offer.getOfferStatus().equals(OfferStatus.REJECTED)){
            throw new BadRequestException("This offer has already been rejected and cannot be rejected again.");
        }

        ServiceRequest serviceRequest = offer.getServiceRequest();
        if (serviceRequest.getStatus().equals(ServiceRequestStatus.REJECTED)) {
            throw new BadRequestException("This service request has already been rejected.");
        }

            offer.setOfferStatus(OfferStatus.REJECTED);
            offerRepository.save(offer);


            serviceRequest.setStatus(ServiceRequestStatus.REJECTED);
            serviceRequestRepository.save(serviceRequest);

    }


    // GET BY ID
    public OfferGetDto getById(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(()-> new NotFoundException("Offer not found!"));

        try {
            return modelMapper.map(offer, OfferGetDto.class);
        }catch (Exception e){
            throw new InternalServerErrorException("Failed to retrieve according to ID", e);
        }


    }

    public List<OfferGetDto> getOffersByServiceRequestId(Long serviceRequestId) {
        Optional<Offer> offerList = offerRepository.findByServiceRequestId(serviceRequestId);

        if(offerList.isEmpty()){
            throw new NotFoundException("No offers found for this service request");
        }

        return offerList.stream()
                .map(offer -> modelMapper.map(offer, OfferGetDto.class))
                .collect(Collectors.toList());

    }
}