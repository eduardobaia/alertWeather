package br.com.alertWeather.controller;

import java.util.List;

import org.springframework.core.GenericTypeResolver;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public abstract class GenericController<T>{
	
	private Class<T> classe;
	
	public T fromJson(String json) {
		instanciarClasse();
		return new JSONDeserializer<T>().use(null, classe).deserialize(json);
	}

	public String toJson(Object object) {
		return new JSONSerializer().exclude("*.class").serialize(object);
    }
	
	public String toJson(Long contador, List<?> lista) {
		JsonAlertWeather json = new JsonAlertWeather(contador, lista);
		
		return new JSONSerializer().exclude("*.class").include("list").serialize(json);
    }
	
//    public String toJson(String[] include) {
//        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
//    }
//    
//    public static T fromJson(String json) {
//        return new JSONDeserializer<T>().use(null, Account.class).deserialize(json);
//    }
//    
//    public static String toJsonArray(Collection<T> collection) {
//        return new JSONSerializer().exclude("*.class").serialize(collection);
//    }
//    
//    public static String toJsonArray(Collection<T> collection, String[] fields) {
//        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
//    }
//    
//    public static Collection<T> fromJsonArray(String json) {
//        return new JSONDeserializer<List<T>>().use("values", Account.class).deserialize(json);
//    }
	
	
	@SuppressWarnings("unchecked")
	private void instanciarClasse(){
		this.classe = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericController.class);
	}
	
	private class JsonAlertWeather{
		private Long counter;
		private List<?> list;
		
		public JsonAlertWeather(Long counter, List<?> list) {
			this.counter = counter;
			this.list = list;
		}

		@SuppressWarnings("unused")
		public Long getCounter() {
			return counter;
		}

		@SuppressWarnings("unused")
		public List<?> getList() {
			return list;
		}
	}
	
}
