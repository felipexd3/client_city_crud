package com.blank.ilia.repository;

import com.blank.ilia.model.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUsernameAndActiveIsTrue(String user);
}
