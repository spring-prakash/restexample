package com.prakash.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakash.model.User;

@Repository
public interface UserRepositoryService extends JpaRepository<User, Integer> {

}
