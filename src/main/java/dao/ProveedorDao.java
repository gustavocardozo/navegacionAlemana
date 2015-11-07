package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import service.IGenericDao;
import domain.Proveedor;

@Repository
public class ProveedorDao implements IGenericDao<Proveedor, Long> {

	@Override
	public Long persist(Proveedor newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Proveedor transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Proveedor transientObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Proveedor persistentObject) {
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
	public Proveedor findByPrimaryKey(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proveedor> findAllByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proveedor findByUniqueProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proveedor> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}


}
