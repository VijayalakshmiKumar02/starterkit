package com.cognizant.deployprocessorSupport.connection;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.eclipse.egit.github.core.Authorization;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.deployprocessorSupport.common.ApplicationConstants;
import com.cognizant.deployprocessorSupport.logger.ILogger;
import com.cognizant.deployprocessorSupport.logger.LoggerFactory;
import com.cognizant.deployprocessorSupport.utils.PropertyFileReader;
import com.cognizant.deployprocessorSupport.utils.Util;


@Component
public class GistConnection {
	
	private static ILogger logger;

	@Autowired
	private LoggerFactory loggerFactory;

	private String authToken;

	@Autowired
	private PropertyFileReader propertyFileReader;

	@Autowired
	private Util util;

	@PostConstruct
	public void initConnection() {
		logger = loggerFactory.getLoggerInstance();
		createConnection();
	}

	public String getAuthToken() {
		return authToken;
	}

	public GistService getGistInstance() {

		GistService gistService = new GistService();
		gistService.getClient().setOAuth2Token(getAuthToken());
		return gistService;
	}

	public void createConnection()  {
		util.setSystemProperties();
		
		Integer maxCount = Integer
				.valueOf(propertyFileReader.getMessage(ApplicationConstants.GIST_CONNECTION_FREQUENCY));
		Authorization auth = null;
		for (int count = 0; count < maxCount; count++) {
			auth = getAuthorization();
			if (auth != null) {
				break;
			}
			try {
				Long delayFrequency = Long
						.valueOf(propertyFileReader.getMessage(ApplicationConstants.GIST_CONNECTION_DELAY));
				Thread.sleep(delayFrequency);
			} catch (Exception e) {

			}
		}

		if (auth != null) {
			authToken = auth.getToken();
		} else {
			logger.error("Gist Connection Failed!");
		}

	}

	public Authorization getAuthorization() {
		OAuthService oauthService = new OAuthService();
		oauthService.getClient().setCredentials(propertyFileReader.getMessage(ApplicationConstants.GIST_USERNAME),
				propertyFileReader.getMessage(ApplicationConstants.GIST_PASSWORD));
		Authorization auth = new Authorization();
		auth.setNote(new Timestamp(new Date().getTime()).toString());
		auth.setScopes(Arrays.asList(ApplicationConstants.GIST_KEY));
		try {
			auth = oauthService.createAuthorization(auth);
		} catch (Exception e) {
			return null;
		}
		return auth;
	}
}
