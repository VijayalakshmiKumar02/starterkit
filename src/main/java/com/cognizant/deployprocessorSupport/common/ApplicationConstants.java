package com.cognizant.deployprocessorSupport.common;

/**
 * File : ApplicationConstants.java Description : ApplicationConstants class to
 * maintain all static values for CRON Revision History : Version Date Author
 * Reason 0.1 09-Jun-2016 324401 Initial version
 */
public class ApplicationConstants {
	/**
	 * Creating constant variable for CLEAN_OLD_GIST_IN_HOURS
	 */
	public static final String CLEAN_OLD_GIST_IN_HOURS = "gist_cleanFiles_hours";
	/**
	 * Creating constant variable for CLEAN_GIST_FILENAME
	 */
	public static final String CLEAN_GIST_DEPLOY_PROCESSOR_FILENAME = "gist_cleanDeployProcessor_filename";
	/**
	 * Creating constant variable for CLEAN_GIST_FILENAME
	 */
	public static final String CLEAN_GIST_MICRO_SERVICE_FILENAME = "gist_cleanMicroService_filename";
	/**
	 * Creating constant variable for gist.connection.frequency
	 */
	public static final String GIST_CONNECTION_FREQUENCY = "gist.connection.frequency";
	/**
	 * Creating constant variable for gist.connection.delay
	 */
	public static final String GIST_CONNECTION_DELAY = "gist.connection.delay";
	/**
	 * Creating constant variable for LOGGER_SELECT
	 */
	public static final String LOGGER_SELECT = "logger_select";
	/**
	 * Creating constant variable for Graylog
	 */
	public static final String GRAYLOG = "GrayLog";
	/**
	 * Creating constant variable for Logger URL
	 */
	public static final String GRAY_LOG_URL = "grayLog_url";
	public static final String LEVEL = "level";
	public static final String APP_ID = "appid";

	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
	public static final String INFO = "info";

	public static final String PRIORITY = "priority";

	public static final String CRITICAL = "Critical";
	/**
	 * Creating constant variable for MESSAGE
	 */
	public static final String MESSAGE = "message";
	
	public static final String POST_METHOD = "POST";
	
	/**
	 * Creating constant variable for nodejs
	 */
	public static final Integer[] CONNECTION_ERRORS = new Integer[] { 400, 404, 401, 500, 429, 409, 415, 504,405 };
	
	public static final String SSL_INSTANCE_KEY = "SSL";

	/**
	 * Creating constant variable for FILE
	 */
	public static final String FILE = "file";
	/**
	 * Creating constant variable for GIST
	 */
	public static final String GIST_KEY = "gist";
	/**
	 * Creating constant variable for GIST_USERNAME
	 */
	public static final String GIST_USERNAME = "gist_username";
	/**
	 * Creating constant variable for GIST_PASSWORD
	 */
	public static final String GIST_PASSWORD = "gist_password";
	/**
	 * Creating constant variable for proxy configuration
	 */
	public static final String CONFIG_USE_PROXY = "config.useProxy";
	/**
	 * Creating constant variable for proxt host
	 */
	public static final String CONFIG_PROXY_HOST = "config.proxy.host";
	/**
	 * Creating constant variable for port
	 */
	public static final String CONFIG_PROXY_PORT = "config.proxy.port";
	/**
	 * Creating constant variable for proxy username
	 */
	public static final String CONFIG_PROXY_USER = "config.proxy.user";
	/**
	 * Creating constant variable for proxy password
	 */
	public static final String CONFIG_PROXY_PASS = "config.proxy.pass";
	/**
	 * Creating constant variable for TRUE_STRING
	 */
	public static final String TRUE_STRING = "true";
	/**
	 * Creating constant variable for TRUE_STRING
	 */
	public static final long GIST_CLEANUP_DELAY = 2000;// 43200000;
	/**
	 * Creating constant variable for TRUE_STRING
	 */
	public static final String EMPTY_STRING = "";
	
	public static final String ERROR_DELETING_OLD_GIST_KEY = "Error Occured in Gist Cleanup Scheduler : ";
	public static final String DELETED_SUCCESS_MESSAGE_KEY = "Deleted Old Gist : ";
	public static final String NOTHING_TO_DELETE = "Old Gist Not Found";
	public static final String CONFIG_ERROR_KEY = "Invalid Configuration of Gist Cleanup Frequency : ";
	public static final String HOURS_KEY = " Hours";
	public static final String CLEANUP_STARTED_KEY = "Gist Cleanup Started for ";

}
