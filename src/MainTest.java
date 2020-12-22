import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class MainTest {

	public static void main(String[] args) throws Exception {
		Path pathA = Path.of(System.getProperty("user.home") + "/Music");
		Path pathB = Path.of("/mnt/sda2/Musik/Tidal Rips");

		List<String> filesA = Files.walk(pathA)
				.map(p -> p.toFile().getName())
				.filter(s -> s.endsWith(".flac"))
				.collect(Collectors.toList());
		List<String> filesB = Files.walk(pathB)
				.map(p -> p.toFile().getName())
				.filter(s -> s.endsWith(".flac"))
				.collect(Collectors.toList());

		for(String s : filesA) {
			if(!filesB.contains(s))
				System.out.println(s);
		}
	}
}
