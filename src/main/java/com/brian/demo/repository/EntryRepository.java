package com.brian.demo.repository;

import com.brian.demo.domain.Entry;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Entry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
