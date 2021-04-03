//Here is a comment to test if Hans got the repo cloned properly with java in vscode
//Testing wing's origin

//What Hans added to this came from Exeter Java Front-End Integration Lesson 3
package com.komodo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

// import java.lang.invoke.InjectedProfile;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
    private String password;
	
    //patient or doctor
	private String role;

	// @JsonBackReference
    // @Fetch(FetchMode.JOIN)
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Appointment> appointments;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public List<Appointment> getAppointments() {
	// 	return new ArrayList<Appointment>(appointments);
	// }

	// public void setAppointments(List<Appointment> appointments) {
	// 	this.appointments = appointments;
	// }
	// public void addAppointment(Appointment newappointment) {
	// 	//prevent endless loop
	// 	if (appointments.contains(newappointment))
	// 	  return ;
	// 	//add new account
	// 	appointments.add(newappointment);
	// 	//set myself into the twitter account
	// 	((Appointment) appointments).setUser(this);
	//   }

	// public void removeAppointment(Appointment appointment){
	// 	if(!appointments.contains(appointment)){
	// 		return;
	// 	}
	// 	appointments.remove(appointment);

	// }
	
}

