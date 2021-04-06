package com.komodo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.komodo.model.Appointment;
import com.komodo.repository.AppointmentRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppointmentController.class)
@EnableSpringDataWebSupport

public class AppointmentControllerTest {
    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(appointmentController)
                .build();
    }
    @Test
    public void testgetAllAppointmentsByUserId() throws Exception{
        List<Appointment> appont = new ArrayList<>();
        Page<Appointment> appointments = new PageImpl<>(appont);
        Pageable pageable = PageRequest.of(0,1);
        when(appointmentRepository.findByUserId((long)1, pageable)).thenReturn(appointments);
        mockMvc.perform(get("/3/appointments")).andExpect(status().isOk());
        
    }
}