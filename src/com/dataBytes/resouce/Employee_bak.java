package com.dataBytes.resouce;

import com.dataBytes.dto.Employee;

public class Employee_bak {
	
	private String employeeId;
	private String employeeName;
	private String odcId;
	private String odcName;
	
	public Employee_bak() {};
	
	public Employee_bak(Employee_bak employee_bak) {
		
		//setEmployeeId(employee_bak.getItem_code()+"");
		//setEmployeeName(employee_bak.getItem_name());
		//setOdcId(employee_bak.getPrice()+"");
		//setOdcName(employee_bak.getQty()+"");
	};
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getOdcId() {
		return odcId;
	}
	public void setOdcId(String odcId) {
		this.odcId = odcId;
	}
	public String getOdcName() {
		return odcName;
	}
	public void setOdcName(String odcName) {
		this.odcName = odcName;
	}

	

}
