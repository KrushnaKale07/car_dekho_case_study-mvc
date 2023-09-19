package com.kkrushnas.cardekho_case_study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkrushnas.cardekho_case_study.pojo.CarPOJO;
import com.kkrushnas.cardekho_case_study.repository.CarRepository;

public class CarService {

	@Autowired
	private CarRepository repository;

	public List<CarPOJO> findAllCars() {
		List<CarPOJO> cars = repository.findAllCars();
		return cars;
	}

	public CarPOJO addCar(String name, String brand, String fuel_type, double price) {
		CarPOJO pojo = repository.addCar(name, brand, fuel_type, price);
		return pojo;
	}

	public CarPOJO searchCar(int id) {
		CarPOJO pojo = repository.searchCar(id);
		return pojo;
	}

	public CarPOJO removeCar(int id) {
		CarPOJO pojo = repository.removeCar(id);
		return pojo;
	}

	public CarPOJO updateCar(int id, String name, String brand, String fuel_type, double price) {
		CarPOJO pojo = repository.updateCar(id, name, brand, fuel_type, price);
		return pojo;
	}

}
