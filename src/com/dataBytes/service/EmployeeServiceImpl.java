package com.dataBytes.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dataBytes.controller.EmployeeController;
import com.dataBytes.dao.EmployeeDAO;
import com.dataBytes.dto.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Employee findById(long id) {
		
		Employee employee = employeeDAO.getById(id); 		
		updateFilenames(employee);
		
		return employee;
	}

	@Override
	public boolean isExist(long id) {
		return employeeDAO.isExist(id);
	}

	@Override
	public List<Employee> findByName(String name) {
		
		List<Employee> employees = employeeDAO.getByName(name, -1, -1);		
		for (Employee employee : employees) {			 
			updateFilenames(employee);
		}
		
		return employees;
	}

	@Override
	public List<Employee> findByName(String name, int firstResult, int maxResults) {
		
		List<Employee> employees = employeeDAO.getByName(name, firstResult, maxResults);		
		for (Employee employee : employees) {			 
			updateFilenames(employee);
		}
		
		return employees;
	}

	@Override
	public List<Employee> findByIdOrName(String idOrname) {
		
		List<Employee> employees = employeeDAO.get(idOrname, -1, -1);		
		for (Employee employee : employees) {			 
			updateFilenames(employee);
		}
		
		return employees;
	}

	@Override
	public List<Employee> findByIdOrName(String idOrname, int firstResult, int maxResults) {
		
		List<Employee> employees = employeeDAO.get(idOrname, firstResult, maxResults);		
		for (Employee employee : employees) {			 
			updateFilenames(employee);
		}
		
		return employees;
	}

	@Override
	public boolean add(Employee employee) {
		try {
			employeeDAO.add(employee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Employee employee) {
		try {
			employeeDAO.update(employee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(long id) {
		try {
			employeeDAO.delete(id);
			
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + id);
			if (dir.exists()) {
				FileUtils.deleteDirectory(dir);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Employee> findAllEmployees() {

		List<Employee> employees = employeeDAO.list(-1, -1);		
		for (Employee employee : employees) {			 
			updateFilenames(employee);
		}
		
		return employees;
	}

	public void updateFilenames(Employee employee) {
		
		if (employee == null ) {
			return;
		}
		
		try {
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles" + File.separator + employee.getId());
			if (dir.exists()) {
				Set<String> files = employee.getFiles();
				Iterator<File> list = FileUtils.iterateFiles(dir, null, false);

				while (list.hasNext())
					files.add(list.next().getName());

			}
		} catch (Exception e) {
			log.error("Exception occured while adding Filename to Employee object.", e);

		}

	}

}