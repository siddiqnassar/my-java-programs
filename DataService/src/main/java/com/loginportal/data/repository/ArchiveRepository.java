package com.loginportal.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.loginportal.data.model.ArchiveUsersDocument;

@Repository
public interface ArchiveRepository extends MongoRepository<ArchiveUsersDocument, String> {

}
