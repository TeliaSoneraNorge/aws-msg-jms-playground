package pl.ene.awsmsgjmsplayground.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:jms-sqs-messaging.xml")
public class AwsMsgJmsPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsMsgJmsPlaygroundApplication.class, args);
	}
}
