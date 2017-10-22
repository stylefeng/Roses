package com.stylefeng.roses.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.stylefeng.roses")
public class RosesUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosesUserApplication.class, args);
	}
}
