package com.kkrushnas.cardekho_case_study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kkrushnas.cardekho_case_study.pojo.AdminPOJO;
import com.kkrushnas.cardekho_case_study.pojo.CarPOJO;
import com.kkrushnas.cardekho_case_study.service.CarService;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Stubbing;

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
			List<CarPOJO> cars = service.findALLCars();
			if (!cars.isEmpty()) {
				map.addAttribute("cars", cars);
				return "Add";
			}
			return "Add";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed...!");
		return "login";
	}

	// Add student Controller
	@PostMapping("/add")
	public String addStudent(@SessionAttribute(name = "login", required = false) AdminPOJO admin,
			@RequestParam String name, @RequestParam String email, @RequestParam long contact,
			@RequestParam String address, ModelMap map) {
		if (admin != null) {
			CarPOJO pojo = service.addCar(name, email, contact, address);

			// Success
			if (pojo != null) {
				map.addAttribute("msg", "Data inserted successfully");
				List<CarPOJO> cars = service.findALLCars();
				map.addAttribute("cars", cars);
				return "Add";
			}

			// Failure
			map.addAttribute("msg", "Data not inserted...!");
			List<CarPOJO> cars = service.findALLCars();
			if (!cars.isEmpty()) {
				map.addAttribute("cars", cars);
			}
			return "Add";
		}
		map.addAttribute("msg","Session inactive. Login to proceed...!");
		return "Login";
	}
	
	//Search Page Controller
	@PostMapping("/search")
	public String searchStudent(@SessionAttribute(name = "login", required = false)AdminPOJO admin, 
			@RequestParam int id, ModelMap map) {
		if (admin != null) {
			CarPOJO pojo = service.searchCar(id);
			
			//Sucess
			if (pojo != null) {
				map.addAttribute("student", pojo);
				map.addAttribute("msg", "Car data found..!");
				return "search";
			}
			//Failure
			map.addAttribute("msg", "Car data not found...!");
			return "Search";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
	}
	
}
