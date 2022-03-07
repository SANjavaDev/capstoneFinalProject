package com.carsforyouproject.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carsforyouproject.base.dto.AdminDetails;
import com.carsforyouproject.base.dto.CarDetails;

public interface UserService {
	 
	public List<CarDetails>  getAllCarDetails();
	
	public CarDetails insertCarDetails(CarDetails carDetails,HttpServletRequest request); 
	
	public CarDetails updateCarDetails(CarDetails carDetails,HttpServletRequest request);
	
	public void deleteCarDetails(CarDetails carDetails,Integer carId);
	
	public List<CarDetails> getCar(); 
	
	public List<CarDetails> getCarByName(String carName);
	
	public AdminDetails signUp(AdminDetails adminDetails);

	

}
