package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.ServiceRequestCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ServiceRequestGetDto;
import ilknurdogan.servicefinder.entities.Customer;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.entities.ServiceRequest;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.CustomerRepository;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import ilknurdogan.servicefinder.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                    .status("pending ")
                    .phoneNumber(optionalCustomer.get().getPhoneNumber())
                    .email(optionalCustomer.get().getEmail()).build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }


    }

    // GET BY ID
    public ServiceRequestGetDto getById(Long customerId) {
        try {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(customerId);

        if(optionalServiceRequest.isEmpty()){
            throw new NotFoundException("Service request not found!");
        }
            ServiceRequest serviceRequest = optionalServiceRequest.get();
            Long id = serviceRequest.getId();
            String jobDescription = serviceRequest.getJobDescription();
            String urgency = serviceRequest.getUrgency();
            String status = serviceRequest.getStatus();
            String customerName = serviceRequest.getCustomer().getName();
            String serviceProviderName = serviceRequest.getServiceProvider().getName();
            String address = serviceRequest.getAddress();
            String phoneNumber = serviceRequest.getPhoneNumber();
            String email = serviceRequest.getEmail();



            return ServiceRequestGetDto.builder().id(id)
                    .jobDescription(jobDescription)
                    .urgency(urgency)
                    .status(status)
                    .customerName(customerName)
                    .serviceProviderName(serviceProviderName)
                    .address(address)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .build();



        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }

    }
}
