package com.komodo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.komodo.model.Appointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Page<Appointment> findByUserId(Long userId, Pageable pageable);
    Optional<Appointment> findByIdAndUserId(Long id, Long userId);

}
