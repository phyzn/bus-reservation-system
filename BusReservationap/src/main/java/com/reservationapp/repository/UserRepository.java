package com.reservationapp.repository;

import com.reservationapp.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserRegistration,Long> {

}
