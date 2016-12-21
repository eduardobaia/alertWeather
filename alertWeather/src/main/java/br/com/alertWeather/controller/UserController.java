package br.com.alertWeather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alertWeather.entity.User;
import br.com.alertWeather.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController extends GenericController<User> {

	@Autowired
	private UserService userService;

	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> seach(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "qntPage", required = false, defaultValue = "10") Integer qntPage) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		Long counter = 0L;
		List<User> list = new ArrayList<User>();
		boolean pagination = false;

		if (page != 0) {
			counter = userService.counter(name, password);
			pagination = true;
		}

		if (pagination) {

			if (counter != 0) {
				list = userService.list(name, password);
			} else {
				list = userService.list(name, password);
			}

		}
 
	return new ResponseEntity<String>(toJson(counter, list), headers, HttpStatus.OK);

	}
	 
	
	@RequestMapping(headers = "Accept=application/json", value = "/{id}" )
	@ResponseBody
	public ResponseEntity<String> seachObject(@PathVariable("id") int id) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		User user = userService.seach(id);

		if (user == null) {
		return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<String>(toJson(user), headers, HttpStatus.OK);

		}

	}

	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		User user = fromJson(json);
		userService.saveUpdate(user);
		
		RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
		headers.add("Location",uriBuilder.path(a.value()[0]).build().toUriString());
		
		return new ResponseEntity<String>(toJson(user), headers, HttpStatus.CREATED);
			
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Integer id) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		User user = fromJson (json);
		user.setId(id);
		
		try{
			userService.saveUpdate(user);
		}
		catch(Exception e){
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
	
	
}
