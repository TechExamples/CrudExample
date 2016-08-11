package com.dataBytes.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dataBytes.dto.Employee;
import com.dataBytes.dto.Privilege;

@Repository("privilegeDao")
@Transactional
public class PrivilegeServiceImpl implements PrivilegeDao {

	private static final Logger log = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Privilege> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Privilege> privileges = null;
		
		try {	
			Query query = session.createQuery("from Privilege");
			privileges = (List<Privilege>)query.list();
			
		} catch (Exception e) {
			log.error("Exception Occured in fetching privileges record fetch", e);
		}		
		return privileges;
	}

}
