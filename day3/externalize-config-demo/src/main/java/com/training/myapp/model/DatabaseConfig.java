package com.training.myapp.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix="db")
@Data @NoArgsConstructor @AllArgsConstructor
public class DatabaseConfig {
    
	private String url;
	private String username;
	private String password;
	private String driverClass;
}
