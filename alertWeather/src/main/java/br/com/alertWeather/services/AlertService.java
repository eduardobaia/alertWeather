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
	
	public void saveUpdate(Alert object){
		repository.saveUpdate(object);
	}
	
	
	
	public List<Alert> list ( String name, String description, String temp, String city, String state, Integer userId, Integer page, Integer qnt){
		return repository.seach(name, description, temp, city, state, userId, page, qnt);
				
	}
	
	public Long counter( String name, String description, String temp, String city, String state, Integer userId){
		return repository.counter(name, description, temp, city, state, userId);
		
	}
	
	public Alert seach (int id){
		return repository.seachById(id);
	}
	
}
