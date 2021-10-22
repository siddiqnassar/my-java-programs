package com.loginportal.data.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ArchiveUsersDocument {

	@Id
	private String archiveId;
	private Date date;
	private List<ArchiveUser> users;

	public ArchiveUsersDocument() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ArchiveUser> getUsers() {
		return users;
	}

	public void setUsers(List<ArchiveUser> users) {
		this.users = users;
	}

	public String getArchiveId() {
		return archiveId;
	}

	@Override
	public String toString() {
		return "ArchiveUsersDocument [archiveId=" + archiveId + ", date=" + date + ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((archiveId == null) ? 0 : archiveId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		ArchiveUsersDocument other = (ArchiveUsersDocument) obj;
		if (archiveId == null) {
			if (other.archiveId != null)
				return false;
		} else if (!archiveId.equals(other.archiveId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

}
