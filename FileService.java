import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileService {
    private final Command command;
    private final String filePath;

    public FileService(Command command, String filePath) {
        this.command = command;
        this.filePath = filePath;
    }

    public List<String> readFile (String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            System.out.println("Invalid file path specified");
            throw new RuntimeException(e);
        }

    }

    public String generateOutputFileName () {
        Path path = Paths.get(filePath);
        String directory = path.getParent().toString();
        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String baseFileName = fileName.substring(0, fileName.lastIndexOf('.'));
        return switch (command) {
            case ENCRYPT -> Paths.get(directory, baseFileName + "[ENCRYPTED]" + extension).toString();
            case DECRYPT, BRUTEFORCE -> Paths.get(directory, baseFileName + "[DECRYPTED]" + extension).toString();
        };
    }
}

