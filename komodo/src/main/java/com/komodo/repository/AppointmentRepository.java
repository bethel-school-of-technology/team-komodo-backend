package com.komodo.repository;

import java.util.List;
import java.util.Optional;

import com.komodo.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findById(Long id);
}
