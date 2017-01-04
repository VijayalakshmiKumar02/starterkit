package com.cognizant.deployprocessorSupport.scheduler;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cognizant.deployprocessorSupport.common.ApplicationConstants;
import com.cognizant.deployprocessorSupport.connection.GistConnection;
import com.cognizant.deployprocessorSupport.logger.ILogger;
import com.cognizant.deployprocessorSupport.logger.LoggerFactory;
import com.cognizant.deployprocessorSupport.utils.PropertyFileReader;

/**
 * File : GistCleanupScheduler.java Description : Scheduler class for Gist old
 * logs cleanup Revision History : Version Date Author Reason 0.1 28-July-2016
 * 324401 Initial version
 */

@Component
public class GistCleanupScheduler {
	private static ILogger logger;

	@Autowired
	private LoggerFactory loggerFactory;

	@Autowired
	private GistConnection gistConnection;

	@Autowired
	private PropertyFileReader propertyFileReader;

	@PostConstruct
	public void initStartUp() throws Exception {
		logger = loggerFactory.getLoggerInstance();
		logger.info("Initializing Gist Cleanup Scheduler!");
		String configuredHours = propertyFileReader.getMessage(ApplicationConstants.CLEAN_OLD_GIST_IN_HOURS);
		int minutesBack = (int) (Float.parseFloat(configuredHours) * 60); // Converted to Minutes
		if (minutesBack > 0) {
			cronHandlerGIST();
		} else {
			throw new IllegalStateException(ApplicationConstants.CONFIG_ERROR_KEY + configuredHours + ApplicationConstants.HOURS_KEY);
		}

	}

	/**
	 * Cron scheduled on the top of every hour of every day for deleting old
	 * gist files
	 */
	// @Scheduled(cron = "0 0 * * * *")
	@Scheduled(cron = "${gist.cleanup.cron}")
	public void cronHandlerGIST() {
		try {
			int minutesBack = (int) (Float.parseFloat(propertyFileReader.getMessage(ApplicationConstants.CLEAN_OLD_GIST_IN_HOURS)) * 60); // Converted to Minutes																																// Minutes
			String dpFileName = propertyFileReader.getMessage(ApplicationConstants.CLEAN_GIST_DEPLOY_PROCESSOR_FILENAME);
			String microServiceFile = propertyFileReader.getMessage(ApplicationConstants.CLEAN_GIST_MICRO_SERVICE_FILENAME);
			logger.info(ApplicationConstants.CLEANUP_STARTED_KEY + dpFileName + " & " + microServiceFile + " : User : " + propertyFileReader.getMessage(ApplicationConstants.GIST_USERNAME));
			GistService gistService = gistConnection.getGistInstance();
			List<Gist> gistList = gistService.getGists(propertyFileReader.getMessage(ApplicationConstants.GIST_USERNAME));
			ListIterator<Gist> itr = gistList.listIterator();
			boolean notDeleted = true;
			while (itr.hasNext()) {
				Gist gist = itr.next();
				Map<String, GistFile> filesMap = gist.getFiles();
				boolean isMarked = false;
				String zipFileName = ApplicationConstants.EMPTY_STRING;
				for (Map.Entry<String, GistFile> entry : filesMap.entrySet()) {
					GistFile file = entry.getValue();
					if (file.getFilename().equalsIgnoreCase(dpFileName) || file.getFilename().equalsIgnoreCase(microServiceFile)) {
						isMarked = true;
					} else {
						zipFileName = file.getFilename();
					}
				}
				if (isMarked) {
					Date lastUpdated = gist.getUpdatedAt();
					Date today = new Date();
					int minuteDifference = (int) ((today.getTime() - lastUpdated.getTime()) / 60000);
					if ((minuteDifference > minutesBack)) {
						if (zipFileName.equalsIgnoreCase(ApplicationConstants.EMPTY_STRING)) {
							logger.info(ApplicationConstants.DELETED_SUCCESS_MESSAGE_KEY + gist.getId());
						} else {
							logger.info(ApplicationConstants.DELETED_SUCCESS_MESSAGE_KEY + zipFileName);
						}
						gistService.deleteGist(gist.getId());
						notDeleted = false;
					}
				}
			}
			if (notDeleted) {
				logger.info(ApplicationConstants.NOTHING_TO_DELETE);
			}

		} catch (Exception e) {
			logger.error(ApplicationConstants.ERROR_DELETING_OLD_GIST_KEY + e.getMessage());
		}
	}
}
