package de.mineclashtv.metadata;

public class Metadata {

	private final String title, artist, album, date, track, bitRate, sampleRate, bitsPerSample;

	public Metadata(String title,
	                String artist,
	                String album,
	                String date,
	                String track,
	                String bitRate,
	                String sampleRate,
	                String bitsPerSample) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.date = date;
		this.track = track;
		this.bitRate = bitRate;
		this.sampleRate = sampleRate;
		this.bitsPerSample = bitsPerSample;
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

	public String getDate() {
		return date;
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

	@Override
	public String toString() {
		return "Metadata{" +
				"title='" + title + '\'' +
				", artist='" + artist + '\'' +
				", album='" + album + '\'' +
				", date='" + date + '\'' +
				", track='" + track + '\'' +
				", bitRate='" + bitRate + '\'' +
				", sampleRate='" + sampleRate + '\'' +
				", bitsPerSample=" + bitsPerSample +
				'}';
	}

}
