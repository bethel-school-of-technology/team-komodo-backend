package com.komodo.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.komodo.model.Appointment;
import com.komodo.model.Slot;
import com.komodo.repository.AppointmentRepository;
import com.komodo.repository.SlotRepository;
import com.komodo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class AppointmentController {
    @Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private UserRepository userRepository;

    // public AppointmentController(AppointmentRepository appointmentRepository, UserRepository userRepository) {
	// 	super();
	// 	this.appointmentRepository = appointmentRepository;
	// 	this.userRepository = userRepository;
	// }
	
	@GetMapping("/{userId}/appointments")
	Page<Appointment> getAllAppointmentsByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable){
		
		return appointmentRepository.findByUserId(userId,pageable);
		
		 
	}
	@GetMapping("/{userId}/appointment/{appointmentId}")
	ResponseEntity<?> getAppointment(@PathVariable (value = "userId") Long userId, @PathVariable (value = "appointmentId") Long appointmentId){
		Optional<Appointment> appointment = appointmentRepository.findByIdAndUserId(appointmentId, userId);
		if(!userRepository.existsById(userId)){
			return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
		}
		return appointment.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping("/{userId}/appointment")
	ResponseEntity<?> createAppointment(@Valid @RequestBody Appointment appointment, @PathVariable (value = "userId") Long userId ) throws URISyntaxException{
		System.out.print("Im Posting !!!!!!!!!!!");
		// appointment.setCreatedDate();
		userRepository.findById(userId).map(user -> {
			appointment.setUser(user);
			return appointmentRepository.save(appointment);
		});
		Date date = appointment.getDate();
		System.out.println(date);
		System.out.println(slotRepository.findByDay(date));
		Slot slot = new Slot();
		
		if (slotRepository.findByDay(date) == null){
			slot.setDay(date);
			if(appointment.getSlot() == 1) slot.setSlot_1(appointment.getId());
			else if(appointment.getSlot() == 2) slot.setSlot_2(appointment.getId());
			else if(appointment.getSlot() == 3) slot.setSlot_3(appointment.getId());
			slotRepository.save(slot);
		}else{
			slot = slotRepository.findByDay(date);
			if(appointment.getSlot() == 1 && slot.getSlot_1() == null){
				slot.setSlot_1(appointment.getId());
			}else if(appointment.getSlot() == 2 && slot.getSlot_2() == null){
				slot.setSlot_2(appointment.getId());
			}else if(appointment.getSlot() == 3 && slot.getSlot_3() == null){
				slot.setSlot_3(appointment.getId());
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message","Slot is occupied").body("Occupied lol");
			}
			slotRepository.save(slot);
		}
		return ResponseEntity.created(new URI("/api/appointment" + appointment.getId())).body(appointment.getId());
	}
	// @PutMapping("/{userId}/appointment/{appointmentId}")
	// ResponseEntity<?> updateAppointment(@Valid @RequestBody Appointment appointment,@PathVariable(value="userId") Long userId, @PathVariable(value="appointmentId") Long appointmentId){
	// 	System.out.print("Im updating !!!!!!!!!!!");
	// 	if(!appointmentRepository.existsById(appointmentId)){
	// 		return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
	// 	}
	// 	appointmentRepository.findById(appointmentId).map(appointment ->{
	// 		appointment.set
	// 	})
	// 	// Appointment putAppointment = appointmentRepository.findById(id).orElse(null);
	// 	// if (putAppointment == null) {
	// 	// 	return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
	// 	// }else {
	// 	// 	// appointment.setCreatedDate();
	// 	// 	appointmentRepository.save(appointment);
	// 	// }
				
	// 	// return ResponseEntity.ok(putAppointment);
	// }
	
	@DeleteMapping("/{userId}/appointment/{appointmentId}")
	ResponseEntity<?> deleteAppointment(@PathVariable(value="userId") Long userId, @PathVariable(value="appointmentId") Long appointmentId){
		return appointmentRepository.findByIdAndUserId(appointmentId, userId).map(appointment -> {
			appointmentRepository.delete(appointment);
			return ResponseEntity.ok().build();
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
