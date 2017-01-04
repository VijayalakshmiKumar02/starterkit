package com.cognizant.deployprocessorSupport;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableScheduling
public class DeployProcessorSupportToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeployProcessorSupportToolApplication.class, args);
	}
	/**
	 * method to set the Locale value which will be used across the application
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	/**
	 * Method which will be used to load the property file
	 * 
	 * @return
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] propertyArray = new String[1];
		propertyArray[0] = "classpath:/properties/supportTool";
		//propertyArray[1] = "classpath:/properties/error";
		messageSource.setBasenames(propertyArray);
		return messageSource;
	}

}
