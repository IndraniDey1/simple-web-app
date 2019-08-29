package com.javabycode.springboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootApplication
public class MyWebApplication extends SpringBootServletInitializer {
	final static Logger logger = Logger.getLogger(MyWebApplication.class);

	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public static void main(String[] args) throws Exception {
		logger.debug("Starting MyWebApplication: .....");
		ApplicationContext ctx = SpringApplication.run(MyWebApplication.class, args);
		MyWebApplication application = ctx.getBean(MyWebApplication.class);
		logger.debug("Before : Invoking go method");

		application.go();
		logger.debug("After : Invoking go method");
	}

	@SuppressWarnings("unchecked")
	private void go() {
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		for (int i = 0; i < 2; i++) {
			futures.add((Future<Integer>) threadPoolTaskExecutor.submit(new MyWorker()));
		}
		logger.info("******Blocking until job is  completed on runner thread********");
		for (Future<Integer> currFuture : futures) {
			try {
				currFuture.get();
			} catch (InterruptedException e) {
				logger.error("InterruptedException", e);
			} catch (ExecutionException e) {
				logger.error("ExecutionException", e);
			}
		}
	}
}
