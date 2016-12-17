package br.com.alertWeather.util;

import java.util.List;

public class AlertWeatherUtil {
	public static boolean isNullOrEmpty(String value){

		boolean validate = false;

		if(value == null){
			validate = true;
		}
		else if(value.isEmpty()){
			validate = true;
		}

		return validate;
	}


	public static boolean isNullOrEmpty(List<?> value){
		boolean validate = false;

		if(value == null){
			validate = true;
		}
		else if(value.isEmpty()){
			validate = true;
		}

		return validate;
	}

	public static boolean isNullOrZero(Object value){

		boolean validate = false;

		if(value == null){
			validate = true;
		}

		else{
			int v = Integer.parseInt(value.toString());

			if(v == 0){
				validate = true;
			}
		}

		return validate;
	}
	
	
}
