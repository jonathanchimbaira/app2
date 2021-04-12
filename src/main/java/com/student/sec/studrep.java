package com.student.sec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studrep extends JpaRepository<student,Long> {
}