package com.brian.demo.repository;



import java.util.Optional;
import java.util.UUID;

import com.brian.demo.domain.Group;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Group entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    void deleteById(UUID id);

    Optional<Group> findById(UUID id);
}
