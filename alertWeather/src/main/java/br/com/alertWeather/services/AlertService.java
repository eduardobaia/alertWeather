package br.com.alertWeather.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alertWeather.entity.Alert;
import br.com.alertWeather.repository.AlertRepository;

@Service
public class AlertService {

	@Autowired
	private AlertRepository repository;
	
	public List<Alert> list ( String name, String description, String temp, String city, String state){
		return repository.seach(name, description, temp, city, state);
				
	}
	
	public Long counter( String name, String description, String temp, String city, String state){
		return repository.counter(name, description, temp, city, state);
		
	}
	
	public Alert seach (int id){
		return repository.seachById(id);
	}
	
}
