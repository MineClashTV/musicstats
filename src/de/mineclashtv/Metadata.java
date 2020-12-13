package de.mineclashtv;

public class Metadata {

	private final String title, artist, album, date, track, bitRate, sampleRate;
	private final int bitsPerSample;

	// Constructor of doom
	public Metadata(String title,
	                String artist,
	                String album,
	                String date,
	                String track,
	                String bitRate,
	                String sampleRate,
	                int bitsPerSample) {
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

	public int getBitsPerSample() {
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

	public String toPrettyString() {
		return String.format(
				"%s. %s - %s from %s (%s) [%skbps %sHz %dbit]",
				track,
				artist,
				title,
				album,
				date,
				bitRate,
				sampleRate,
				bitsPerSample
		);
	}
}
