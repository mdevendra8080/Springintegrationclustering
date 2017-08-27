package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;

@Configuration
@Profile("cloud")
@ComponentScan(basePackageClasses = LockRepository.class)
class JdbcLockConfiguration {
	private static Logger logger = LoggerFactory.getLogger(JdbcLockConfiguration.class);
	@Bean
	public JdbcLockRegistry jdbcLockRegistry(LockRepository lockRepository) {
		logger.info("INSIDE JdbcLockConfiguration");
		return new JdbcLockRegistry(lockRepository);
	}

}