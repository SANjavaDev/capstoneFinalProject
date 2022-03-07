package com.carsforyouproject.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carsforyouproject.base.dto.AdminDetails;
import com.carsforyouproject.base.dto.CarDetails;
import com.carsforyouproject.base.model.AdminRequest;
import com.carsforyouproject.base.model.AdminResponse;
import com.carsforyouproject.base.service.AdminServiceImplimentation;
import com.carsforyouproject.base.service.UserService;
import com.carsforyouproject.base.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class SuperAdminController {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
    private UserService service;
	

	@GetMapping("/getall")
	public List<CarDetails> getAllCarDetails(CarDetails carDetails) {
		List<CarDetails> details = service.getAllCarDetails();
		return details;
	}
	
	}
	



