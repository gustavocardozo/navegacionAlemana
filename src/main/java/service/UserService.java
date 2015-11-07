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


import domain.User;

@Component
public class UserService implements
		IGenericService<User, Long>, Serializable {

	private static final long serialVersionUID = 3903474337047454603L;

	@Autowired
	private SessionFactory sessionFactory;

	
	public UserService() {
	}

	@Override
	public Long save(User instance) {
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
	public boolean update(User transientObject) {
		boolean save = false;
		 Transaction trns = null;
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
	public boolean saveOrUpdate(User transientObject) {
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
	public void delete(User transientObject) {
		deleteByPrimaryKey(transientObject.getId());
	}

	@Override
	public boolean deleteByPrimaryKey(Long primaryKey) {
		boolean save = false;
		 Transaction trns = null;
	     Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            User user = (User) session.load(User.class, new Long(primaryKey));
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
	public User findByPrimaryKey(Long primaryKey) {
		 List<User> users = new ArrayList<User>();
	        Transaction trns = null;
	        Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            Query query = session.createQuery("from User where USER_ID = :id ");
	            query.setParameter("id", primaryKey);
	            users = query.list();
	        } catch (RuntimeException e) {
	        	 if (trns != null) {
		                trns.rollback();
		            }
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	        return users.isEmpty() ? null : users.get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		 List<User> users = new ArrayList<User>();
	        Transaction trns = null;
	        Session session = sessionFactory.openSession();
	        try {
	            trns = session.beginTransaction();
	            users = session.createQuery("from User").list();
	        } catch (RuntimeException e) {
	        	 if (trns != null) {
		                trns.rollback();
		            }
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	        return users;
	}

	@Override
	public List<User> findAllByNullProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}