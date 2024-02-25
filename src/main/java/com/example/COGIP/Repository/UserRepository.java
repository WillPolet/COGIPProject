package com.example.COGIP.Repository;
import com.example.COGIP.Services.UserService;
import com.example.COGIP.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    void deleteByUsername(String username);

    User findByUsername(String username);

//    boolean existsByusername(String username);

}
