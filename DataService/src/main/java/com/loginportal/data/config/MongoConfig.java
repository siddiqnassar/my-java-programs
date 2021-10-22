//package com.loginportal.data.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
//
//import com.loginportal.data.mongoconverter.ArchiveUserReadConverter;
//import com.loginportal.data.mongoconverter.ArchiveUserWriteConverter;
//import com.mongodb.MongoClient;
//
//@Configuration
//public class MongoConfig {
//
//	@Value("${spring.data.mongodb.host}")
//	private String mongoHost;
//
//	@Value("${spring.data.mongodb.port}")
//	private int mongoPort;
//
//	@Value("${spring.data.mongodb.database}")
//	private String mongoDatabase;
//
//	@Autowired
//	MongoDbFactory mongoDbFactory;
//
//	@Bean
//	public MongoTemplate mongoTemplate() throws Exception {
//		MongoTemplate mongoTemplate = new MongoTemplate(mongo(), mongoDatabase);
//		MappingMongoConverter converter = (MappingMongoConverter) mongoTemplate.getConverter();
//		converter.setCustomConversions(mongoCustomConversions());
//		converter.afterPropertiesSet();
//
//		mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
//		return mongoTemplate;
//	}
//
//	@Bean
//	public MongoClient mongo() throws Exception {
//		return new MongoClient(mongoHost, mongoPort);
//	}
//
//	public MongoCustomConversions mongoCustomConversions() {
//		final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
//		converters.add(new ArchiveUserWriteConverter());
//		converters.add(new ArchiveUserReadConverter());
//		return new MongoCustomConversions(converters);
//	}
//}
