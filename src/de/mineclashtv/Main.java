package de.mineclashtv;

import de.mineclashtv.cli.Interface;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static String version = "1.0.0";

	public static void main(String[] args) throws Exception {
		setJAudioTaggerLoggingLevel(Level.OFF);

		Interface cli = new Interface();
		cli.start();
	}

	/**
	 * JAudioTagger by default annihilates your console with irrelevant information, so this is required to make it shut up
	 *
	 * @param level Logging level to set all JAudioLogger loggers to
	 */
	private static void setJAudioTaggerLoggingLevel(Level level) {
		Logger[] loggers = new Logger[] {
				Logger.getLogger("org.jaudiotagger")
		};

		Arrays.stream(loggers).forEach(l -> l.setLevel(level));
	}

}
