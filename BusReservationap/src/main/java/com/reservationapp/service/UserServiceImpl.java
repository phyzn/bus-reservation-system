package com.reservationapp.service;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.payload.UserRegistrationDto;
import com.reservationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public UserRegistrationDto createUser(UserRegistration userRegistration){
        userRepository.save(userRegistration);
        return null;
    }

    public UserRegistration getUserbyId(long id) {
        return userRepository.findById(id).get();
    }
}
