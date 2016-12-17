package br.com.alertWeather.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alertWeather.entity.User;
import br.com.alertWeather.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> list (String name, String password){
		return repository.search(name, password);
	}
	
	public Long counter (String name, String password){
		return repository.counter(name, password);
	}
	
	
	
	public User seach (int id){
		return repository.seachById(id);
   }
	
}
