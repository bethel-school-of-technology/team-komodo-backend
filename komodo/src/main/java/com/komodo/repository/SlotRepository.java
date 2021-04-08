package com.komodo.repository;

import java.util.Date;

import com.komodo.model.Slot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot,Integer> {
    Slot findById(Long id);
    Slot findByDay(Date date);
    
    
    // Slot findBySlot_3AndDay(Long slot_3, Date date);
    // Slot findBySlot_2AndDay(Long slot_2, Date date);
    // Slot findBySlot_1AndDay(Long slot_1, Date date);
}
