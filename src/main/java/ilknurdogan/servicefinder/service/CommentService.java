package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import ilknurdogan.servicefinder.dto.requestDto.CommentCreateDto;
import ilknurdogan.servicefinder.entities.Comment;
import ilknurdogan.servicefinder.entities.Customer;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.entities.ServiceRequest;
import ilknurdogan.servicefinder.exception.BadRequestException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.CommentRepository;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import ilknurdogan.servicefinder.repository.ServiceRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final CommentRepository commentRepository;
    private final ServiceRequestRepository serviceRequestRepository;

    @Transactional
    public void createComment(CommentCreateDto commentCreateDto) {
        ServiceRequest serviceRequest =serviceRequestRepository.findById(commentCreateDto.getServiceRequestId())
                .orElseThrow(()-> new NotFoundException("Service request not found!"));

        if(serviceRequest.getStatus() != ServiceRequestStatus.APPROVED){
            throw new BadRequestException("The service request must be approved in order to comment.");
        }


            ServiceProvider serviceProvider = serviceRequest.getServiceProvider();
            Customer customer = serviceRequest.getCustomer();

            Comment comment = Comment.builder()
                    .text(commentCreateDto.getText())
                    .score(commentCreateDto.getScore())
                    .createdDate(LocalDate.now())
                    .serviceProvider(serviceProvider)
                    .customer(customer)
                    .serviceRequest(serviceRequest)
                    .build();
            commentRepository.save(comment);

            serviceProvider.updateAverageScore(commentCreateDto.getScore());
            serviceProviderRepository.save(serviceProvider);

    }
}
