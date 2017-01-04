package com.cognizant.deployprocessorSupport.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.deployprocessorSupport.common.ApplicationConstants;

@Component
public class Util {
	@Autowired
	private PropertyFileReader propertyFileReader;

	public void setSystemProperties() {
		if (ApplicationConstants.TRUE_STRING.equalsIgnoreCase(propertyFileReader.getMessage(ApplicationConstants.CONFIG_USE_PROXY))) {
			System.setProperty("https.proxyHost", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_HOST));
			System.setProperty("https.proxyPort", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_PORT));
			System.setProperty("https.proxyUser", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_USER));
			System.setProperty("https.proxyPassword", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_PASS));

			System.setProperty("http.proxyHost", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_HOST));
			System.setProperty("http.proxyPort", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_PORT));
			System.setProperty("http.proxyUser", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_USER));
			System.setProperty("http.proxyPassword", propertyFileReader.getMessage(ApplicationConstants.CONFIG_PROXY_PASS));
		}
	}
}