package com.dataBytes.dao;

import java.util.List;
import java.util.Set;

import com.dataBytes.dto.Employee;

public interface EmployeeDAO {

	public boolean isExist(Long id);
	public Employee getById(Long id);
	public List<Employee> getByName(String name, int firstResult, int maxResults);
	public List<Employee>  get(String idOrName, int firstResult, int maxResults);
	public List<Employee> list(int firstResult, int maxResults);
	public void add(Employee employee);
	public void update(Employee employee);
	public void delete(Long id);
}
