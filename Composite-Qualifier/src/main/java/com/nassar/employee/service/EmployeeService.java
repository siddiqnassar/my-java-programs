package com.nassar.employee.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nassar.employee.model.Address;
import com.nassar.employee.model.AddressRequest;
import com.nassar.employee.model.Employee;
import com.nassar.employee.model.GenericResponse;
import com.nassar.employee.repository.AddressRepository;
import com.nassar.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	GenericResponse genericResponse;
	
	@Autowired
	private AddressRepository addressRepository;
	

	public Long saveEmployee(Employee emp) {
		try {
			Employee savedEmp = employeeRepository.save(emp);
			return savedEmp.getEmployee_id();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return (long) 0;
	}
	
	public Long saveAddress(AddressRequest addr) {
		try {
			@SuppressWarnings("deprecation")
			Employee emp = employeeRepository.getById(addr.getEmployee_id());
			if(emp.getEmployee_id()!=null) {
				Address savedAddr = addressRepository.save(createAddressBody(addr,emp));
				return savedAddr.getAddress_id();	
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return (long) 0;
	}
	
	public GenericResponse updateEmployee(Employee emp) {
		try {
			Employee employee = employeeRepository.getReferenceById(emp.getEmployee_id());
			if(employee != null) {
				employee = employeeRepository.save(emp);
				return createObjectResponse(employee, "updated the Employee info", HttpStatus.OK.value());
			} else {
				return createObjectResponse(employee, "employee didnt exist", HttpStatus.NOT_FOUND.value());
			}
		} catch (Exception ex) {
			logger.error("updateEmployee function error :- {} ", ex.getMessage() );
			return createObjectResponse(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
		}
	}
	
	public GenericResponse updateAddress(AddressRequest addr) {
		try {
			Employee emp = employeeRepository.getReferenceById(addr.getEmployee_id());
			Address newAddress = createAddressBody(addr, emp);
			Address oldAddress = addressRepository.getReferenceById(newAddress.getAddress_id());
			
			if(oldAddress != null) {
				newAddress = addressRepository.save(newAddress);
				return createObjectResponse(newAddress, "updated the Address info", HttpStatus.OK.value());
			} else {
				return createObjectResponse(newAddress, "Address id didnt exist", HttpStatus.NOT_FOUND.value());
			}
		} catch (Exception ex) {
			logger.error("updateAddress function error :- {} ", ex.getMessage() );
			return createObjectResponse(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
		}
	}
	
	public Address createAddressBody(AddressRequest addReq,Employee emp) {
		if(emp !=null ) {
			return new Address( addReq.getAddress_id(), addReq.getCity(), addReq.getStreetName(), emp);
		}
		return null;
		
	}

	public GenericResponse getEmployeeById(Long employee_id) {
		try {
			Employee employee = employeeRepository.getReferenceById(employee_id);
			if(employee !=null && employee.getEmployee_id() != 0 && employee.getEmployee_id() != null && !employee.getName().contains("null")) {
				return createObjectResponse(employee, "Able to get the Employee info", HttpStatus.OK.value());
			}
		} catch (Exception ex) {
			logger.error("getEmployeebyId function error :- {} ", ex.getMessage() );
			return createObjectResponse(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
		}
		return createObjectResponse(null, "unable to get the Employee Details", HttpStatus.NOT_FOUND.value());
	}
	
	public GenericResponse createObjectResponse(Object obj, String message, int statusCode) {
		genericResponse.setObj(obj);
		genericResponse.setMessage(message);
		genericResponse.setStatusCode(statusCode);
		
		return genericResponse;
	}

	public GenericResponse getAddressById(Long address_id) {
		try {
			Address add = addressRepository.getReferenceById(address_id);
			if(add !=null && add.getAddress_id() != 0 && add.getAddress_id() != null && !add.getCity().contains("null")) {
				return createObjectResponse(add, "Able to get the Address info", HttpStatus.OK.value());
			}
		} catch (Exception ex) {
			logger.error("getAddressById function error :- {} ", ex.getMessage() );
			return createObjectResponse(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
		}
		return createObjectResponse(null, "unable to get the Address Details", HttpStatus.NOT_FOUND.value());
	}

	public GenericResponse deleteEmployeeById(Long employee_id) {
		try {
			Employee employee = employeeRepository.getReferenceById(employee_id);
			
			if(employee !=null && employee.getEmployee_id() != 0 && employee.getEmployee_id() != null && !employee.getName().contains("null")) {
				logger.info("Employee Info exists in DB");
				//return createObjectResponse(employee, "Able to get the Employee info", HttpStatus.OK.value());
				List<Address> addresses = addressRepository.getAddressByEmpId(employee_id);
				System.out.println("Address are " + addresses.size());
				if(addresses.size() <= 0) {
					employeeRepository.deleteById(employee_id);
					return createObjectResponse(null, "successfully deleted the employee from DB", HttpStatus.OK.value());
				} else {
					return createObjectResponse(null, "Please delete child table elements of Employee DB", HttpStatus.NO_CONTENT.value());
				}
			} else {
				logger.info("Employee Info Doesn't exists in DB");
			}
		} catch (Exception ex) {
			logger.error("deleteEmployeeById function error :- {} ", ex.getMessage() );
			return createObjectResponse(null, ex.getMessage(), ex.getMessage().contains("ConstraintViolationException")? HttpStatus.NOT_FOUND.value(): HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return createObjectResponse(null, "unable to get the Employee Details", HttpStatus.NOT_FOUND.value());
	}
	
	public GenericResponse deleteAddressById(Long address_id) {
		try {
			addressRepository.deleteById(address_id);
			return createObjectResponse(null, "successfully deleted the address from DB", HttpStatus.OK.value());
		} catch (Exception ex) {
			logger.error("deleteAddressById function error :- {} ", ex.getMessage() );
			return createObjectResponse(null, ex.getMessage(), ex.getMessage().contains("ConstraintViolationException")? HttpStatus.NOT_FOUND.value(): HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	public List<Employee> getAllEmployees() {
		try {
			return employeeRepository.findAll();
		} catch (Exception ex) {
			logger.error("getAllEmployees function error :- {} ", ex.getMessage() );
			return null;
		}
	}

	public Page<Address> getAllAddress(int pageCount, int elementsPerPage, String sort_by) {
		try {
			Pageable pageElements = PageRequest.of(pageCount, elementsPerPage, Sort.by(sort_by));
			return addressRepository.findAll(pageElements);
		} catch (Exception ex) {
			logger.error("getAllAddress function error :- {} ", ex.getMessage() );
			return null;
		}
	}
	
}
