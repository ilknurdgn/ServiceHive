package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import ilknurdogan.servicefinder.dto.requestDto.ServiceRequestCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ServiceRequestGetDto;
import ilknurdogan.servicefinder.entities.Customer;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.entities.ServiceRequest;
import ilknurdogan.servicefinder.exception.BadRequestException;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.CustomerRepository;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import ilknurdogan.servicefinder.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final CustomerRepository customerRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final ModelMapper modelMapper;


    //CREATE
    public void createServiceRequest(ServiceRequestCreateDto serviceRequestCreateDto) {
        ServiceRequest serviceRequest = getServiceRequest(serviceRequestCreateDto);
        try {
            serviceRequestRepository.save(serviceRequest);
        } catch (Exception e) {
            throw new InternalServerErrorException("Service request could not be created!", e);
        }
    }

    private ServiceRequest getServiceRequest(ServiceRequestCreateDto serviceRequestCreateDto) {
        try {
            Long customerId = serviceRequestCreateDto.getCustomerId();
            Long serviceProviderId = serviceRequestCreateDto.getServiceProviderId();
            String jobDescription = serviceRequestCreateDto.getJobDescription();
            String urgency = serviceRequestCreateDto.getUrgency();
            String address = serviceRequestCreateDto.getAddress();

            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCustomer.isEmpty()) {
                throw new NotFoundException("Customer not found!");
            }

            Optional<ServiceProvider> optionalServiceProvider = serviceProviderRepository.findById(serviceProviderId);
            if (optionalServiceProvider.isEmpty()) {
                throw new NotFoundException("Service provider not found");
            }

            return ServiceRequest.builder()
                    .customer(optionalCustomer.get()).serviceProvider(optionalServiceProvider.get())
                    .jobDescription(jobDescription)
                    .urgency(urgency)
                    .address(address)
                    .status(ServiceRequestStatus.PENDING)
                    .phoneNumber(optionalCustomer.get().getPhoneNumber())
                    .email(optionalCustomer.get().getEmail()).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }


    }

    // GET BY ID
    public ServiceRequestGetDto getById(Long id) {
        try {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(id);

        if(optionalServiceRequest.isEmpty()){
            throw new NotFoundException("Service request not found!");
        }

            return convertToDto(optionalServiceRequest.get());

        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }

    }


    // GET ALL BY SERVICE PROVIDER ID
    public List<ServiceRequestGetDto> getAllByServiceProviderId(Long serviceProviderId) {
        Optional<ServiceProvider> optionalServiceProvider = serviceProviderRepository.findById(serviceProviderId);
        if (optionalServiceProvider.isEmpty()) {
            throw new NotFoundException("Service provider not found!");
        }
        List<ServiceRequest> serviceRequestList = serviceRequestRepository.findAllByServiceProvider_Id(serviceProviderId);

        return serviceRequestList.stream()
                .filter(serviceRequest -> !serviceRequest.getStatus().equals(ServiceRequestStatus.CANCELLED))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    // GET ALL BY CUSTOMER ID
    public List<ServiceRequestGetDto> getAllByCustomerId(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer not found!");
        }
        List<ServiceRequest> serviceRequestList = serviceRequestRepository.findAllByCustomer_Id(customerId);

        return serviceRequestList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public ServiceRequestGetDto convertToDto(ServiceRequest serviceRequest) {
        try {
            ServiceRequestGetDto dto = new ServiceRequestGetDto();
            dto.setId(serviceRequest.getId());
            dto.setJobDescription(serviceRequest.getJobDescription());
            dto.setUrgency(serviceRequest.getUrgency());
            dto.setStatus(serviceRequest.getStatus());
            dto.setCustomerName(serviceRequest.getCustomer().getName());
            dto.setServiceProviderName(serviceRequest.getServiceProvider().getName());
            dto.setAddress(serviceRequest.getAddress());
            dto.setPhoneNumber(serviceRequest.getPhoneNumber());
            dto.setEmail(serviceRequest.getEmail());
            dto.setOffer(serviceRequest.getOfferList());

            return dto;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }


    // CANCEL SERVICE REQUEST
    public void cancelServiceRequest(Long id) {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(id);

        if (optionalServiceRequest.isEmpty()){
            throw new NotFoundException("Service request not found!");
        }
            ServiceRequest serviceRequest = optionalServiceRequest.get();

            if(ServiceRequestStatus.CANCELLED.equals(serviceRequest.getStatus())){
                throw new BadRequestException("The service request has already been cancelled. Cannot process the cancellation request.");
            }
            try {

                serviceRequest.setStatus(ServiceRequestStatus.CANCELLED);
                serviceRequestRepository.save(serviceRequest);

            } catch (Exception e) {
                throw new InternalServerErrorException("Service request could not be canceled", e);
            }
    }


    // DELETE
    public void deleteServiceRequest(Long id) {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(id);

        if(optionalServiceRequest.isEmpty()){
            throw new NotFoundException("Service request not found!");
        }

        try {
           serviceRequestRepository.deleteById(id);
        }catch (Exception e){
            throw new InternalServerErrorException("Service request could not be deleted!");
        }
    }
}
