package com.example.COGIP.Services;
import com.example.COGIP.Repository.UserRepository;
import com.example.COGIP.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean verifyIfUsernameExist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}

