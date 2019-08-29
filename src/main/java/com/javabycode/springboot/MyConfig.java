package com.javabycode.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class MyConfig {

	@Bean
	ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
		t.setCorePoolSize(10);
		t.setMaxPoolSize(100);
		t.setQueueCapacity(50);
		t.setAllowCoreThreadTimeOut(true);
		t.setKeepAliveSeconds(120);
		return t;
	}
}