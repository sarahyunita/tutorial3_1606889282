package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial3.model.CarModel;

import org.springframework.stereotype.Service;

@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}
	
	@Override
	public void addCar(CarModel car) {
		archiveCar.add(car);
	}
	
	@Override
	public List<CarModel> getCarList(){
		return archiveCar;
	}
	
	public void deleteCarList(CarModel car) {
		for(int i=0;i<archiveCar.size();i++) {
			if(archiveCar.get(i).equals(car)) {
				archiveCar.remove(car);
	
			}
		}
		
		
	}
	
	@Override
	public CarModel getCarDetail(String id) {
		for(int i=0;i<archiveCar.size();i++) {
			if(archiveCar.get(i).getId().equals(id)) {
				return archiveCar.get(i);
			}
			
		}
		return null;
	}
	
	
	
}
