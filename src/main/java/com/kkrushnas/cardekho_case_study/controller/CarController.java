package com.kkrushnas.cardekho_case_study.controller;

//import java.awt.font.TextLayout.CaretPolicy;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import com.kkrushnas.cardekho_case_study.pojo.AdminPOJO;
import com.kkrushnas.cardekho_case_study.pojo.CarPOJO;
import com.kkrushnas.cardekho_case_study.service.CarService;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Stubbing;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class CarController {

	@Autowired
	private CarService service;

	// Home page controller
	@GetMapping("/home")
	public String home(@SessionAttribute(name = "login", required = false) AdminPOJO admin, ModelMap map) {
		if (admin != null) {
			return "Home";
		}
		map.addAttribute("msg", "Session inactive. login to proceed");
		return "login";
	}

	// Add page controller
	@GetMapping("/add")
	public String addPage(@SessionAttribute(name = "login", required = false) AdminPOJO admin, ModelMap map) {
		if (admin != null) {
			List<CarPOJO> cars = service.findAllCars();
			if (!cars.isEmpty()) {
				map.addAttribute("cars", cars);
				return "Add";
			}
			return "Add";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed...!");
		return "login";
	}

	// Add car Controller
	@PostMapping("/add")
	public String addStudent(@SessionAttribute(name = "login", required = false) AdminPOJO admin,
			@RequestParam String name, @RequestParam String brand, @RequestParam String fuel_type,
			@RequestParam double price, ModelMap map) {
		if (admin != null) {
			CarPOJO pojo = service.addCar(name, brand, fuel_type, price);

			// Success
			if (pojo != null) {
				map.addAttribute("msg", "Data inserted successfully");
				List<CarPOJO> cars = service.findAllCars();
				map.addAttribute("cars", cars);
				return "Add";
			}

			// Failure
			map.addAttribute("msg", "Data not inserted...!");
			List<CarPOJO> cars = service.findAllCars();
			if (!cars.isEmpty()) {
				map.addAttribute("cars", cars);
			}
			return "Add";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed...!");
		return "Login";
	}

	// Search Page Controller
	@PostMapping("/search")
	public String searchStudent(@SessionAttribute(name = "login", required = false) AdminPOJO admin,
			@RequestParam int id, ModelMap map) {
		if (admin != null) {
			CarPOJO pojo = service.searchCar(id);

			// Success
			if (pojo != null) {
				map.addAttribute("student", pojo);
				map.addAttribute("msg", "Car data found..!");
				return "search";
			}
			// Failure
			map.addAttribute("msg", "Car data not found...!");
			return "Search";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
	}

	// Remove page controller
	@GetMapping("/remove")
	public String removePage(@SessionAttribute(name = "login", required = false) AdminPOJO admin, ModelMap map) {
		if (admin != null) {
			List<CarPOJO> cars = service.findAllCars();

			// Success
			if (!cars.isEmpty()) {
				map.addAttribute("cars", cars);
				return "Remove";
			}
			map.addAttribute("msg", "No data present...!");
			return "Remove";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed...!");
		return "Login";
	}

	// Remove Car controller
	@PostMapping("/remove")
	public String removeCar(@SessionAttribute(name = "login", required = false) AdminPOJO admin, @RequestParam int id,
			ModelMap map) {

		if (admin != null) {
			CarPOJO pojo = service.removeCar(id);
			List<CarPOJO> cars = service.findAllCars();

			// Success
			if (pojo != null) {
				map.addAttribute("msg", "Data removed successfull..!");
				map.addAttribute("cars", cars);
			}

			// Failure
			map.addAttribute("msg", "Data does not exists");
			map.addAttribute("cars", cars);
			return "Remove";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
	}

	// Update Page Controller
	@GetMapping("/update")
	public String updatePage(@SessionAttribute(name = "login", required = false) AdminPOJO admin, ModelMap map) {
		if (admin != null) {
			List<CarPOJO> cars = service.findAllCars();
			map.addAttribute("Cars", cars);
			return "Update";
		}
		map.addAttribute("map", "Session inactive. Login to proceed...!");
		return "Login";
	}

	// Update form controller
	@GetMapping("/update")
	public String updateForm(@SessionAttribute(name = "login", required = false) AdminPOJO admin, @RequestParam int id,
			ModelMap map) {

		if (admin != null) {
			CarPOJO pojo = service.searchCar(id);

			// Success
			if (pojo != null) {
				map.addAttribute("car", pojo);
				return "Update";
			}

			// Failure
			List<CarPOJO> cars = service.findAllCars();
			map.addAttribute("cars", cars);
			map.addAttribute("msg", "Car data not found..!");
			return "Update";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
	}

	// Update Car Controller
	public String updateCar(@SessionAttribute(name = "Login", required = false) AdminPOJO admin, @RequestParam int id,
			@RequestParam String name, @RequestParam String brand, @RequestParam String fuel_type,
			@RequestParam double price, ModelMap map) {
		if (admin != null) {
			CarPOJO pojo = service.updateCar(id, name, brand, fuel_type, price);

			// Success
			if (pojo != null) {
				List<CarPOJO> cars = service.findAllCars();
				map.addAttribute("msg", "Data updated successfully..!");
				map.addAttribute("cars", cars);
				return "Update";
			}
			List<CarPOJO> cars = service.findAllCars();
			map.addAttribute("msg", "Data not updates...!");
			map.addAttribute("cars", cars);
			return "Update";

		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
	}
}