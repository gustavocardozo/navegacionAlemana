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

import domain.Proveedor;

@Component
public class ProveedorService implements
		IGenericService<Proveedor, Long>, Serializable {

	private static final long serialVersionUID = -7563106887341660668L;

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public ProveedorService() {
	}
	

	@Override
	public Long save(Proveedor instance) {
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
	public boolean update(Proveedor transientObject) {
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
	public boolean saveOrUpdate(Proveedor transientObject) {
		boolean save = false;
		if (null != transientObject.getId()) {
			save = update(transientObject);
		}else{
			Long id = save(transientObject);
			save = null != id ? true : false;
		}
		return save;
	}

	@Override
	public void delete(Proveedor persistentObject) {
		deleteByPrimaryKey(persistentObject.getId());
	}

	@Override
	public boolean deleteByPrimaryKey(Long primaryKey) {
		boolean save = false;
		 Transaction trns = null;
	     Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            Proveedor user = (Proveedor) session.load(Proveedor.class, new Long(primaryKey));
	            session.delete(user);
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
	public Proveedor findByPrimaryKey(Long primaryKey) {
		
		List<Proveedor> proveedors = new ArrayList<Proveedor>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from Proveedor where PROVEEDOR_ID = :id ");
            query.setParameter("id", primaryKey);
            proveedors = query.list();
        } catch (RuntimeException e) {
        	 if (trns != null) {
	                trns.rollback();
	            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return proveedors.isEmpty() ? null : proveedors.get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> findAll() {
		 List<Proveedor> proveedors = new ArrayList<Proveedor>();
	        Transaction trns = null;
	        Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            proveedors = session.createQuery("from Proveedor").list();
	        } catch (RuntimeException e) {
	        	 if (trns != null) {
		                trns.rollback();
		            }
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	        return proveedors;
	}

	@Override
	public List<Proveedor> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
