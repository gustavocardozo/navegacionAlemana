package service;

import java.util.List;

/**
 * Generic Service
 */
public interface IGenericService<E, PK> {

	public PK save(E newInstance) ;

	public boolean update(E transientObject);

	public boolean saveOrUpdate(E transientObject);

	public void delete(E persistentObject);

	public boolean deleteByPrimaryKey(PK primaryKey);

	public void deleteAll();

	public E findByPrimaryKey(PK primaryKey);

	public List<E> findAllByProperty(String propertyName, Object value);

	public E findByUniqueProperty(String propertyName, Object value);

	public List<E> findAll();

	List<E> findAllByNullProperty(String propertyName);

}