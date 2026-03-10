package com.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ot.entity.ScheduledOperation;

@Repository
public interface ScheduledOperationRepository extends JpaRepository<ScheduledOperation, Long> {
}
