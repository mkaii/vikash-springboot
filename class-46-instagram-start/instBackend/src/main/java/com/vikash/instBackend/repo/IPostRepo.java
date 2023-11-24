package com.vikash.instBackend.repo;

import com.vikash.instBackend.model.Post;
import com.vikash.instBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends  JpaRepository<Post,Integer> {
}
