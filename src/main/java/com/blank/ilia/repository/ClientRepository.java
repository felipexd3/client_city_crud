package com.blank.ilia.repository;

import com.blank.ilia.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByNameContainingAndRemovedAtIsNull(String name);
    Optional<Client> findByIdAndRemovedAtIsNull(Long id);
}
