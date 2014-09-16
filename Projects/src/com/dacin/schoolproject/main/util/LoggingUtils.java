package com.dacin.schoolproject.main.util;

/**
 * This is a simple implementation for logging. It contains functionality to log
 * messages of different severity, and to change the level dynamically.
 */

public class LoggingUtils {

	public enum Level {
		// levels
		DEBUG, INFO, WARNING, ERROR

		// Remark: If you order the entries in this enum reasonably,
		// you can compare them using .ordinal()
	}

	/** The level of the logger. Determines which messages are actually logged. */
	private static Level CURRENT_LEVEL = Level.DEBUG; 

	/**
	 * Set the level of this logger.
	 * 
	 * @param level
	 *            The new level to use.
	 */
	public static void setLevel(Level level) {
		CURRENT_LEVEL=level;
	}

	/**
	 * Log a message if the currently set level is lower or equal to the
	 * provided level.
	 * 
	 * @param level
	 *            The level of this message.
	 * @param message
	 *            The message to log.
	 */
	public static void log(Level level, String message) {
		if(level.ordinal()>=CURRENT_LEVEL.ordinal()){
			System.out.println(message);
		}
	}

	/**
	 * Convenience function to log error messages. This message is logged if the
	 * logger is set to error or lower.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void error(String message) {
		log(Level.ERROR, message);
	}

	/**
	 * Convenience function to log warnings. This message is logged if the
	 * logger is set to warning or lower.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void warning(String message) {
		log(Level.WARNING, message);
	}

	/**
	 * Convenience function to log info messages. This message is logged if the
	 * logger is set to info or lower.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void info(String mesage) {
		log(Level.INFO, mesage);
		
	}

	/**
	 * Convenience function to log debug messages. This message is logged if the
	 * logger is set to debug or lower.
	 * 
	 * @param message
	 *            The message to log.
	 */
	public static void debug(String mesage) {
		log(Level.DEBUG, mesage);
	}

	/* ----- */

	private LoggingUtils() {
		// Don't allow instances of this class.
	}
}
