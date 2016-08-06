package com.dataBytes.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataBytes.controller.EmployeeController;
import com.dataBytes.dto.Employee;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	 
	
    public boolean isExist(Long id) {
    	Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("select 1 from Employee where ID=:id");
			
			
			List employeeList =query.setParameter("id", id).list();
		    return employeeList.isEmpty() ? false: true;
		    
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;		
    }
    
    @Override 	    
	public void add(Employee item) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(item);
		  } catch (HibernateException e) {
			  e.printStackTrace();
			  session.getTransaction().rollback();
		}
			session.getTransaction().commit();
	}
	
    @Override 	
	public void update(Employee item) {
		
		Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("IN Update");
			session.beginTransaction();
			session.saveOrUpdate(item);
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		session.getTransaction().commit();
	}
	
	@Override 
	public Employee getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Employee item=null;
		try {
			System.out.println("IN GetIteam");
			session.beginTransaction();
		    item = (Employee) session.get(Employee.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return item;
	}
	
	@Override 
	public List<Employee> getByName(String name, int firstResult, int maxResults) {
		Session session = sessionFactory.getCurrentSession();
		Employee item=null;
		List<Employee> employees = null;
		try {
			log.debug("calling getByName procedure");
			session.beginTransaction();
			Query query = session.createQuery("from Employee where ID=:idOrName "
					+ "or FIRSTNAME||MIDDLENAME||LASTNAME like '%:name%' ");
			
			query.setParameter("name", name);
			if (firstResult != -1) query.setFirstResult(firstResult);  
			if (maxResults != -1) query.setMaxResults(maxResults); 
		    employees =query.setParameter("idOrName", name).list();
	
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return employees;
	}
	
	@Override 
	public List<Employee> get(String idOrName, int firstResult, int maxResults) {
		Session session = sessionFactory.getCurrentSession();
		Employee item=null;
		List<Employee> employees = null;
		try {
			System.out.println("In getEmployee(String idOrName)");
			session.beginTransaction();
		    //item = (EmployeeMap) session.get(EmployeeMap.class, id);
			Query query = session.createQuery("from Employee where ID=:idOrName "
					+ "or FIRSTNAME||MIDDLENAME||LASTNAME like '%:idOrName%' ");
			
			query.setParameter("idOrName", idOrName);
			if (firstResult != -1) query.setFirstResult(firstResult);  
			if (maxResults != -1) query.setMaxResults(maxResults); 
		    employees =query.setParameter("idOrName", idOrName).list();
		    
		} catch (Exception e) {
			e.printStackTrace();	
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return employees;
	}
	
	@Override 
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Employee item = (Employee) session.get(Employee.class, id);
		if(null != item) {
			session.delete(item);
		}
		session.getTransaction().commit();
		//return item;
	}

	@Override 
	public List<Employee> list(int firstResult, int maxResults) {
		
		Session session = sessionFactory.getCurrentSession();
		List<Employee> items = null;
		try {
			System.out.println("IN LIST");
			
			Query query = session.createQuery("from Employee");
			if (firstResult != -1) query.setFirstResult(firstResult);  
			if (maxResults != -1) query.setMaxResults(maxResults); 
			items = (List<Employee>)query.list();
			
		} catch (Exception e) {
			e.printStackTrace();			
		}		
		return items;
	}

}
