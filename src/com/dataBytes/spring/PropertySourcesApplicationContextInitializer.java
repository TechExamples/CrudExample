package com.dataBytes.spring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

public class PropertySourcesApplicationContextInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	private static final Logger log = LoggerFactory.getLogger(PropertySourcesApplicationContextInitializer.class);

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		String profile = System.getenv("spring.profiles.active");
		if (profile == null) {
			RuntimeException e = new RuntimeException("Application Enviroment Configuration Error."
					+ "Environment variable 'spring.profiles.active' not set properly ");
			
			log.error("Environment variable 'spring.profiles.active' not set properly. "
					+ "Ex: For *nix, export spring.profiles.active=PROD . Refer README.txt for configuration",e);
			throw e;
		}
		profile = profile.trim();
		profile = profile.length() > 0 ? profile : System.getProperty("spring.profiles.active");
		System.setProperty("spring.profiles.active", profile);
		
		log.info("Add env variable spring.profiles.active value "+ profile+" to propterties");
		
		//PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
		//Properties properties = new Properties();
		//properties.setProperty("spring.profiles.active", profile);

		//applicationContext.addBeanFactoryPostProcessor(configurer);

		//applicationContext.refresh();
	}
}
