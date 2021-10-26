package org.saurav.simpletests.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Tests the Log4j2 functionality
 * Configuration file log4j2.xml is in /src directory
 * Keep changing the configuration in log4j2.xml to try out different functionalities of log4j2. 
 *
 */
public class Log4jTester {



	public static void main(String[] args) {

		System.setProperty("logdir", getLogFilePath()); //need to initalize this before Log4j2 classes are called
		Log4J2Logger log4jlogger = new Log4J2Logger();
		log4jlogger.log();
	}
	
	
	public static String getLogFilePath() {
		//change log file path according to OS version
		if (isMac()) {
			return System.getProperty("user.home");
		} else {
			return System.getProperty("user.home");
		}

	}
	public static boolean isMac() {
		String osName = System.getProperty("os.name");
		if("Mac OS X".equals(osName)) {
			return true;
		}else {
			return false;
		}
	}
}

class Log4J2Logger {
	
	private static final Logger logger = LogManager.getLogger(Log4J2Logger.class);
	
	public void log () {
		logger.debug("Debug log message");
		logger.info("Info log message ");
		logger.error("Error log message");
		logger.warn("Warn log message");
		logger.fatal("Fatal log message");
		logger.trace("Trace log message");
		
	}

}

