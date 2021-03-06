package com.carsforyouproject.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carsforyouproject.base.dto.AdminDetails;
import com.carsforyouproject.base.dto.CarDetails;
import com.carsforyouproject.base.model.AdminRequest;
import com.carsforyouproject.base.model.AdminResponse;
import com.carsforyouproject.base.model.CarResponse;
import com.carsforyouproject.base.service.AdminServiceImplimentation;
import com.carsforyouproject.base.service.UserService;
import com.carsforyouproject.base.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AdminServiceImplimentation adminservice;

	@Autowired
	private UserService service;
	
	

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AdminRequest adminRequest) throws Exception {
		try {
			authenticationManager.authenticate(

					new UsernamePasswordAuthenticationToken(adminRequest.getUsername(), adminRequest.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(adminRequest.getUsername());
			String jwt = jwtUtil.generateToken(userDetails);
			
			return ResponseEntity.ok(new AdminResponse("logged in successfully", false, jwt,userDetails.getAuthorities()));
		} catch (Exception e) {
			return ResponseEntity.ok((new AdminResponse("invalid username", true, null,null)));
		}

	}

	@PostMapping("/signUp")
	public ResponseEntity<AdminResponse> signUpDetails(@RequestBody  AdminDetails adminDetails) {
		AdminDetails signUpData = adminservice.signUp(adminDetails);
		if (signUpData != null) {
			return ResponseEntity.created(null).body(new AdminResponse("User created!", false, null,null));
		}
		// return ResponseEntity.ok(new AdminResponse("username already exists", true,
		// null));
		return ResponseEntity.badRequest().body(new AdminResponse("Username already exists!", true, null,null));
	}

	@GetMapping("/carsforyou")
	public ResponseEntity<?> getAllCarDetails() {
		List<CarDetails> carDetails = service.getAllCarDetails();
		if(carDetails != null) {
		
			return  ResponseEntity.ok(new CarResponse(carDetails, null, false));
		} else  {
			
			return ResponseEntity.ok(new CarResponse(null, "data not found", true));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> insertCarDetails(@RequestBody CarDetails carDetails,HttpServletRequest request) {
		try {
			service.insertCarDetails(carDetails,request);
			return ResponseEntity.ok(new CarResponse(null, "data added successfully", false));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new CarResponse(null, "something went wrong", true));
		}
	}

	@PutMapping("/carsforyou/update/{carId}")
	public ResponseEntity<?> updateCarDetails(@RequestBody CarDetails carDetails, @PathVariable Integer carId,HttpServletRequest request) {
		try {
			carDetails.setCarId(carId);
			service.updateCarDetails(carDetails, request);
			return ResponseEntity.ok(new CarResponse(null, "data updated successfully", false));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new CarResponse(null, "something went wrong", true));
		}

	}

	@DeleteMapping("carsforyou/{carId}")
	public ResponseEntity<?> deleteCarDetails(CarDetails carDetails, @PathVariable Integer carId) {
		try {
			service.deleteCarDetails(carDetails, carId);
			return  ResponseEntity.ok(new CarResponse(null, "data deleted successfully", false));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new CarResponse(null, "data not found for id"+carId, true));
		}

	}

}
