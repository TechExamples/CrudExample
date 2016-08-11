package com.dataBytes.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataBytes.dao.EmployeeDAO;
import com.dataBytes.dao.PrivilegeDao;
import com.dataBytes.dto.Employee;
import com.dataBytes.dto.Privilege;

@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Override
	public List<Privilege> getAllPrivileges() {
		
		List<Privilege> privileges = privilegeDao.list();
		
		return privileges;
	}

}
