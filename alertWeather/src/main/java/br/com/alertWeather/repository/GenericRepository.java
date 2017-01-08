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
	public void saveUpdate(Object object){
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(object);
	}

	@Transactional
	public void delete(Object object){
		Session session = entityManager.unwrap(Session.class);
	//	session.merge(object); //acrescentei aqui .
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
	
	protected List<T> pageList(StringBuilder sql, Map<String, Object> parameters, Integer page, Integer qntPage){
		instanceClass();
		
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), classe);
		setParameters(query, parameters);
		
		if(page != null && qntPage != null){
			query.setFirstResult(page(page, qntPage));
			query.setMaxResults(qntPage);
		}
		
		return query.getResultList();		
	}
	
	protected List<T> list(StringBuilder sql, Map<String, Object> parameters){
		return pageList(sql, parameters, null, null);		
	}


	protected void setParameters(TypedQuery<T> query, Map<String, Object> parameters){
		for (String key : parameters.keySet()) {
			query.setParameter(key, parameters.get(key));
		}
	}

	protected void setParameters(Query query, Map<String, Object> parameters){
		for (String key : parameters.keySet()) {
			query.setParameter(key, parameters.get(key));
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
