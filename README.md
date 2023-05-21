This program helps encrypt and decrypt text from a file. To run the program, download the JAR file and execute it using the command "java -jar caesarCipher.jar command filePath key", where:

command - the command to execute. There are 3 commands: ENCRYPT, DECRYPT, BRUTEFORCE.
filePath -  the path to the file (recommended to be specified in quotes).
key - the encryption/decryption key. Mandatory for ENCRYPT and DECRYPT commands.

Currently, the program can only encrypt and decrypt text in English.

It uses an improved version of the Caesar cipher for encryption. The following characters are added: '.', ',', '«', '»', '"', ''', ':', '!', '?', and space.
During decryption, the text format remains unchanged.

The BRUTEFORCE command cracks the cipher, outputs the obtained encryption key to the console, and creates a file with the decrypted text.

If you run the program without any arguments (java -jar caesarCipher.jar), it will run the console version of the program.

I have added some error checks and error handling in certain places, but not everywhere.