package com.loginportal.data.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ArchiveUser {
	private Long userID;
	private String emailID;
	private String userRole = "user";
	private String firstName;
	private String lastName;
	private String phoneNo;
	private Date accountCreationTime;
	private String gender;
	private String maritalStatus;
	private String profession;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateOfBirth;

	public ArchiveUser() {
		super();
	}

	public ArchiveUser(User user) {
		this.userID = user.getUserID();
		this.emailID = user.getEmailID();
		this.userRole = user.getUserRole();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.phoneNo = user.getPhoneNo();
		this.accountCreationTime = new Date(user.getAccountCreationTime().getTime());
		this.gender = user.getGender();
		this.maritalStatus = user.getMaritalStatus();
		this.profession = user.getProfession();
		this.dateOfBirth = user.getDateOfBirth();
	}

	public Long getUserID() {
		return userID;
	}

	public String getEmailID() {
		return emailID;
	}

	public String getUserRole() {
		return userRole;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public Date getAccountCreationTime() {
		return accountCreationTime;
	}

	public String getGender() {
		return gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getProfession() {
		return profession;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setAccountCreationTime(Date accountCreationTime) {
		this.accountCreationTime = accountCreationTime;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "ArchiveUser [userID=" + userID + ", emailID=" + emailID + ", userRole=" + userRole + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNo=" + phoneNo + ", accountCreationTime="
				+ accountCreationTime + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", profession="
				+ profession + ", dateOfBirth=" + dateOfBirth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountCreationTime == null) ? 0 : accountCreationTime.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArchiveUser other = (ArchiveUser) obj;
		if (accountCreationTime == null) {
			if (other.accountCreationTime != null)
				return false;
		} else if (!accountCreationTime.equals(other.accountCreationTime))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (emailID == null) {
			if (other.emailID != null)
				return false;
		} else if (!emailID.equals(other.emailID))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}

}
