package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.domain.ServiceRequestStatus;
import ilknurdogan.servicefinder.dto.requestDto.CommentCreateDto;
import ilknurdogan.servicefinder.dto.requestDto.CommentUpdateDto;
import ilknurdogan.servicefinder.dto.responseDto.CommentGetDto;
import ilknurdogan.servicefinder.entity.Comment;
import ilknurdogan.servicefinder.entity.Customer;
import ilknurdogan.servicefinder.entity.ServiceProvider;
import ilknurdogan.servicefinder.entity.ServiceRequest;
import ilknurdogan.servicefinder.exception.BadRequestException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.CommentRepository;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import ilknurdogan.servicefinder.repository.ServiceRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final CommentRepository commentRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final ServiceProviderService serviceProviderService;

    // CREATE
    @Transactional
    public void createComment(CommentCreateDto commentCreateDto) {
        ServiceRequest serviceRequest =serviceRequestRepository.findById(commentCreateDto.getServiceRequestId())
                .orElseThrow(()-> new NotFoundException("Service request not found!"));

        boolean commentExists = commentRepository.existsByServiceRequestId(serviceRequest.getId());
        if (commentExists) {
            throw new BadRequestException("Only one comment can be made on a service request.");
        }

        if(serviceRequest.getStatus() != ServiceRequestStatus.APPROVED){
            throw new BadRequestException("The service request must be approved in order to comment.");
        }


            ServiceProvider serviceProvider = serviceRequest.getServiceProvider();
            Customer customer = serviceRequest.getCustomer();


        if (customer == null) {
            throw new NotFoundException("Customer not found for this service request!");
        }

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

    // GET BY SERVICE PROVIDER ID
    public List<CommentGetDto> getByServiceProviderId(Long serviceProviderId) {
        List<Comment> commentList = commentRepository.findAllByServiceProvider_Id(serviceProviderId);

        return  commentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public CommentGetDto convertToDto(Comment comment){
        CommentGetDto dto = new CommentGetDto();

        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setScore(comment.getScore());
        dto.setCustomerName(comment.getCustomer().getName());
        dto.setCustomerProfileImgUrl(comment.getCustomer().getProfileImgUrl());
        dto.setCreatedDate(comment.getCreatedDate());

        return dto;

    }


    // UPDATE COMMENT
    @Transactional
    public void updateComment(Long id, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Comment not found!"));

        double oldScore = comment.getScore();

        if (commentUpdateDto.getScore() != null) {
            comment.setScore(commentUpdateDto.getScore());
        }

        if (commentUpdateDto.getText() != null) {
            comment.setText(commentUpdateDto.getText());
        }

        Comment updatedComment = commentRepository.save(comment);

        if (commentUpdateDto.getScore() != null) {
            serviceProviderService.updateAverageScoreAfterCommentUpdate(
                    comment.getServiceProvider().getId(),
                    oldScore,
                    updatedComment.getScore()
            );
        }


    }

    // DELETE
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Comment not found!"));

        Long serviceProviderId = comment.getServiceProvider().getId();
        Integer scoreToRemove = comment.getScore();

        commentRepository.delete(comment);

        // Update service provider's average score after comment deletion
        serviceProviderService.updateAverageScoreAfterCommentDelete(serviceProviderId, scoreToRemove);
    }
}
