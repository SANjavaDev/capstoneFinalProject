package com.carsforyouproject.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carsforyouproject.base.dto.AdminDetails;

public interface AdminDao extends JpaRepository<AdminDetails, Integer> {

	public AdminDetails findByAdminId(Integer adminId); 
	
	public AdminDetails findByUsername(String username);

}
