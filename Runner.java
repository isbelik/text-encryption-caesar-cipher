public class Runner {
    private Command command;
    private String filePath;
    private int key;

    public void runProgramm(String[] args) {
        try {
            this.command = Command.valueOf(args[0]);
            this.filePath = args[1];
            if (args.length == 2) {
                this.key = 0;
            } else {
                this.key = Integer.parseInt(args[2]);
            }
        } catch (Exception e) {
            System.out.println("Please provide valid data.");
        }

        CaesarCipher caesarCipher = new CaesarCipher(command, filePath, key);

        if (args.length < 2) {
            System.out.println("Not all parameters were specified, running the console version.");
            new CLI().cliRunner();
        } else if (command == Command.BRUTEFORCE && args.length == 2) {
            caesarCipher.bruteForceFile();
        } else if (args.length == 3) {
            if (command == Command.ENCRYPT) caesarCipher.encryptFile();
            if (command == Command.DECRYPT) caesarCipher.decryptFile();
            if (command == Command.BRUTEFORCE) caesarCipher.bruteForceFile();
        }
    }
}
