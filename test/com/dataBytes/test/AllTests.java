package com.dataBytes.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.dataBytes.test.controller.EmployeeControllerTest;


@RunWith(Suite.class)
@SuiteClasses({ EmployeeControllerTest.class
	})
public class AllTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
