package com.loginportal.data.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MultipleLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long multipleLoginID;
	private Long userID;
	private String emailID;
	private String userAgent;
	private String ipAddress;
	private String location;
	private Timestamp loggedInTime;

	public MultipleLogin() {
		super();
	}

	public long getMultipleLoginID() {
		return multipleLoginID;
	}

	public void setMultipleLoginID(long multipleLoginID) {
		this.multipleLoginID = multipleLoginID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getLoggedInTime() {
		return loggedInTime;
	}

	public void setLoggedInTime(Timestamp loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	@Override
	public String toString() {
		return "MultipleLogin [multipleLoginID=" + multipleLoginID + ", userID=" + userID + ", emailID=" + emailID
				+ ", userAgent=" + userAgent + ", ipAddress=" + ipAddress + ", location=" + location + ", loggedInTime="
				+ loggedInTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((loggedInTime == null) ? 0 : loggedInTime.hashCode());
		result = prime * result + (int) (multipleLoginID ^ (multipleLoginID >>> 32));
		result = prime * result + ((userAgent == null) ? 0 : userAgent.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		MultipleLogin other = (MultipleLogin) obj;
		if (emailID == null) {
			if (other.emailID != null)
				return false;
		} else if (!emailID.equals(other.emailID))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (loggedInTime == null) {
			if (other.loggedInTime != null)
				return false;
		} else if (!loggedInTime.equals(other.loggedInTime))
			return false;
		if (multipleLoginID != other.multipleLoginID)
			return false;
		if (userAgent == null) {
			if (other.userAgent != null)
				return false;
		} else if (!userAgent.equals(other.userAgent))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

}
