package service;

import java.io.Serializable;
import java.util.List;


/**
* Generic DAO for Hibernate 
*/
public interface IGenericDao<E,PK  extends Serializable> {
	
    PK persist(E newInstance);
    void update(E transientObject);
	void saveOrUpdate(E transientObject);
    void delete(E persistentObject);
    void deleteByPrimaryKey(PK primaryKey);
    void deleteAll();
	Long count();
    E findByPrimaryKey(PK primaryKey);
    List<E> findAllByProperty(String propertyName, Object value);
    E findByUniqueProperty(String propertyName, Object value);
    List<E> findAll();
	List<E> findAllByNullProperty(String propertyName);
	     	

}