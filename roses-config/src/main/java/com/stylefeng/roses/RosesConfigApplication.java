package com.stylefeng.roses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RosesConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosesConfigApplication.class, args);
	}
}
