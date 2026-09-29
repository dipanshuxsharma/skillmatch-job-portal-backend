package com.skillmatch.repository;

import com.skillmatch.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );
}