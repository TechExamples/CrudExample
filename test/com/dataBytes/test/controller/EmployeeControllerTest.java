package com.dataBytes.test.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.dataBytes.controller.EmployeeController;
import com.dataBytes.dto.Employee;
import com.dataBytes.mail.MailHandler;
import com.dataBytes.service.EmployeeService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springServlet-servlet.xml"})
@ActiveProfiles("TEST")
public class EmployeeControllerTest {
	
	@InjectMocks
    private EmployeeController employeeController = new EmployeeController();

	@Mock
	private EmployeeService employeeServiceMock;
	
	@Mock
	private MailHandler mailHandler;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeControllerTest.class);

    public EmployeeControllerTest() {}

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	
    }

    @After
    public void tearDown() {

    }
    
    //@Test(expected = InsufficientProductsException.class)
    @Test
    public void testGetEmployeesJson() {
    	
    	Employee e = new Employee();
    	e.setId(new Long(10079));
    	
    	List<Employee> employees= new ArrayList<Employee>();
	    employees.add(e);
    	
        when(employeeServiceMock.findAllEmployees()).thenReturn(employees);
        
      //test the add functionality
        Assert.assertEquals(employeeServiceMock.findAllEmployees(), employees);
        
        verify(employeeServiceMock).findAllEmployees();
        
    }
    
    @Test
    public void testIntGetEmployeesJson() {
    	
    	Employee e = new Employee();
    	e.setId(new Long(10079));
    	
    	List<Employee> employees= new ArrayList<Employee>();
	    employees.add(e);
    	
        when(employeeServiceMock.findAllEmployees()).thenReturn(employees);
        
      //test the add functionality
        Assert.assertEquals(employeeServiceMock.findAllEmployees(), employees);
        
        verify(employeeServiceMock).findAllEmployees();
        
    }
}
