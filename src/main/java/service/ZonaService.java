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

import domain.Zona;
import domain.Zona.TIPO_CATEGORIA;
import domain.Zona.TIPO_PESO;
import domain.Zona.TIPO_TEMPERATURA;

@Component
public class ZonaService implements
		IGenericService<Zona, Long>, Serializable {

	private static final long serialVersionUID = -4354031832123678690L;

	@Autowired
	private SessionFactory sessionFactory;
	
	public ZonaService() {
	}

	@Override
	public Long save(Zona instance) {
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
	public boolean update(Zona transientObject) {
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
	public boolean saveOrUpdate(Zona transientObject) {
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
	public void delete(Zona persistentObject) {
		deleteByPrimaryKey(persistentObject.getId());
		
	}

	@Override
	public boolean deleteByPrimaryKey(Long primaryKey) {
		boolean save = false;
		 Transaction trns = null;
	     Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            Zona zona = (Zona) session.load(Zona.class, new Long(primaryKey));
	            session.delete(zona);
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
	public Zona findByPrimaryKey(Long primaryKey) {
		List<Zona> zonas = new ArrayList<Zona>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from Zona where ZONA_ID = :id ");
            query.setParameter("id", primaryKey);
            zonas = query.list();
        } catch (RuntimeException e) {
        	 if (trns != null) {
	                trns.rollback();
	            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return zonas.isEmpty() ? null : zonas.get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> findAll() {
		List<Zona> zonas = new ArrayList<Zona>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            zonas = session.createQuery("from Zona").list();
        } catch (RuntimeException e) {
        	 if (trns != null) {
	                trns.rollback();
	            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return zonas;
	}

	@Override
	public List<Zona> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}



	@SuppressWarnings("unchecked")
	public List<Zona> findActiveAll() {
		List<Zona> zonas = new ArrayList<Zona>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            
            Query query = session.createQuery("from Zona where ESTADO = :estado ");
            query.setParameter("estado", true);
            zonas = query.list();

        } catch (RuntimeException e) {
        	 if (trns != null) {
	                trns.rollback();
	            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return zonas;
	}
	
	public Zona findByProperties(TIPO_TEMPERATURA temperatura , TIPO_CATEGORIA categoria , TIPO_PESO peso){
		List<Zona> list = this.findActiveAll();
		List<Zona> listOK = new ArrayList<Zona>();
		Zona value = null;
		
		for (Zona zona : list) {
			if (temperatura.equals(zona.getTemperatura()) 
				&& categoria.equals(zona.getCategoria())
						&& peso.equals(zona.getPeso())){
				listOK.add(zona);
			}
		}
		
		if (!listOK.isEmpty()) {
			value = listOK.get(0);
			
			for (Zona zona : listOK) {
				if (value.getCapacidad() < zona.getCapacidad()) {
					value = zona;
				}
			}
		}
		
		return value;
	}
	
}