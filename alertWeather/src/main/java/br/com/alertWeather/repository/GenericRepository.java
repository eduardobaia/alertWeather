package br.com.alertWeather.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.core.GenericTypeResolver;

public abstract class GenericRepository<T> {

	private Class<T> classe;

	private String WHERE = " WHERE ";
	private String AND = " AND ";

	@PersistenceContext
	protected EntityManager entityManager;

	@Transactional
	public void savaUpdate(Object object){
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(object);
	}

	@Transactional
	public void delete(Object object){
		Session session = entityManager.unwrap(Session.class);
		session.delete(object);
	}

	@Transactional
	public T seachById(int id){
		instanceClass();
		return entityManager.find(classe, id);
	}

	protected Long counter(StringBuilder sql, Map<String, Object> parameters){
		Query query = entityManager.createQuery(sql.toString(), Long.class);
		setParameters(query, parameters);
		Long result = (Long) query.getSingleResult();
		return result;
	}
	
	protected List<T> pageList(StringBuilder sql, Map<String, Object> parametros, Integer pagina, Integer quantidadePagina){
		instanceClass();
		
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), classe);
		setParameters(query, parametros);
		
		if(pagina != null && quantidadePagina != null){
			query.setFirstResult(page(pagina, quantidadePagina));
			query.setMaxResults(quantidadePagina);
		}
		
		return query.getResultList();		
	}
	
	protected List<T> list(StringBuilder sql, Map<String, Object> parametros){
		return pageList(sql, parametros, null, null);		
	}


	protected void setParameters(TypedQuery<T> query, Map<String, Object> parametros){
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
	}

	protected void setParameters(Query query, Map<String, Object> parametros){
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
	}

	protected void setSQL(StringBuilder sql, String newSQL){

		if(sql.toString().contains(WHERE.trim())){
			sql.append(AND).append(newSQL);			
		}else{
			sql.append(WHERE).append(newSQL);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void instanceClass(){
		this.classe = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericRepository.class);
	}
	
	private int page(int page, int qntPage){
		int value = page - 1;
		if(value <= 0){
			return 0;
		}
		value = value * qntPage;
		return value;
	}
	
	

}
