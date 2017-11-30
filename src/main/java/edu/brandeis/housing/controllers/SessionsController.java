package edu.brandeis.housing.controllers;

import edu.brandeis.housing.models.User;
import edu.brandeis.housing.repository.UserRepository;
import edu.brandeis.housing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionsController {
    private UserRepository userRepository;

    @Autowired
    public SessionsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        //Sql injection from query params? Who cares? Someone using curl to fuck with the db? Also who cares
        //Security? Lol what's that
        User exists = this.userRepository.findByUserName(username);
        if (exists == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        if (BCrypt.checkpw(password, exists.getPasswordHash())) {
            return new ResponseEntity<User>(exists, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }
}
