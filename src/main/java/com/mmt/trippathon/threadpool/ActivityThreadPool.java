package com.mmt.trippathon.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class ActivityThreadPool {

	private ExecutorService executor;

	private ActivityThreadPool() {
		executor = Executors.newFixedThreadPool(50);
	}

	public ExecutorService getExecutor() {
		return executor;
	}

}