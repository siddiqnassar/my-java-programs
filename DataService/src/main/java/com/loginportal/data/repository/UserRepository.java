package com.loginportal.data.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loginportal.data.model.AccountStatus;
import com.loginportal.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.emailID = :emailID")
	Optional<User> getUserByEmailID(@Param("emailID") String emailID);

	@Query("select u from User u where u.userID = :userID")
	Optional<User> getUserByUserID(@Param("userID") Long userID);

	@Modifying
	@Query("delete from User u where u.userID = :userID")
	void deleteUser(@Param("userID") Long userID);

	@Modifying
	@Query("update User u set u.accountStatus = :accountStatus where u.userID = :userID")
	int updateAccountStatus(@Param("accountStatus") AccountStatus accountStatus, @Param("userID") Long userID);

	@Query("select u from User u")
	List<User> getUsers();

	@Modifying
	@Query("update User u set u.firstName = :firstName, u.lastName = :lastName, u.phoneNo = :phoneNo, u.gender = :gender, u.maritalStatus = :maritalStatus, u.profession = :profession, u.dateOfBirth = :dateOfBirth where u.userID = :userID")
	int updateUser(@Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("phoneNo") String phoneNo, @Param("gender") String gender,
			@Param("maritalStatus") String maritalStatus, @Param("profession") String profession,
			@Param("dateOfBirth") Date dateOfBirth, @Param("userID") Long userID);

	@Modifying
	@Query("delete from User u where u.accountStatus = ?1")
	void purgeUsers(AccountStatus accountStatus);

	@Query("select u from User u where u.accountStatus = :accountStatus")
	List<User> getUsersByAccountStatus(@Param("accountStatus") AccountStatus accountStatus);

	@Modifying
	@Query("update User u set u.accountStatus = :newStatus where u.accountStatus = :oldStatus")
	void changeAccountStatus(@Param("oldStatus") AccountStatus oldStatus, @Param("newStatus") AccountStatus newStatus);
}
