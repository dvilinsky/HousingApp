package edu.brandeis.housing.controllers;

import edu.brandeis.housing.models.Apartment;
import edu.brandeis.housing.models.Rating;
import edu.brandeis.housing.models.User;
import edu.brandeis.housing.repository.ApartmentRepository;
import edu.brandeis.housing.repository.RatingRepository;
import edu.brandeis.housing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApartmentsController {
    private ApartmentRepository apartmentRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository; //ugh so much coupling....

    @Autowired
    public ApartmentsController(ApartmentRepository apartmentRepository, UserRepository userRepository, RatingRepository
                                ratingRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    @PostMapping(value = "/apartments", produces = "application/json")
    public ResponseEntity<Apartment> addApartment(@RequestBody Apartment newApartment, @RequestParam String landlordId) {
        //This is entirely dependent on the client to send in the JSON correctly. Oh well,
        //deadlines are looming
        User landlord = this.userRepository.findOne(Integer.parseInt(landlordId));
        if (landlord == null) {
            return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
        }
        newApartment.setLandlord(landlord);
        Apartment a = this.apartmentRepository.save(newApartment);
        landlord.addApartment(a);
        return new ResponseEntity<Apartment>(a, HttpStatus.OK);
    }

    @GetMapping(value = "/apartments/{apartmentId}", produces = "application/json")
    public ResponseEntity<Apartment> getApartment(@PathVariable String apartmentId) {
        Apartment a = this.apartmentRepository.findOne(Integer.parseInt(apartmentId));
        return a != null ? new ResponseEntity<Apartment>(a, HttpStatus.OK) : new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "apartments/{apartmentId}/ratings", produces = "application/json")
    public ResponseEntity<Apartment> rateApartment(@PathVariable String apartmentId, @RequestBody Rating rating,
                                                   @RequestParam String writerId) {
        Apartment a = this.apartmentRepository.findOne(Integer.parseInt(apartmentId));
        if (a == null) {
            return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
        }
        User writer = this.userRepository.findOne(Integer.parseInt(writerId));
        rating.setWriter(writer);
        rating.setRatingFor(a);
        this.ratingRepository.save(rating);
        a = this.apartmentRepository.save(a);
        return new ResponseEntity<Apartment>(a, HttpStatus.OK);
    }

    public ResponseEntity<List<Apartment>> search(@RequestParam String address) {
        //Possible sql injecton? who cares?? lol
        if (address.isEmpty()) {
            return new ResponseEntity<List<Apartment>>(HttpStatus.BAD_REQUEST);
        }
        List<Apartment> results = this.apartmentRepository.findApartmentByAddress(address);
        return new ResponseEntity<List<Apartment>>(results, HttpStatus.OK);
    }
}
