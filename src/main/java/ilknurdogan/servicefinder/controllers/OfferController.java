package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.OfferCreateDto;
import ilknurdogan.servicefinder.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
