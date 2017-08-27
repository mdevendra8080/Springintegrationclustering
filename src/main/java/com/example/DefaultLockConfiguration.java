package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.support.locks.DefaultLockRegistry;

@Configuration
@Profile("!cloud")
class DefaultLockConfiguration {
	private static Logger logger = LoggerFactory.getLogger(DefaultLockConfiguration.class);
	
	@Bean
	public DefaultLockRegistry defaultLockRegistry() {
		logger.info("INSIDE DefaultLockConfiguration");
		return new DefaultLockRegistry();
	}
}