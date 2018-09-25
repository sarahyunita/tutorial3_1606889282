package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;


@Controller
public class CarController {
	@Autowired
	private CarService mobilService;

	
	
	@RequestMapping("/car/add")
	public String add(@RequestParam(value="id", required = true) String id,
					@RequestParam(value="brand", required = true) String brand,
					@RequestParam(value = "type", required = true) String type,
					@RequestParam(value = "price", required = true) Long price,
					@RequestParam(value= "amount", required = true) Integer amount) {
		CarModel car= new CarModel(id, brand, type, price, amount);
		mobilService.addCar(car);
		return "add";
	}
	
	
	@RequestMapping("/car/view")
	public String view(@RequestParam("id") String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		model.addAttribute("car", archive);
		return "view-car";
	}
	
	
	@RequestMapping("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archive = mobilService.getCarList();
		
		model.addAttribute("listCar",archive);
		return "viewall-car";
	}
	
	@RequestMapping(value= {"car/view","/car/view/{id}"})
	public String viewPath(@PathVariable Optional <String> id, Model model) {
		if(id.isPresent()) {
			CarModel archive = mobilService.getCarDetail(id.get());
			if(archive != null) {
				model.addAttribute("car", archive);
				return "view-car";
			}
			else {
				return "view-fail";
			}
		}
		return "view-fail";	
	}
	
	@RequestMapping({"/car/update/{id}/amount/{amount}"})
	public String updateAmount(@PathVariable Optional<String> id, Model model,
			@PathVariable Optional<Integer> amount ) {
		if(id.isPresent()) {
			CarModel archive = mobilService.getCarDetail(id.get());
			if(archive !=null) {
				if(amount.isPresent()) {
				//model.addAttribute("car", archive);
					archive.setAmount(amount.get());
					return "update";
				}
				else {
					return "update";
				}
			}
			else {
				return "update-fail";
			}
		
		}
		else {
			return "update-fail";
			
		}
	
	}
	
	@RequestMapping({"/car/delete/{id}"})
	public String delete(@PathVariable Optional<String> id, Model model) {
		if(id.isPresent()) {
			CarModel archive = mobilService.getCarDetail(id.get());
			if(archive!= null) {
				List<CarModel> carList = mobilService.getCarList();
				carList.remove(archive);
				model.addAttribute("car", archive);
				return "delete";
				
			}
			else {
				return "delete-fail";
			}
		}
		return "delete-fail";
		
	}
	
	

}
