package com.odazie.imagerepository.business.service;

import com.odazie.imagerepository.data.entity.User;
import com.odazie.imagerepository.data.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }



    public User findUserByEmail(String email){
        return getUserRepository().findUserByEmail(email);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }


    public void saveUser(User user) {
        getUserRepository().save(user);

    }
}
