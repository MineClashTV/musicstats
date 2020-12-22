package de.mineclashtv.metadata;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetadataUtilities {

	public static Metadata getMetadata(File file) throws
			TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
		AudioFile audioFile = AudioFileIO.read(file);
		Tag tag = audioFile.getTag();
		AudioHeader header = audioFile.getAudioHeader();
		String fileName = file.getName();

		String title         = tag.getFirst(FieldKey.TITLE);
		String artist        = tag.getFirst(FieldKey.ARTIST);
		String album         = tag.getFirst(FieldKey.ALBUM);
		String year          = tag.getFirst(FieldKey.YEAR);
		String track         = tag.getFirst(FieldKey.TRACK);
		String bitRate       = header.getBitRate() + " kbps";
		String sampleRate    = header.getSampleRate() + " Hz";
		String bitsPerSample = header.getBitsPerSample() + " bit";
		String format        = fileName.substring(fileName.lastIndexOf(".") + 1);

		return new Metadata(title, artist, album, year, track, bitRate, sampleRate, bitsPerSample, format);
	}

	public static List<Metadata> getAllMetadata(List<File> files)
			throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {
		List<Metadata> a = new ArrayList<>();

		for(File file : files)
			a.add(getMetadata(file));

		return a;
	}

	public static Map<String, Integer> getFrequencyOf(List<Metadata> dataList, MetaTag tag) {
		Map<String, Integer> a = new HashMap<>();

		dataList.forEach(d -> {
			switch(tag) {
				case TITLE           -> incrementValueInMap(a, d.getTitle());
				case ARTIST          -> incrementValueInMap(a, d.getArtist());
				case ALBUM           -> incrementValueInMap(a, d.getAlbum());
				case YEAR            -> incrementValueInMap(a, d.getYear());
				case TRACK           -> incrementValueInMap(a, d.getTrack());
				case BITRATE         -> incrementValueInMap(a, d.getBitRate());
				case SAMPLE_RATE     -> incrementValueInMap(a, d.getSampleRate());
				case BITS_PER_SAMPLE -> incrementValueInMap(a, d.getBitsPerSample());
				case FORMAT 	     -> incrementValueInMap(a, d.getFormat());
			}
		});

		return a;
	}

	public static void printFrequency(List<File> music, List<Metadata> metadata, MetaTag tag) {
		getFrequencyOf(metadata, tag)
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.forEach(a ->
					System.out.printf("%dx %s (%.2f%% of total %ss)\n",
						a.getValue(),
						a.getKey(),
						(float)a.getValue() / music.size() * 100,
						tag.toString().replace("_", " ").toLowerCase()
					)
				);
	}

	/**
	 * Increments the Integer value associated with given key String by 1.<br/>
	 * If the key doesn't yet exist, it will be initiated with a value of 1.
	 *
	 * @param map Map to increment a value
	 * @param key Key whose value should get increased
	 */
	private static void incrementValueInMap(Map<String, Integer> map, String key) {
		if(map.containsKey(key)) /* Key already present; increase its value by 1 */
			map.replace(key, map.get(key) + 1);
		else /* Key not yet present; add it with an initial value of 1 */
			map.put(key, 1);
	}

}
