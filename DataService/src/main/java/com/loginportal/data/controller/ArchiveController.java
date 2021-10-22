package com.loginportal.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginportal.data.model.ArchiveUsersDocument;
import com.loginportal.data.service.CronJobs;

@RestController
@RequestMapping("/api/data/archive")
public class ArchiveController {
	@Autowired
	CronJobs cronJobs;

	@GetMapping("/list-all")
	public List<ArchiveUsersDocument> getArchives() {
		return cronJobs.getArchives();
	}
}
