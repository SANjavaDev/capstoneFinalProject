package com.carsforyouproject.base.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carsforyouproject.base.dto.CarDetails;
import com.carsforyouproject.base.model.CarResponse;
import com.carsforyouproject.base.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	private UserService service;
	
	@GetMapping("/getbyname/{carName}")
	  public ResponseEntity<?> getCarByName(@PathVariable String carName){
   	   
   	   try {
		List<CarDetails> carList=	service.getCarByName(carName);
			return ResponseEntity.ok(new CarResponse(carList,"null",false));
		} catch (Exception e) {
			return ResponseEntity.ok(new CarResponse(null, "something went wronhg", true));
		}
   	   
      }

}
