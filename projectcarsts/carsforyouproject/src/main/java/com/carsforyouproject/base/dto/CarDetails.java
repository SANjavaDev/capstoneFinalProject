package com.carsforyouproject.base.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class CarDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carId;
	private String carName;
	private String carCompany;
	private String fuelType;
	private String powerSteering;
	private String breakSystem;
	private Double showroomPrice;
	private Double onroadPrice;
	private Double mileage;
	private Integer seatingCapacity;
	private Integer engineCapacity;
	private String gearType;
	private String imageURL;
	
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name="admin_id",referencedColumnName = "adminId")
	private AdminDetails adminDetails;

	public CarDetails() {
		super();
	}

	
	
	

}
