package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import service.IGenericDao;
import domain.Zona;

@Repository
public class ZonaDao implements IGenericDao<Zona, Long> {

	@Override
	public Long persist(Zona newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Zona transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Zona transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Zona persistentObject) {
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
	public Zona findByPrimaryKey(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Zona> findAllByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zona findByUniqueProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Zona> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Zona> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}


}
