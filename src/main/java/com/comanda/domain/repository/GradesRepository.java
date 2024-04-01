package com.comanda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comanda.domain.entity.Grade;
@Repository
public interface GradesRepository extends JpaRepository<Grade, Long> {

}
