package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import service.IGenericDao;
import domain.Mercaderia;

@Repository
public class MercaderiaDao implements IGenericDao<Mercaderia, Long> {

	@Override
	public Long persist(Mercaderia newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Mercaderia transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Mercaderia transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Mercaderia persistentObject) {
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
	public Mercaderia findByPrimaryKey(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mercaderia> findAllByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mercaderia findByUniqueProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mercaderia> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mercaderia> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}


}