package de.mineclashtv;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static final Path path = Path.of("/mnt/sda2/Musik/Tidal Rips");
    private static final String[] fileTypes = {
            "flac", "mp3", "alac", "wav"
    };

    public static void main(String[] args) throws Exception {
        setJAudioTaggerLoggingLevel(Level.OFF);

        List<File> music = getFiles(path);
        System.out.println(music.size() + " tracks found");

        List<Metadata> metadata = getAllMetadata(music);
        Map<String, Integer> artistFrequency = getArtistFrequency(metadata);

        artistFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(artist -> System.out.println(artist.getValue() + "x " + artist.getKey()));
    }

    private static void setJAudioTaggerLoggingLevel(Level level) {
        Logger[] loggers = new Logger[] {
                Logger.getLogger("org.jaudiotagger")
        };

        Arrays.stream(loggers).forEach(l -> l.setLevel(level));
    }

    private static Metadata getMetadata(File file) throws
            TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        AudioFile audioFile = AudioFileIO.read(file);
        Tag tag = audioFile.getTag();
        AudioHeader header = audioFile.getAudioHeader();

        String title = tag.getFirst(FieldKey.TITLE);
        String artist = tag.getFirst(FieldKey.ARTIST);
        String album = tag.getFirst(FieldKey.ALBUM);
        String date = tag.getFirst(FieldKey.YEAR);
        String track = tag.getFirst(FieldKey.TRACK);
        String bitRate = header.getBitRate();
        String sampleRate = header.getSampleRate();
        int bitsPerSample = header.getBitsPerSample();

        return new Metadata(title, artist, album, date, track, bitRate, sampleRate, bitsPerSample);
    }

    private static List<Metadata> getAllMetadata(List<File> files)
            throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {
        List<Metadata> a = new ArrayList<>();

        for(File file : files)
            a.add(getMetadata(file));

        return a;
    }

    private static Map<String, Integer> getArtistFrequency(List<Metadata> metadata) {
        Map<String, Integer> a = new HashMap<>();

        metadata.forEach(meta -> {
            String artist = meta.getArtist();

            if(a.containsKey(artist))
                a.replace(artist, a.get(artist) + 1);
            else
                a.put(artist, 1);
        });

        return a;
    }

    private static List<File> getFiles(Path root) throws IOException {
        return Files.walk(root)
                    .map(Path::toFile)
                    .filter(Main::isMusicFile)
                    .sorted()
                    .collect(Collectors.toList());
    }

    private static boolean isMusicFile(File file) {
        for(String type : fileTypes)
            if(file.getName().endsWith(type))
                return true;

        return false;
    }
}
