package com.example.demo.configuration;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class HelloWorldAmqConfig {
	public static final String HELLO_WORLD_QUEUE = "hello.world.queue";
	@Bean
	public Queue helloWorldJMSQueue() {
		return new ActiveMQQueue(HELLO_WORLD_QUEUE);

	}

}
