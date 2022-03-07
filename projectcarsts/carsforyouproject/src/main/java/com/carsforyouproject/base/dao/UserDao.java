package com.carsforyouproject.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carsforyouproject.base.dto.CarDetails;
@Repository
public interface UserDao extends JpaRepository<CarDetails, Integer>{
 
	public CarDetails findByCarId(Integer carId); 
	public CarDetails findBycarName(String carName);
	public CarDetails findByCarCompany(String carCompany);

	

	
}
