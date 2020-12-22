package de.mineclashtv.metadata;

public class Metadata {

	private final String title, artist, album, year, track, bitRate, sampleRate, bitsPerSample, format;

	public Metadata(String title,
	                String artist,
	                String album,
	                String year,
	                String track,
	                String bitRate,
	                String sampleRate,
	                String bitsPerSample,
					String format) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.track = track;
		this.bitRate = bitRate;
		this.sampleRate = sampleRate;
		this.bitsPerSample = bitsPerSample;
		this.format = format;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getYear() {
		return year;
	}

	public String getTrack() {
		return track;
	}

	public String getBitRate() {
		return bitRate;
	}

	public String getSampleRate() {
		return sampleRate;
	}

	public String getBitsPerSample() {
		return bitsPerSample;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public String toString() {
		return "Metadata{" +
				"title='" + title + '\'' +
				", artist='" + artist + '\'' +
				", album='" + album + '\'' +
				", year='" + year + '\'' +
				", track='" + track + '\'' +
				", bitRate='" + bitRate + '\'' +
				", sampleRate='" + sampleRate + '\'' +
				", bitsPerSample='" + bitsPerSample + '\'' +
				", format='" + format + '\'' +
				'}';
	}
}
