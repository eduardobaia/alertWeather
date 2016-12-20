package br.com.alertWeather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alertWeather.entity.Alert;
import br.com.alertWeather.services.AlertService;

@Controller
@RequestMapping("/alerts")
public class AlertController extends GenericController<Alert> {

	@Autowired
	private AlertService alertService;

	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> seach(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "temp") String temp,
			@RequestParam(value = "city") String city,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "qntPage", required = false, defaultValue = "10") Integer qntPage) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		Long counter = 0L;
		List<Alert> list = new ArrayList<Alert>();
		boolean pagination = false;

		if (page != 0) {
			counter = alertService
					.counter(name, description, temp, city, state);
			pagination = true;
		}

		if (pagination) {

			if (counter != 0) {
				list = alertService.list(name, description, temp, city, state);
			} else {
				list = alertService.list(name, description, temp, city, state);
			}

		}

		return new ResponseEntity<String>(toJson(counter, list), headers,
				HttpStatus.OK);

	}
	
	@RequestMapping(headers = "Accept=application/json", value = "/{id}" )
	@ResponseBody
	public ResponseEntity<String>  seachObject (@PathVariable("id") int id){
		

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		Alert alert = alertService.seach(id);
		
		if(alert == null){
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);

		}else{
			return new ResponseEntity<String>(toJson(alert), headers, HttpStatus.OK);
	
		}
		
	}

}
