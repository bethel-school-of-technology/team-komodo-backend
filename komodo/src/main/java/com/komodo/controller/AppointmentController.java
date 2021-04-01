package com.komodo.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import com.komodo.model.Appointment;
import com.komodo.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class AppointmentController {
    @Autowired
	private AppointmentRepository appointmentRepository;
	
    public AppointmentController(AppointmentRepository appointmentRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
	}
	
	@GetMapping("/appointments")
	Collection<Appointment> appointments(Principal principal){
		System.out.println(principal.getName());
		return appointmentRepository.findAll();
	}
	@GetMapping("/appointment/{id}")
	ResponseEntity<?> getAppointment(@PathVariable Long id){
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		
		return appointment.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping("/appointment")
	ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) throws URISyntaxException{
		System.out.print("Im Posting !!!!!!!!!!!");
		// appointment.setCreatedDate();
		Appointment result = appointmentRepository.save(appointment);
		
		return ResponseEntity.created(new URI("/api/appointment" + result.getId())).body(result);
	}
	@PutMapping("/appointment/{id}")
	ResponseEntity<?> updateAppointment(@Valid @RequestBody Appointment appointment,@PathVariable(value="id") Long id){
		System.out.print("Im updating !!!!!!!!!!!");
		Appointment putAppointment = appointmentRepository.findById(id).orElse(null);
		if (putAppointment == null) {
			return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
		}else {
			// appointment.setCreatedDate();
			appointmentRepository.save(appointment);
		}
				
		return ResponseEntity.ok(putAppointment);
	}
	
	@DeleteMapping("/appointment/{id}")
	ResponseEntity<?> deleteAppointment(@PathVariable Long id){
		appointmentRepository.deleteById(id);
		
		return ResponseEntity.ok("ok");
	}
}
