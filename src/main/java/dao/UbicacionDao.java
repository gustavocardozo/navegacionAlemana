package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import domain.Ubicacion;
import service.IGenericDao;

@Repository
public class UbicacionDao implements IGenericDao<Ubicacion, Long> {

	@Override
	public Long persist(Ubicacion newInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ubicacion transientObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Ubicacion transientObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Ubicacion persistentObject) {
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
	public Ubicacion findByPrimaryKey(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ubicacion> findAllByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ubicacion findByUniqueProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ubicacion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ubicacion> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

}
