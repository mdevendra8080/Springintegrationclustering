package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.leader.Candidate;
import org.springframework.integration.leader.event.DefaultLeaderEventPublisher;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.integration.support.locks.LockRegistry;

@SpringBootApplication
public class CronServiceLeaderApplication {

	@Bean
	public LockRegistryLeaderInitiator leaderInitiator(LockRegistry locks,
			Candidate candidate, ApplicationEventPublisher applicationEventPublisher) {
		LockRegistryLeaderInitiator initiator = new LockRegistryLeaderInitiator(locks,
				candidate);
		initiator.setLeaderEventPublisher(
				new DefaultLeaderEventPublisher(applicationEventPublisher));
		return initiator;
	}

	public static void main(String[] args) {
		SpringApplication.run(CronServiceLeaderApplication.class, args);
	}
}