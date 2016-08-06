package com.dataBytes.service;

import java.util.List;

import com.dataBytes.dto.Employee;

public interface EmployeeService {
	
	public Employee findById(long id);
	
	public boolean isExist(long id);
	
	public List<Employee> findByName(String name);
	
	public List<Employee> findByName(String name, int firstResult, int maxResults);
	
	public List<Employee> findByIdOrName(String idOrname);
     
	public List<Employee> findByIdOrName(String idOrname, int firstResult, int maxResults);
	
    boolean add(Employee employee);
     
    boolean update(Employee employee);
     
    boolean delete(long id);
 
    public List<Employee> findAllEmployees(); 
     
}
