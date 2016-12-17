package br.com.alertWeather.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.alertWeather.entity.User;
import br.com.alertWeather.util.AlertWeatherUtil;

@Repository
public class UserRepository extends GenericRepository<User> {

	@Transactional
	public Long counter(String name, String password) {

		StringBuilder sql = new StringBuilder("SELECT COUNT(c) FROM User c ");

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (!AlertWeatherUtil.isNullOrEmpty(name)) {
			setSQL(sql, "c.name LIKE : name");
			parameters.put("name", "%" + name + "%");
		}

		if (!AlertWeatherUtil.isNullOrEmpty(password)) {
			setSQL(sql, "c.password LIKE : password");
			parameters.put("password", "%" + password + "%");
		}

		return counter(sql, parameters);

	}

	@Transactional
	public List<User> search(String name, String password) {

		StringBuilder sql = new StringBuilder("SELEC c FROM User c");

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (!AlertWeatherUtil.isNullOrEmpty(name)) {
			setSQL(sql, "c.name LIKE :name");
			parameters.put(name, "%" + name + "%");

		}
		
		if (!AlertWeatherUtil.isNullOrEmpty(password)) {
			setSQL(sql, "c.password LIKE :password");
			parameters.put(password, "%" + password + "%");

		}

		return pageList(sql, parameters, 1, 10);

	}

}
