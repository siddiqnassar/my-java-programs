package com.loginportal.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loginportal.data.model.MultipleLogin;

@Repository
public interface MultipleLoginRepository extends JpaRepository<MultipleLogin, Long> {
	@Query("select ml from MultipleLogin ml where ml.ipAddress = :ipAddress and ml.userID = :userID")
	public Optional<MultipleLogin> getMultipleLoginByIPAndUserID(@Param("ipAddress") String ipAddress,
			@Param("userID") Long userID);
}
