package com.kkrushnas.cardekho_case_study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkrushnas.cardekho_case_study.pojo.CarPOJO;
import com.kkrushnas.cardekho_case_study.repository.CarRepository;

public class CarService {

	@Autowired
	private CarRepository repository;

	public List<CarPOJO> findALLCars() {
		List<CarPOJO> cars = repository.findAllCars();
		return cars;
	}

	public CarPOJO addCar(String name, String email, long contact, String address) {
		CarPOJO pojo = repository.addCar(name, email, contact, address);
		return pojo;
	}

	public CarPOJO searchCar(int id) {
		CarPOJO pojo = repository.searchCar(id);
		return pojo;
	}

}
