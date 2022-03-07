package com.carsforyouproject.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carsforyouproject.base.dao.AdminDao;
import com.carsforyouproject.base.dao.UserDao;
import com.carsforyouproject.base.dto.AdminDetails;
import com.carsforyouproject.base.dto.CarDetails;
import com.carsforyouproject.base.dto.MyAdminDetails;
import com.carsforyouproject.base.util.JwtUtil;

@Service
public class AdminServiceImplimentation implements UserDetailsService, UserService {
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private UserDao dao;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminDetails adminDetails = (AdminDetails) adminDao.findByUsername(username);
		if (adminDetails != null) {
			return new MyAdminDetails(adminDetails);
		} else {
			throw new UsernameNotFoundException("username not found ");
		}
	}

	public AdminDetails signUp(AdminDetails adminDetails) {
		AdminDetails adminObj = adminDao.findByUsername(adminDetails.getUsername());
		if (adminObj == null)
			return adminDao.save(adminDetails);
		else
			return null;
	}

	@Override
	public List<CarDetails> getAllCarDetails() {
		return dao.findAll();
	}

	@Override
	public CarDetails insertCarDetails(CarDetails carDetails, HttpServletRequest request) {
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		String adminName = jwtUtil.extractUsername(token);
		AdminDetails adminDetails = adminDao.findByUsername(adminName);
		double showroomPrice = (carDetails.getShowroomPrice() * 100000);
		double onRoadPrice = 0;
		carDetails.setShowroomPrice(showroomPrice);

		if (carDetails.getShowroomPrice() < 500000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.13);

		} else if (carDetails.getShowroomPrice() > 500000 && carDetails.getShowroomPrice() < 1000000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.14);
		} else if (carDetails.getShowroomPrice() > 1000000 && carDetails.getShowroomPrice() < 2000000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.17);
		} else if (carDetails.getShowroomPrice() > 2000000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.18);
		}

		carDetails.setOnroadPrice(onRoadPrice);
		carDetails.setAdminDetails(adminDetails);

		return dao.save(carDetails);
	}

	@Override
	public CarDetails updateCarDetails(CarDetails carDetails,HttpServletRequest request) {
		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);
		String adminName = jwtUtil.extractUsername(token);
		AdminDetails adminDetails = adminDao.findByUsername(adminName);
		double showroomPrice = (carDetails.getShowroomPrice() * 100000);
		double onRoadPrice = 0;
		carDetails.setShowroomPrice(showroomPrice);

		if (carDetails.getShowroomPrice() < 500000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.13);

		} else if (carDetails.getShowroomPrice() > 500000 && carDetails.getShowroomPrice() < 1000000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.14);
		} else if (carDetails.getShowroomPrice() > 1000000 && carDetails.getShowroomPrice() < 2000000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.17);
		} else if (carDetails.getShowroomPrice() > 2000000) {
			onRoadPrice = (carDetails.getShowroomPrice() * 1.18);
		}

		carDetails.setOnroadPrice(onRoadPrice);
		carDetails.setAdminDetails(adminDetails);

		return dao.save(carDetails);
	}

	@Override
	public void deleteCarDetails(CarDetails carDetails, Integer carId) {
		dao.delete(carDetails);
	}

	@Override
	public List<CarDetails> getCar() {
		return dao.findAll();
	}

	@Override
	public List<CarDetails> getCarByName(String carName) {
		List<CarDetails> carDList = (List<CarDetails>) dao.findBycarName(carName);
		return carDList;
	}
}
