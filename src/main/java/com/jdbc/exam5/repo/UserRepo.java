package com.jdbc.exam5.repo;

import com.jdbc.exam5.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
