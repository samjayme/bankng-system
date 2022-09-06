package com.samueljavaspringboot.bankng_system.repos;

import com.samueljavaspringboot.bankng_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findUserByUserName(String userName);
}