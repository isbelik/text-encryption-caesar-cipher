import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CaesarCipher {
    private final String filePath;
    private int key;
    FileService fileService;

    public CaesarCipher(Command command, String filePath, int key) {
        this.filePath = filePath;
        this.key = key;
        this.fileService = new FileService(command, filePath);
    }

    String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz.,\"':!? ";

    public void encryptFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileService.generateOutputFileName()))) {
            for (String string : fileService.readFile(filePath)) {
                for (char charFromString : string.toCharArray()) {
                    char symbol = encryptChar(charFromString, key);
                    bufferedWriter.append(symbol);
                }
                bufferedWriter.newLine();
            }
            System.out.println("The file was successfully encrypted.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void decryptFile() {
        try (BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(fileService.generateOutputFileName()))) {
            for (String string : fileService.readFile(filePath)) {
                for (char c : string.toCharArray()) {
                    char symbol = encryptChar(c, -key);
                    bufferedWriter2.append(symbol);
                }
                bufferedWriter2.newLine();
            }
            System.out.println("The file was successfully decrypted.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bruteForceFile() {
        List<String> stringsFtomFile = fileService.readFile(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        String condition = ". ";
        label:
        for (int i = 0; i < alphabet.length(); i++) {
            for (String s : stringsFtomFile) {
                for (char c : s.toCharArray()) {
                    char symbol = encryptChar(c, -i);
                    stringBuilder.append(symbol);
                }
                String partOfText = stringBuilder.toString();
                int index = partOfText.indexOf(condition);
                if (index != -1 && index + condition.length() < partOfText.length()) {
                    for (int j = 0; j < partOfText.length(); j++) {
                        char nextChar = partOfText.charAt(partOfText.indexOf(condition) + condition.length());
                        if (Character.isUpperCase(nextChar)) {
                            System.out.println("Key to decrypt text: " + i);
                            this.key = i;
                            break label;
                        }
                    }
                }
                stringBuilder.setLength(0);
            }
        }
        decryptFile();
    }

    public char encryptChar(char c, int key) {
        if (Character.isLetterOrDigit(c) || c == '.' || c == ',' || c == '«' || c == '»' || c == '"' || c == '\'' || c == ':' || c == '!' || c == '?' || c == ' ') {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int encryptedIndex = (index + key) % alphabet.length();
                if (encryptedIndex < 0) encryptedIndex = encryptedIndex + alphabet.length();
                c = alphabet.charAt(encryptedIndex);
            }
        }
        return c;
    }
}
