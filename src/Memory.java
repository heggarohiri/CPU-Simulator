import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Memory {
    final static int[] memory = new int[2000];

    /**
     * Memory constructor takes in a file path as a parameter to initialize the memory array.
     *
     * @param filePath the path of the input program file
     */
    public Memory(String filePath) {

        // Initialize the scanner to null
        Scanner input = null;

        /*
         * Open the file and read from the text file. If the file object does not exist, throw a FileNotFound exception
         * and terminate the process.
         */
        try {
            input = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Cannot open file.");
            System.exit(-1);
        }

        int i = 0;
        while (input.hasNext()) {

            // If the next token is an integer, add it to the array
            if (input.hasNextInt()) {
                int n = input.nextInt();
                memory[i++] = n;
            }
            else {
                String line = input.next();

                // Skip the line if it's empty OR it contains a comment
                if (line.isEmpty() || line.contains("//")) {
                    input.nextLine();
                }
                // Jump to the index after '.', and continue initializing the array
                else if (line.charAt(0) == '.') {
                    int j = Integer.parseInt(line.substring(1));
                    i = j;
                }
                // Skip the line if it contains anything else
                else {
                    input.nextLine();
                }
            }
        }
    }

    public void runMemory(Scanner scanner) {

        String line;
        int address;
        int data;

        while (scanner.hasNext()) {

            // Read the next command from the CPU
            line = scanner.nextLine();

            // Split the command into an array
            String[] arr = line.split("\\s+");

            // Get the character of the command
            String command = arr[0];

            switch (command) {

                /* Read the data from memory */
                case "r":
                    address = Integer.parseInt(arr[1]);
                    System.out.println(read(address));
                    break;

                /* Write the data to memory */
                case "w":
                    address = Integer.parseInt(arr[1]);
                    data = Integer.parseInt(arr[2]);
                    write(address, data);
                    break;

                /* Terminate the process if the command is invalid */
                default:
                    System.out.printf("Invalid command: %s.", command);
                    System.exit(-1);
            }
        }
    }

    /**
     * Method retrieves the data stored in memory at the given address.
     *
     * @param address the address of the data
     * @return the value stored at the address
     */
    public static int read(int address) {
        return memory[address];
    }

    /**
     * Method writes data to memory at the given address.
     *
     * @param address the address the data will be stored
     * @param data the value to be stored at the address
     */
    public static void write(int address, int data) {
        memory[address] = data;
    }

    public static void main(String[] args) {

        try {

            // Get file path from command line
            Scanner scanner = new Scanner(System.in);

            String filePath = null;
            if (scanner.hasNextLine()) {
                filePath = scanner.nextLine();
            }

            // Initialize the memory array
            Memory memory1 = new Memory(filePath);

            // Run the memory program
            memory1.runMemory(scanner);


        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

