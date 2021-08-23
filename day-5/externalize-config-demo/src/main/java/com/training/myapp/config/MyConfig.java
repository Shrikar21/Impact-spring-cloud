package com.training.myapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:myconfig.properties")
@Data
public class MyConfig {
	
	@Value("${remote.server.ip}")
	private String remoteServerIp;
	
	@Value("${remote.server.username}")
	private String remoteServerUsername;
	
	@Value("${remote.server.password}")
	private String remoteServerPassword;
	
	@Value("${web.service.endpoint}")
	private String webServiceUrlEndpoint;	
}
