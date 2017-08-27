package com.example;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
class Scheduler implements SchedulingConfigurer, Closeable {

	private volatile ScheduledTaskRegistrar taskRegistrar;
	private volatile ExecutorService pool;
	private volatile boolean running = false;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		this.taskRegistrar = taskRegistrar;
	}

	public void addTask(Runnable task, String expression) {
		if (running) {
			taskRegistrar.scheduleCronTask(new CronTask(task, expression));
		}
		else {
			taskRegistrar.addCronTask(new CronTask(task, expression));
		}
	}

	public void start() {
		running = true;
		if (taskRegistrar != null) {
			pool = Executors.newScheduledThreadPool(10);
			taskRegistrar.setScheduler(pool);
			taskRegistrar.afterPropertiesSet();
		}
	}

	public void stop() {
		if (taskRegistrar != null) {
			taskRegistrar.destroy();
			pool.shutdown();
			pool = null;
		}
		running = false;
	}

	@Override
	public void close() throws IOException {
		stop();
	}

}