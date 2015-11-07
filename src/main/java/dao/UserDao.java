package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import service.IGenericDao;
import domain.User;


@Repository
public class UserDao implements IGenericDao<User, Long> {

	@Override
	public Long persist(User newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(User transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User persistentObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByPrimaryKey(Long primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByPrimaryKey(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUniqueProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}


}
