package com.cognizant.deployprocessorSupport.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.deployprocessorSupport.common.ApplicationConstants;
import com.cognizant.deployprocessorSupport.utils.PropertyFileReader;


/**
* File                          : LoggerFactory.java
* Description          : Factory class to select the logger based on the property file values
* Revision History :
* Version      Date            Author       Reason
* 0.1          09-Jun-2016     144690  Initial version
*/
@Component
public class LoggerFactory {
	
	@Autowired
	private PropertyFileReader propertyFileReader;
	
	@Autowired
	private ILogger fileAppenderLog;

	@Autowired
	private ILogger grayLogAppenderLog;
	
	/**
	 * method to choose the logger type based on the property value
	 * @return
	 */
	public ILogger getLoggerInstance() {
		String loggerMode = propertyFileReader.getMessage(ApplicationConstants.LOGGER_SELECT);
		if (loggerMode.equalsIgnoreCase(ApplicationConstants.FILE)) {
			return fileAppenderLog;
		} 	
		else if (loggerMode.equalsIgnoreCase(ApplicationConstants.GRAYLOG)) {
			return grayLogAppenderLog;
		}
		else {
			return null;
		}
	}
}
