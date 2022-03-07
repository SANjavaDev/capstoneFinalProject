package com.carsforyouproject.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carsforyouproject.base.dto.AdminDetails;

public interface SuperAdminDao extends JpaRepository<AdminDetails, Integer> {
	public AdminDetails findByusername(String username);
}
