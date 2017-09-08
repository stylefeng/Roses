package com.stylefeng.roses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class RosesApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RosesApiGatewayApplication.class, args);
	}
}
