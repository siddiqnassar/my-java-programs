package com.nassar.controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nassar.employee.exception.GlobalExceptionHandler;
import com.nassar.employee.model.Address;
import com.nassar.employee.model.AddressRequest;
import com.nassar.employee.model.Employee;
import com.nassar.employee.model.GenericResponse;
import com.nassar.employee.model.ErrorResponse;
import com.nassar.employee.service.EmployeeService;


@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
//	@GetMapping("/")
//	public ResponseEntity<Long> getAllEmp() {
//		return ResponseEntity.status(HttpStatus.OK).body(employeeService.saveAddressAndUser());
//	}
	
	@PostMapping(path = "saveEmp", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> saveEmployeeDetails(@RequestBody Employee employee) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employee));	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((long) 0);	
		}
	}
	
	@PostMapping(path = "saveAddress", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> saveAddress(@RequestBody AddressRequest addressReq) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveAddress(addressReq));
	}
	
	@PutMapping("updateEmployee")
	public ResponseEntity<GenericResponse> updateEmployee(@RequestBody Employee employee) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employee));	
		} catch (Exception ex) {
			logger.error("Update Employee Failed");
		}
		return null;
		
	}
	
	@GetMapping("getallEmp")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());	
		} catch (Exception ex) {
			logger.error("Update Employee Failed");
		}
		return null;
		
	}
	
	@GetMapping("getallAddress")
	public ResponseEntity<Page<Address>> getAllAddress(@RequestParam int pageCount, @RequestParam int elementsPerPage, @RequestParam String sort_by) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllAddress(pageCount,elementsPerPage,sort_by));	
		} catch (Exception ex) {
			logger.error("Update Employee Failed");
		}
		return null;
		
	}
	
	@GetMapping("getEmp/{id}")
	public ResponseEntity<GenericResponse> getEmployeeById(@PathVariable("id") Long employee_id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(employee_id));	
		} catch (Exception ex) {
			logger.error("Update Employee Failed");
		}
		return null;
		
	}
	
	
	@PutMapping("updateAddress")
	public ResponseEntity<GenericResponse> updateAddress(@RequestBody AddressRequest addr) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateAddress(addr));	
		} catch (Exception ex) {
			logger.error("Update Address Failed");
		}
		return null;
		
	}
	
	@GetMapping("getAddress/{id}")
	public ResponseEntity<GenericResponse> getAddressById(@PathVariable("id") Long address_id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAddressById(address_id));	
		} catch (Exception ex) {
			logger.error("Update Employee Failed");
		}
		return null;
		
	}
	
	@DeleteMapping("deleteEmp/{id}")
	public ResponseEntity<GenericResponse> deleteEmployeeById(@PathVariable("id") Long employee_id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployeeById(employee_id));	
		} catch (Exception ex) {
			logger.error("Update Employee Failed");
		}
		return null;
		
	}
	
	@DeleteMapping("deleteAddress/{id}")
	public ResponseEntity<GenericResponse> deleteAddressById(@PathVariable("id") Long address_id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteAddressById(address_id));	
		} catch (Exception ex) {
			logger.error("getEmployeeById Failed");
		}
		return null;
		
	}
	
	//Testing the Global Exception here
	@SuppressWarnings("unused")
	@GetMapping(path = "/error") 
	public ResponseEntity<ErrorResponse> getError() {
		try {
			ErrorResponse err = null;
			if (err == null) {
				throw new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Employee from Controller Not found");
			}
			
		} catch (Exception ex) {
			throw new ErrorResponse(ex != null?((ErrorResponse) ex).getStatusCode():HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Employee Not found"));
	}
}
