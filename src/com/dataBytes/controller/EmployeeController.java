package com.dataBytes.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.dataBytes.dto.Employee;
import com.dataBytes.dto.EmployeePrivilege;
import com.dataBytes.dto.Privilege;
import com.dataBytes.mail.MailHandler;
import com.dataBytes.service.EmployeeService;
import com.dataBytes.service.PrivilegeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {

	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;
	String rootPath = System.getProperty("catalina.home") + File.separator + "tmpFiles";

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PrivilegeService privilegeService;

	@Autowired
	private MailHandler mailHandler;

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	/*
	@Autowired
	@Qualifier("employeeValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	*/
	/*
	 * @RequestMapping(value = "/listEmployee", method = RequestMethod.GET)
	 * public ModelAndView list() { System.out.println("We are in list block");
	 * List<Employee> employeeList = employeeDAO.list(-1, -1); ModelAndView
	 * model = new ModelAndView("ListEmployee"); model.addObject("employeeList",
	 * employeeList); return model; }
	 * 
	 * @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	 * public ModelAndView add() { log.debug("Entered into add"); ModelAndView
	 * model = new ModelAndView("AddEmployee"); model.addObject("employee", new
	 * Employee()); List<Employee> employeeList = employeeDAO.list(-1, -1);
	 * model.addObject("employeeList", employeeList); return model; }
	 * 
	 * @RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	 * public ModelAndView edit(@RequestParam(value = "id", required = true)
	 * Long id) { System.out.println("Id= " + id); ModelAndView model = new
	 * ModelAndView("AddEmployee"); Employee employee = employeeDAO.get(id);
	 * model.addObject("employee", employee); List<Employee> employeeList =
	 * employeeDAO.list(-1,-1); model.addObject("employeeList", employeeList);
	 * return model; }
	 * 
	 * @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	 * public ModelAndView delete(@RequestParam(value = "id", required = true)
	 * Long id) { ModelAndView model = new ModelAndView("AddEmployee");
	 * employeeDAO.delete(id); model.addObject("employee", new Employee());
	 * List<Employee> employeeList = employeeDAO.list(-1,-1);
	 * model.addObject("employeeList", employeeList); mailHandler.sendMail(
	 * "Employee Remove", "Employee Removed Successfully"); return model; }
	 * 
	 * @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	 * public ModelAndView save(@ModelAttribute("employee") Employee employee) {
	 * log.info("Input employee object: "+employee); if (null != employee)
	 * employeeDAO.add(employee);
	 * 
	 * ModelAndView model = new ModelAndView("AddEmployee");
	 * model.addObject("employee", employee); List<Employee> employeeList =
	 * employeeDAO.list(-1, -1); model.addObject("employeeList", employeeList);
	 * mailHandler.sendMail("Employee Add", "Employee Added Successfully");
	 * return model; }
	 * 
	 */

	@RequestMapping(value = "/admin/home")
	public ModelAndView displayHomePage() {
		log.debug("displayHomePage");
		ModelAndView model = new ModelAndView("home");
		List<Employee> employees = employeeService.findAllEmployees();
		model.addObject("employees", employees);
		return model;
	}

	@RequestMapping(value = "/admin/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesJson() {

		List<Employee> employees;
		try {
			employees = employeeService.findAllEmployees();
			if (employees == null || employees.isEmpty()) {
				return new ResponseEntity<List<Employee>>(employees, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Exception occured in employeeService calling", e);
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/admin/employee/{idOrName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesJson(@PathVariable(value = "idOrName") String idOrName) {
		List<Employee> employees;

		try {
			employees = employeeService.findByIdOrName(idOrName);
			if (employees == null || employees.isEmpty()) {
				return new ResponseEntity<List<Employee>>(employees, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for idOrName =" + idOrName, e);
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);

	}

	@RequestMapping(value = { "/guest/employee/{id}", "/admin/employee/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody @Valid Employee employee, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {

		if (bindingResult.hasErrors()) {
			String msg = "Input data validation failed";
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		} else {

			if (employeeService.isExist(employee.getId())) {
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(
						ucBuilder.path("/admin/employee/" + employee.getId()).buildAndExpand(employee.getId()).toUri());
				String msg = "A Employee with Id " + employee.getId() + " already exist";
				log.info(msg);
				return new ResponseEntity<String>(msg, headers, HttpStatus.CONFLICT);
			}
		}

		try {
			if (employeeService.add(employee)) {
				mailHandler.sendMail("Employee Add", "Employee Added Successfully");
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(
						ucBuilder.path("/admin/employee/" + employee.getId()).buildAndExpand(employee.getId()).toUri());
				return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} else {
				String errorMessage = "Employee Add not happend whle adding to database due to request unfulfillment or internal server issue.";
				log.error(errorMessage + "employe =" + employee);
				return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for employe =" + employee, e);
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/admin/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Employee employee, UriComponentsBuilder ucBuilder, BindingResult bindingResult) {

		Set<EmployeePrivilege> employeePrivileges =  employee.getPrivileges();
		for (EmployeePrivilege employeePrivilege: employeePrivileges) {
			employeePrivilege.setEmployeeId(employee.getId());
		}
		
		System.out.print("employee:"+employee);
		if (bindingResult.hasErrors()) {
			String msg = "Input data validation failed";
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		} else {
			log.info("Employee Object employee"+ employee);
			if (!employeeService.isExist(employee.getId())) {
				String msg = "A Employee with Id " + employee.getId() + " does not exist";
				log.info(msg);
				return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
			}
		}

		try {
			employeeService.update(employee);

		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for employe =" + employee, e);
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		mailHandler.sendMail("Employee Update", "Employee updated Successfully");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/admin/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id) {

		if (id == 0) {
			String msg = "Can not support request with employee id is 0 or empty";
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);

		} else if (!employeeService.isExist(id)) {
			String msg = "A Employee with Id " + id + " does not exist";
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}

		try {
			employeeService.delete(id);

		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for employe ID =" + id, e);
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		mailHandler.sendMail("Employee Delete", "Employee deleted Successfully");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/admin/privileges", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPrivilegesJson() {

		List<Privilege> privileges;
		try {
			privileges = privilegeService.getAllPrivileges();
			if (privileges == null || privileges.isEmpty()) {
				return new ResponseEntity<List<Privilege>>(privileges, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Privilege>>(privileges, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Exception occured in privilegeService calling", e);
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/admin/{id}/file", method = RequestMethod.POST)
	public ResponseEntity<?> uploadMultipleFileHandler(@PathVariable(value = "id") long id,
			@RequestParam("files") MultipartFile[] files,
			UriComponentsBuilder ucBuilder) {
		
		String msg = null;
		HttpHeaders headers = new HttpHeaders();
		String url	= "";
		log.info("multipart filename lenth:"+ files.length);
		if (files.length == 0) {
			msg = "Uploaded file array length is zero. Seems files are not uploaded or not with param name 'files'";
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String filename = file.getOriginalFilename();
			log.info("multipart filename:"+ filename);
			try {
				// Creating the directory to store file
				File dir = new File(rootPath + File.separator + id);
				log.info("Dir path:"+dir.getAbsolutePath());
				if (!dir.exists())
					FileUtils.forceMkdir(dir);
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);

				if (serverFile.exists()) {
					FileUtils.forceDelete(serverFile);
				}

				try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
					stream.write(file.getBytes());
				} catch (Exception e) {
					msg = "Exception in coping Multipart file data to output stream object";
					log.error(msg, e);
					throw e;
				}

				log.info("Server File Location=" + serverFile.getAbsolutePath());
				msg = "file=" + filename;
				String tempUrl = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/admin/"+id+"/file/"+filename+".json").build().toUriString();
				url = url.equals("") ? tempUrl: url + "," + tempUrl; 
				log.info("URI:"+url);
		        
			} catch (Exception e) {
				log.error("Exception occured in while access file upload for emp id " + id
						+ ". Most likely file permissions issue ", e);
				msg = e + " <== error";
				return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		headers.set("Location", url);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Download the file
	 * 
	 * @param request
	 * @param response
	 * @param id
	 *            Employee id
	 * @param filename
	 *            Filename to be downloaded
	 * @return FileContent
	 */
	@RequestMapping(value = "/admin/{id}/file/{filename}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadFile(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "id") long id, @PathVariable(value = "filename") String filename) {

		log.info("Calling downloadFile"+ id +", filename:"+filename);
		String msg = null;
		File dir = new File(rootPath + File.separator + id);

		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);
		if (!serverFile.exists()) {
			log.info("Required file "+serverFile.getAbsolutePath()+" does not exist");
			msg = "Required file "+filename+" not found";
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}

		try (FileInputStream inputStream = new FileInputStream(serverFile);
				OutputStream outputStream = response.getOutputStream()) {

			// get MIME type of the file
			String mimeType = request.getServletContext().getMimeType(serverFile.getAbsolutePath());
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			log.info("MIME type: " + mimeType);

			// set content attributes for the response
			response.setContentType(mimeType);
			response.setContentLength((int) serverFile.length());

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", serverFile.getName());
			response.setHeader(headerKey, headerValue);

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			log.info("Server File Location=" + serverFile.getAbsolutePath());

			msg = msg + "You successfully downloaded file=" + serverFile.getName();

		} catch (Exception e) {
			log.error("Exception occured in while access file upload for emp id " + id
					+ ". Most likely file permissions issue ", e);
			msg = e + " <== error";
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}

	/**
	 * Delete server file
	 */
	@RequestMapping(value = "/admin/{id}/file/{filename}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteFile(@PathVariable(value = "id") long id,
			@PathVariable(value = "filename") String filename) {

		String msg = null;
		try {

			File dir = new File(rootPath + File.separator + id);
			File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);

			if (!dir.exists() && !serverFile.exists()) {
				msg = "File does not exist";
				log.info(msg + ". Server File Location=" + serverFile.getAbsolutePath());

				return new ResponseEntity<String>(msg, HttpStatus.NO_CONTENT);
			}
			FileUtils.forceDelete(serverFile);

			msg = msg + "Successfully Deleted file=" + filename;

		} catch (Exception e) {
			log.error("Exception occured in while deleting file " + filename + " for emp id " + id
					+ ". Most likely file permissions issue ", e);
			msg = e + " <== error";
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/admin/excel", method = RequestMethod.GET)
	public ResponseEntity<?> downloadEmployeeInfoIntoExcel(HttpServletRequest request, HttpServletResponse response) {

		String msg = null;
		List<Employee> employees;
		
		try (OutputStream outputStream = response.getOutputStream()){
			employees = employeeService.findAllEmployees();
			//System.out.println("Map:"+map);
			
			List<String> columnNames = new ArrayList<String>();
			columnNames.add("id");
			columnNames.add("name");
			columnNames.add("appArea");
			columnNames.add("dsId");
			columnNames.add("badgeEndDate");
			columnNames.add("email");
			columnNames.add("cubicleId");
			columnNames.add("managerId");
			columnNames.add("requestType");
			columnNames.add("status");
			columnNames.add("privileges");
			
			//Get the workbook instance for XLS file 
			XSSFWorkbook workbook = new XSSFWorkbook ();

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.createSheet("Employee_Details");

			//Create a new row in current sheet
			Row row = sheet.createRow(0);
			
			//Create a new cell in current row
			int i = 0;
			Cell cell = null;
			for(String columnName : columnNames) {
				cell = row.createCell(i++);
				cell.setCellValue(columnName);
			}
			
			int rowNum = 1;
			for (Employee employee : employees){
				row = sheet.createRow(rowNum++);
				
				int colNum = 0;
				for(String columnName : columnNames) {
					cell = row.createCell(colNum++);
	
					String methodName = "get"+columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
					Method method = employee.getClass().getDeclaredMethod(methodName);
					Object obj = method.invoke(employee);
					
					if (obj instanceof Number) {
						cell.setCellValue(((Number)obj).doubleValue());
					} else if (obj instanceof Date) {
						
						String excelFormatPattern = DateFormatConverter.convert(Locale.ENGLISH, "dd-MMM-yyyy");
						CellStyle cellStyle = workbook.createCellStyle();
						DataFormat poiFormat = workbook.createDataFormat();
					    cellStyle.setDataFormat(poiFormat.getFormat(excelFormatPattern));
					      
						cell.setCellValue((Date)obj);
						cell.setCellStyle(cellStyle);
					} else if (obj instanceof Object[]) {
						String objString = "";
						for(Object arrObj : (Object[])obj) {
							objString = objString + arrObj.toString()+",";
							
						}
						cell.setCellValue(objString);
					} else if (obj instanceof Collection) {
						String objString = "";
						for(Object arrObj : (Collection)obj) {
							objString = objString + arrObj.toString()+",";
						}
						cell.setCellValue(objString);
					} else {
						cell.setCellValue(obj.toString());
					}
				}
			}
			
			String mimeType = "application/vnd.ms-excel";;
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "Employee.xlsx");
			response.setHeader(headerKey, headerValue);

			// set content attributes for the response
			response.setContentType(mimeType);			
			workbook.write(outputStream);

			msg = msg + "You successfully downloaded file" ;
		} catch (Exception e) {
			log.error("Exception occured in while access file upload for emp id " 
					+ ". Most likely file permissions issue ", e);
			msg = e + " <== error";
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
}
