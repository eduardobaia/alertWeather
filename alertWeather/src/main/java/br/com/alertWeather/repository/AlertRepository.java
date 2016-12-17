package br.com.alertWeather.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Repository;

import br.com.alertWeather.entity.Alert;
import br.com.alertWeather.util.AlertWeatherUtil;


@Repository
public class AlertRepository  extends GenericRepository<Alert>{

	public Long counter(String name, String description, String temp, String city, String state) {

		StringBuilder sql = new StringBuilder("SELECT COUNT (a) FROM Alert a");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		if(AlertWeatherUtil.isNullOrEmpty(name)){
			setSQL(sql, "a.name LIKE :name");
			parameters.put("name","%"+ name +"%" );
		}
		

		if(AlertWeatherUtil.isNullOrEmpty(description)){
			setSQL(sql, "a.description LIKE :description");
			parameters.put("description","%"+ description +"%" );
		}
		
		
		if(AlertWeatherUtil.isNullOrEmpty(temp)){
			setSQL(sql, "a.temp LIKE :temp");
			parameters.put("temp","%"+ temp +"%" );
		}
		
		
		if(AlertWeatherUtil.isNullOrEmpty(city)){
			setSQL(sql, "a.city LIKE :city");
			parameters.put("city","%"+ city +"%" );
		}
		
		if(AlertWeatherUtil.isNullOrEmpty(state)){
			setSQL(sql, "a.state LIKE :state");
			parameters.put("state","%"+ state +"%" );
		}
		
		
		return counter(sql, parameters);
	}

	public List <Alert> seach (String name, String description, String temp, String city, String state) {

		
		StringBuilder sql = new StringBuilder("SELECT a FROM Alert a");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		

		if(AlertWeatherUtil.isNullOrEmpty(name)){
			setSQL(sql, "a.name LIKE :name");
			parameters.put("name","%"+ name +"%" );
		}
		

		if(AlertWeatherUtil.isNullOrEmpty(description)){
			setSQL(sql, "a.description LIKE :description");
			parameters.put("description","%"+ description +"%" );
		}
		
		
		if(AlertWeatherUtil.isNullOrEmpty(temp)){
			setSQL(sql, "a.temp LIKE :temp");
			parameters.put("temp","%"+ temp +"%" );
		}
		
		
		if(AlertWeatherUtil.isNullOrEmpty(city)){
			setSQL(sql, "a.city LIKE :city");
			parameters.put("city","%"+ city +"%" );
		}
		
		if(AlertWeatherUtil.isNullOrEmpty(state)){
			setSQL(sql, "a.state LIKE :state");
			parameters.put("state","%"+ state +"%" );
		}
		
		
		
		
		return pageList(sql, parameters, 1, 10);

	}
	
	
}
