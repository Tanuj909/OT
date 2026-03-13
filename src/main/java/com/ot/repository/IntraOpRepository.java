package com.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ot.entity.IntraOpRecord;

public interface IntraOpRepository extends JpaRepository<IntraOpRecord, Long>{

}
