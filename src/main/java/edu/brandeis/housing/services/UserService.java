package edu.brandeis.housing.services;

import edu.brandeis.housing.models.User;
import edu.brandeis.housing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User saveUser(User user) {
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash())); //kind of a misnomer
        return userRepository.save(user);
    }
}
