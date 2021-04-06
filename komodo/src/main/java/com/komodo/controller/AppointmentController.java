package com.komodo.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import com.komodo.model.Appointment;
import com.komodo.repository.AppointmentRepository;
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
