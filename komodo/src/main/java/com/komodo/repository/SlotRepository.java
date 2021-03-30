package com.komodo.repository;

import com.komodo.model.Slot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot,Integer> {
    Slot findAllBySomeIdAsMap(Integer foo);
}
