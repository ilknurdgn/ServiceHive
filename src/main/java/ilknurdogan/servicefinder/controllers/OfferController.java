package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.OfferCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.OfferGetDto;
import ilknurdogan.servicefinder.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
@Validated
public class OfferController {

    private final OfferService offerService;

    // CREATE OFFER
    @PostMapping("/create")
    public ResponseEntity<String> createOffer(@RequestBody @Valid OfferCreateDto offerCreateDto){
        offerService.createOffer(offerCreateDto);
        return new ResponseEntity<>("The offer was successfully created.", HttpStatus.CREATED);
    }

    // APPROVE OFFER
    @PutMapping("/approve")
    public ResponseEntity<String> approveOffer(@RequestParam Long offerId){
        offerService.approveOffer(offerId);
        return new ResponseEntity<>("Offer approved.", HttpStatus.OK);
    }

    // REJECT OFFER
    @PutMapping("/reject")
    public ResponseEntity<String> rejectOffer(@RequestParam Long offerId){
        offerService.rejectOffer(offerId);
        return new ResponseEntity<>("Offer rejected", HttpStatus.OK);
    }

    // GET BY ID
    @GetMapping("/getById")
    public ResponseEntity<OfferGetDto> getById (@RequestParam Long offerId){
        OfferGetDto offerGetDto = offerService.getById(offerId);
        return new ResponseEntity<>(offerGetDto, HttpStatus.OK);
    }

    // GET OFFER BY SERVICE REQUEST ID
    @GetMapping("/getOffersByServiceRequestId")
    public ResponseEntity<List<OfferGetDto>> getOffersByServiceRequestId (@RequestParam Long serviceRequestId){
        List<OfferGetDto> offersGetDto = offerService.getOffersByServiceRequestId(serviceRequestId);
        return new ResponseEntity<>(offersGetDto, HttpStatus.OK);

    }
}
