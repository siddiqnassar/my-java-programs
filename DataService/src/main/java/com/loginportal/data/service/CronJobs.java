package com.loginportal.data.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loginportal.data.model.AccountStatus;
import com.loginportal.data.model.ArchiveUser;
import com.loginportal.data.model.ArchiveUsersDocument;
import com.loginportal.data.model.User;
import com.loginportal.data.repository.ArchiveRepository;
import com.loginportal.data.repository.UserRepository;

@PropertySource("classpath:cron.properties")
@Service
@EnableScheduling
public class CronJobs {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ArchiveRepository archiveRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	private static Logger logger = LoggerFactory.getLogger(CronJobs.class);

	@Transactional
	@Scheduled(cron = "${cron.todelete}")
	public void unconfirmedToDelete() {
		logger.info("unconfirmedToDelete()");
		List<User> unconfirmedUserList = userRepository.getUsersByAccountStatus(AccountStatus.UNCONFIRMED);

		for (User user : unconfirmedUserList) {
			logger.info("{}", user);
		}

		userRepository.changeAccountStatus(AccountStatus.UNCONFIRMED, AccountStatus.DELETE);
	}

	@Transactional
	@Scheduled(cron = "${cron.topurge}")
	public void deleteToPurge() {
		logger.info("deleteToPurge()");
		List<User> deleteUserList = userRepository.getUsersByAccountStatus(AccountStatus.DELETE);

		for (User user : deleteUserList) {
			logger.info("{}", user);
		}

		userRepository.changeAccountStatus(AccountStatus.DELETE, AccountStatus.PURGE);
	}

	@Transactional
	@Scheduled(cron = "${cron.clean}")
	public void purgeUsers() {
		logger.info("purgeUsers()");
		List<User> purgeUserList = userRepository.getUsersByAccountStatus(AccountStatus.PURGE);

		if (!purgeUserList.isEmpty()) {
			List<ArchiveUser> archiveList = new ArrayList<>();

			for (User user : purgeUserList) {
				logger.info("{}", user);
				archiveList.add(new ArchiveUser(user));
			}

			ArchiveUsersDocument archiveUsersDocument = new ArchiveUsersDocument();
			archiveUsersDocument.setDate(new Timestamp((new Date()).getTime()));
			archiveUsersDocument.setUsers(archiveList);
			mongoTemplate.save(archiveUsersDocument);
			userRepository.purgeUsers(AccountStatus.PURGE);
		}
	}

	@Transactional
	public List<ArchiveUsersDocument> getArchives() {
		return mongoTemplate.findAll(ArchiveUsersDocument.class);
	}
}