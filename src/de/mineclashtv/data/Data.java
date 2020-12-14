package de.mineclashtv.data;

import java.nio.file.Path;

public class Data {

	/**
	* Path to the root directory of your music library.
	* Change this accordingly.
	*/
	public static Path path = Path.of("/mnt/sda2/Musik/Tidal Rips");
	/**
	 * All file types/extensions to search for.
	 * You can most likely leave it like this.
	 */
	public static String[] fileTypes = {
			"flac", "mp3", "alac", "wav", "ogg"
	};

}
