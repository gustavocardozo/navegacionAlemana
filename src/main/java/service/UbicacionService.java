package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.Ubicacion;

@Component
public class UbicacionService implements IGenericService<Ubicacion, Long>, Serializable {

	private static final long serialVersionUID = -2155655809853291694L;
	
	@Autowired
	private SessionFactory sessionFactory;

	public UbicacionService() {
	}

	@Override
	public Long save(Ubicacion instance) {
		Transaction trns = null;
		Long id = null;
		Session session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			session.save(instance);
			session.getTransaction().commit();

			id = instance.getId();

		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return id;
	}

	@Override
	public boolean update(Ubicacion transientObject) {
		Transaction trns = null;
		boolean save = false;
		Session session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			session.update(transientObject);
			session.getTransaction().commit();
			save = true;
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return save;
	}

	@Override
	public boolean saveOrUpdate(Ubicacion transientObject) {
		boolean save = false;
		if (null != transientObject.getId()) {
			save = update(transientObject);
		} else {
			Long id = save(transientObject);
			save = null != id ? true : false;
		}
		return save;
	}

	@Override
	public void delete(Ubicacion persistentObject) {
		deleteByPrimaryKey(persistentObject.getId());
	}

	@Override
	public boolean deleteByPrimaryKey(Long primaryKey) {
		boolean save = false;
		Transaction trns = null;
		Session session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			Ubicacion ubicacion = (Ubicacion) session.load(Ubicacion.class, new Long(primaryKey));
			session.delete(ubicacion);
			session.getTransaction().commit();
			save = true;
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return save;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public Ubicacion findByPrimaryKey(Long primaryKey) {

		List<Ubicacion> Ubicacions = new ArrayList<Ubicacion>();
		Transaction trns = null;
		Session session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			Query query = session.createQuery("from Ubicacion where Ubicacion_ID = :id ");
			query.setParameter("id", primaryKey);
			Ubicacions = query.list();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return Ubicacions.isEmpty() ? null : Ubicacions.get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Ubicacion> findAll() {
		List<Ubicacion> Ubicacions = new ArrayList<Ubicacion>();
		Transaction trns = null;
		Session session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			Ubicacions = session.createQuery("from Ubicacion").list();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return Ubicacions;
	}

	@Override
	public List<Ubicacion> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

}
