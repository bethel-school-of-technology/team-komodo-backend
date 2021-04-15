package com.komodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.komodo.model.Slot;
import com.komodo.repository.SlotRepository;

@RestController
@RequestMapping("/api")
public class SlotController {
	@Autowired
	private SlotRepository slotRepository;
	
	@GetMapping("/slots")
	List<Slot> getAllslotRepository(){
		
		return slotRepository.findAll();
		 
	}
}
