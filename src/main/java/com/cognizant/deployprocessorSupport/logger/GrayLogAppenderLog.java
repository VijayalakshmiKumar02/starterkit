package com.cognizant.deployprocessorSupport.logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.deployprocessorSupport.common.ApplicationConstants;
import com.cognizant.deployprocessorSupport.utils.PropertyFileReader;
import com.cognizant.deployprocessorSupport.utils.SSLUtilities;
import com.cognizant.deployprocessorSupport.utils.Util;


/**
* File                          : GrayLogAppenderLog.java
* Description          : This class will support logging for starter kit application.
* Revision History :
* Version      Date            Author       Reason
* 0.1          01-Aug-2016     418618  Initial version
*/

@Component
public class GrayLogAppenderLog implements ILogger {
	
	private static ILogger logger;
	
	@Autowired
	private PropertyFileReader propertyFileReader;

	@Autowired
	private Util util;
	
	@Autowired
	private LoggerFactory loggerFactory;

	private final String ERROR_KEY = "Error Connecting GrayLog Server : ";

	@PostConstruct
	public void initStartUp() {
		logger = loggerFactory.getLoggerInstance();
	}


	/**
	 * Description:saveLog used to call logger API
	 * 
	 */
	public void saveLogToGrayLog(String level, String message) {
		try {
			Map<String, String> requestHeaders = new HashMap<String, String>();
			System.out.println("Saving gray logs....."+message);

			String postBody = null;

			JSONObject inputObject = new JSONObject();

			String requestURL = propertyFileReader.getMessage(ApplicationConstants.GRAY_LOG_URL);

			inputObject.put(ApplicationConstants.LEVEL, level);
			inputObject.put(ApplicationConstants.MESSAGE, message);
			inputObject.put(ApplicationConstants.APP_ID, "DeployProcessorSupportTool");

			postBody = inputObject.toJSONString();

			requestHeaders.put(ApplicationConstants.CONTENT_TYPE, ApplicationConstants.CONTENT_TYPE_APPLICATION_JSON);

			executePostCall(requestURL, postBody, requestHeaders);

		} catch (Exception e) {
			logger.info(ERROR_KEY + e.getMessage());
			e.printStackTrace();
		}
	}

	
	@Override
	public void debug(String msg) {
		saveLogToGrayLog("debug", msg);
	}

	@Override
	public void info(String msg) {
		saveLogToGrayLog("info", msg);
	}

	@Override
	public void error(String msg) {
		saveLogToGrayLog("error", msg);
	}
	
	
	private String executePostCall(String urlLink, String input, Map<String, String> connectionProperties){
		HttpURLConnection conn = null;
		OutputStream os =null;
		try {
			
			URL url = new URL(urlLink);
			util.setSystemProperties();
			SSLUtilities.disableSSLCertificateChecking();
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(ApplicationConstants.POST_METHOD);
			boolean errorFlag=false;
			for (String headerKey : connectionProperties.keySet()) {
				conn.setRequestProperty(headerKey, connectionProperties.get(headerKey));
			}

			 os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader bufferedReader = null;
			int responseCode=Integer.valueOf(conn.getResponseCode());
			if (!Arrays.asList(ApplicationConstants.CONNECTION_ERRORS)
					.contains(responseCode)) {

				bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				errorFlag=true;
			}

			StringBuffer buffer = new StringBuffer();
			while (true) {
				final String line = bufferedReader.readLine();
				if (line == null)
					break;
				buffer.append(line);
			}
			conn.disconnect();
			os.close();		
			return buffer.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
