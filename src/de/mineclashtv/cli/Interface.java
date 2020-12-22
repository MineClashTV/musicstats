package de.mineclashtv.cli;

import de.mineclashtv.Main;
import de.mineclashtv.metadata.MetaTag;
import de.mineclashtv.metadata.Metadata;
import de.mineclashtv.metadata.MetadataUtilities;
import de.mineclashtv.utils.FileUtilities;
import de.mineclashtv.utils.StringUtilities;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Interface {

	private final Scanner scanner;
	private MetaTag tag;
	private Path path;

	public Interface() {
		this.scanner = new Scanner(System.in);
		this.tag = null;
		this.path = null;
	}

	public void start()
			throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
		System.out.println("-".repeat(40));
		System.out.println(StringUtilities.center("Musicstat v" + Main.version, 40));
		System.out.println("-".repeat(40));

		System.out.println("\nPlease enter the root directory of your music library (e.g. ~/Music)");
		System.out.print("=> ");

		this.path = Path.of(scanner.nextLine());

		/* Using ~ to locate user home directory is not natively supported, so do this */
		if(this.path.toString().startsWith("~"))
			this.path = Path.of(this.path.toString().replace("~", System.getProperty("user.home")));

		if(!this.path.toFile().exists() || !this.path.toFile().isDirectory()) {
			System.err.println("This path does not exist or is not a directory. Please try again with a valid path.");

			return;
		}

		System.out.println("\nSuccessfully set path to " + this.path.toString());

		System.out.println("\nPlease enter the metadata you want to get\n" +
				"title, artist, album, year, track, bitrate, sample rate, bits per sample, format"
		);
		System.out.print("=> ");

		this.tag = switch(scanner.nextLine().toLowerCase()) {
			case "year"            -> MetaTag.YEAR;
			case "title"           -> MetaTag.TITLE;
			case "album"           -> MetaTag.ALBUM;
			case "track"           -> MetaTag.TRACK;
			case "artist"          -> MetaTag.ARTIST;
			case "bitrate"         -> MetaTag.BITRATE;
			case "sample rate"     -> MetaTag.SAMPLE_RATE;
			case "bits per sample" -> MetaTag.BITS_PER_SAMPLE;
			case "format"		   -> MetaTag.FORMAT;
			default -> null;
		};

		if(this.tag == null) {
			System.err.println("Error parsing metadata input");

			return;
		}

		System.out.println("\nSorting by " + this.tag.name().toLowerCase().replace("_", " "));

		output(this.path, this.tag);
	}

	private void output(Path path, MetaTag tag) throws
			IOException, ReadOnlyFileException, TagException, InvalidAudioFrameException, CannotReadException {
		long timeA = System.currentTimeMillis();
		List<File> files = FileUtilities.getMusicFiles(path);
		List<Metadata> metadata = MetadataUtilities.getAllMetadata(files);

		System.out.printf("Found %d files\n", files.size());
		MetadataUtilities.printFrequency(files, metadata, tag);

		System.out.println("Operation took " + (System.currentTimeMillis() - timeA) + "ms");
	}
}
