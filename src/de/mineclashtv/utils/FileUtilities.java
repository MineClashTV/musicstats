package de.mineclashtv.utils;

import de.mineclashtv.data.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtilities {

	/**
	 * Walks the root Path for all audio/music files and returns it as a List.<br/>
	 * Valid file types are defined in {@link Data}.
	 *
	 * @param root Root directory of music library
	 * @return A list containing all music files found in the root directory and all of its subdirectories
	 * @throws IOException when files can't be accessed
	 */
	public static List<File> getMusicFiles(Path root) throws IOException {
		return Files.walk(root)
					.map(Path::toFile)
					.filter(FileUtilities::isAudioFile)
					.sorted()
					.collect(Collectors.toList());
	}

	/**
	 * Checks if the given file is a valid audio file by checking its extension.
	 *
	 * @param file File to check
	 * @return true if it is an audio file, false otherwise
	 */
	public static boolean isAudioFile(File file) {
		for(String type : Data.fileTypes)
			if(file.getName().endsWith(type))
				return true;

		return false;
	}

}
