package com.vikash.instBackend.repo;

import com.vikash.instBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findByUserEmail(String newEmail);
}
