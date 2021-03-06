package edu.brandeis.housing.controllers;

import edu.brandeis.housing.models.Rating;
import edu.brandeis.housing.models.User;
import edu.brandeis.housing.repository.RatingRepository;
import edu.brandeis.housing.repository.UserRepository;
import edu.brandeis.housing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {
    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private UserService userService;

    @Autowired
    public UsersController(UserRepository userRepository, RatingRepository ratingRepository, UserService userService) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/users/{userId}", produces = "application/json")
    public ResponseEntity<User> getUserProfile(@PathVariable String userId) {
		//TODO: Toggle what is returned based on if it is your profile or not 
        User u = this.userRepository.findOne(Integer.parseInt(userId));
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createAccount(@RequestBody User newUser) {
        User userExists = userRepository.findByUserName(newUser.getUserName());
        if (userExists != null) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT); //user already exists
        }
        User u = userService.saveUser(newUser);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @PostMapping(value = "/users/{userId}/ratings", produces = "application/json")
    public ResponseEntity<User> rateLandlord(@PathVariable String userId, @RequestBody Rating rating, @RequestParam
                                             String writerId) {
        User landlord = this.userRepository.findOne(Integer.parseInt(userId));
        User writer = this.userRepository.findOne(Integer.parseInt(writerId));
        if (landlord == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        if (landlord.getIsTenant()) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST); //can't rate a tenant
        }
        rating.setWriter(writer);
        rating.setAboutWhom(landlord);
        Rating r = this.ratingRepository.save(rating);
        landlord.addRatingAboutMe(rating);
        writer.addRatingIWrote(rating);
        this.userRepository.save(writer);
        landlord = this.userRepository.save(landlord);
        return new ResponseEntity<User>(landlord, HttpStatus.OK);
    }

    @GetMapping(value = "/users/search")
    public ResponseEntity<List<User>> search(@RequestParam String name) {
        List<User> results =  this.userRepository.findUserByName(name);
        return new ResponseEntity<List<User>>(results, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/ratingsWritten")
    public ResponseEntity<List<Rating>> findRatingsUserHasWritten(@PathVariable String userId) {
        List<Rating> results = this.userRepository.findRatingsUserHasWritten(Integer.parseInt(userId));
        return new ResponseEntity<List<Rating>>(results, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/ratingsAbout")
    public ResponseEntity<List<Rating>> findRatingsAboutUser(@PathVariable String userId) {
        List<Rating> results = this.userRepository.findRatingsAboutUser(Integer.parseInt(userId));
        return new ResponseEntity<List<Rating>>(results, HttpStatus.OK);
    }
}
