package de.mineclashtv;

import de.mineclashtv.data.Data;
import de.mineclashtv.metadata.MetaTag;
import de.mineclashtv.metadata.Metadata;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static de.mineclashtv.metadata.MetadataUtilities.*;
import static de.mineclashtv.utils.FileUtilities.*;

public class Main {

	public static void main(String[] args) throws Exception {
		setJAudioTaggerLoggingLevel(Level.OFF);

		List<File> music = getMusicFiles(Data.path);
		System.out.println(music.size() + " tracks found");

		List<Metadata> metadata = getAllMetadata(music);
		Map<String, Integer> frequency = getFrequencyOf(metadata, MetaTag.ARTIST);

		frequency   .entrySet()
					.stream()
					.sorted(Map.Entry.comparingByValue())
					.forEach(artist -> System.out.println(artist.getValue() + "x " + artist.getKey()));
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
