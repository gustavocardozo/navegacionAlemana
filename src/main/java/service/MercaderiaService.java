package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.Mercaderia;

@Component
public class MercaderiaService implements IGenericService<Mercaderia, Long>, Serializable {

	private static final long serialVersionUID = -8063121520487281281L;

	@Autowired
	private SessionFactory sessionFactory;
	
	public MercaderiaService() {
	}

	@Override
	public Long save(Mercaderia instance) {
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
	public boolean update(Mercaderia transientObject) {
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
	public boolean saveOrUpdate(Mercaderia transientObject) {
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
	public void delete(Mercaderia persistentObject) {
		deleteByPrimaryKey(persistentObject.getId());		
	}

	@Override
	public boolean deleteByPrimaryKey(Long primaryKey) {
		boolean save = false;
		 Transaction trns = null;
	     Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            Mercaderia mercaderia = (Mercaderia) session.load(Mercaderia.class, new Long(primaryKey));
	            session.delete(mercaderia);
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
	public Mercaderia findByPrimaryKey(Long primaryKey) {
		List<Mercaderia> mercaderias = new ArrayList<Mercaderia>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from Mercaderia where MERCADERIA_ID = :id ");
            query.setParameter("id", primaryKey);
            mercaderias = query.list();
        } catch (RuntimeException e) {
        	 if (trns != null) {
	                trns.rollback();
	            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return mercaderias.isEmpty() ? null : mercaderias.get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Mercaderia> findAll() {
		List<Mercaderia> mercaderias = new ArrayList<Mercaderia>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            mercaderias = session.createQuery("from Mercaderia").list();
        } catch (RuntimeException e) {
        	 if (trns != null) {
	                trns.rollback();
	            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return mercaderias;
	}

	@Override
	public List<Mercaderia> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean despachar(Mercaderia mercaderia) {
		boolean saved = false;
		if (null != mercaderia) {
			mercaderia.setFechaEgreso(new Date());
			saved = this.saveOrUpdate(mercaderia);
		}
		
		return saved;
	}



}