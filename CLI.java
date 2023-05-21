import java.util.Scanner;

public class CLI {
    public void cliRunner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Greetings! This is a program to encrypt/decrypt text from a file.");
        System.out.println();
        System.out.println("To continue, enter the required command:");
        System.out.println(Command.ENCRYPT);
        System.out.println(Command.DECRYPT);
        System.out.println(Command.BRUTEFORCE);
        String command = scanner.nextLine();

        System.out.println("Specify the path to the file in quotes:");
        String filePath = scanner.nextLine();

        System.out.println("Specify the key for encryption/decryption:");
        String key = (scanner.nextLine());
        if ((command.equalsIgnoreCase("ENCRYPT") || command.equalsIgnoreCase("DECRYPT")) && key.equals("")) {
            System.out.println("For ENCRYPT and DECRYPT, the key must be specified. Try again.");
        } else if (key.equals("")) key = "0";

        String [] args = new String[]{command, filePath, key};

        new Runner().runProgramm(args);
    }
}
