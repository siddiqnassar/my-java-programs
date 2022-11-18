package com.nassar.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nassar.employee.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query(value = "select * from address ad where ad.employee_id=:employee_id", nativeQuery = true)
	public List<Address> getAddressByEmpId(long employee_id);
}
