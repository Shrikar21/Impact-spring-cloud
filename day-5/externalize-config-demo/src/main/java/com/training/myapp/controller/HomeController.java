package com.training.myapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.myapp.config.MyConfig;
import com.training.myapp.model.DatabaseConfig;

@RestController
public class HomeController {
	
	@Value("${app.version: 0.0.1.SNAPSHOT}")
	private String appVersion;
	
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.author}")
	private String appAuthor;
	
	@Value("This is a static value")
	private String message;
	
	@Value("#{${database.config}}")
	private Map<String, String> databaseConfig;
	
	@Value("${server.ips}")
	private List<String> serverIPs;
	
	@Value("#{${database.config}.dburl}")
	private String dburl;
	
	@Autowired	
	private DatabaseConfig config;
	
	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private Environment env;
	 
	@GetMapping("/properties")
	public String getProperties() {
		// return "App Version => " + appVersion + " | App Name => " + appName + " | App Author Name => " + appAuthor + " | Message => " + message + " | dburl => " + dburl ;
		return "App Version => " + env.getProperty("app.version") + " | App Name => " + env.getProperty("app.name") 
		            + " | App Author Name => " + env.getProperty("app.author") + " | Java => " + env.getProperty("java");
 	}
	
	@GetMapping("/dbconfig")
	public Map<String, String> getDatabaseConfig() {
		return databaseConfig;
	}
	
	@GetMapping("/server-ips")
	public List<String> getServerIps() {
		return serverIPs;
	}
	
	@GetMapping("/database-config")
	public String getDatabaseConfiguration() {
		return config.toString();
	}
	
	@GetMapping("/my-config")
	public String getMyConfig() {
		return myConfig.toString();
	}
	
	@GetMapping("/env")
	public String getEnvironmentVariables() {
		
		Map<String,String> map = System.getenv();
		// map.forEach((k, v) -> System.out.println(k + " - " + v));
		map.forEach((k, v) -> System.out.println(k + " => " + env.getProperty(k)));
		return "hello";
	}
}









