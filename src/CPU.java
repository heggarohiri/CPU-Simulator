import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class CPU {
    static int PC, SP, IR, AC, X, Y;

    static int timer;

    static final int USER_ADDRESS = 0;
    static final int SYSTEM_STACK = 2000;
    static int USER_STACK = 1000;
    static final int INTERRUPT_HANDLER = 1500;
    static final int TIMER_ADDRESS = 1000;

    static PrintWriter writer;
    static Scanner reader;

    static boolean kernelMode;

    static int instructions;

    /**
     * Method initializes the PC, SP, X and Y register values, the instruction count and sets the system mode to user.
     */
    public static void initializeCPU() {
        PC = USER_ADDRESS;
        SP = USER_STACK;
        instructions = 0;
        X = 0;
        Y = 0;
        kernelMode = false;
    }

    public static void main(String[] args) {

        // Initialize the file path to null
        String filePath = null;

        if (args.length == 2) {
            filePath = args[0];
            timer = Integer.parseInt(args[1]);
        }
        else {
            System.out.println("ERROR: Not enough arguments.");
            System.exit(-1);
        }

        try {

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("java Memory");

            OutputStream out = process.getOutputStream();
            writer = new PrintWriter(out);

            InputStream in = process.getInputStream();
            reader = new Scanner(in);

            writer.printf(filePath + "\n");
            writer.flush();

            // Initialize CPU register values
            initializeCPU();

            // Run the program until the END (50) instruction is reached
            do {

                // Load an instruction in to the IR
                IR = fetch();

                // Execute the instruction
                execute();

                // Increment the instruction count if the system is in user mode
                if (!kernelMode) {
                    instructions++;
                }

                // Check the timer interrupt flag to initiate a timer interrupt at address 1000
                if (!kernelMode && instructions >= timer) {
                    instructions = 0;
                    interruptProcess();
                    PC = TIMER_ADDRESS;
                }

            } while (IR != 50);

            process.waitFor();
            int exitVal = process.exitValue();
            System.out.println("Process exited: " + exitVal);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * The function retrieves data stored in memory.
     *
     * @param address the address the data is stored
     * @return the data stored at the address
     */
    public static int read(int address) {

        // Terminate the process if there is a memory violation
        if (address >= USER_STACK && !kernelMode){
            System.out.println("Memory violation: Cannot access system address 1000 in User Mode.");
            System.exit(-1);
        }

        writer.printf("r %d\n", address);
        writer.flush();

        // Read the data at the address from memory if it exists; otherwise, return -1
        if (reader.hasNext()) {
            String data = reader.nextLine();
            return Integer.parseInt(data);
        }
        else {
            return -1;
        }
    }

    /**
     * The function writes data to memory at a given address.
     *
     * @param address the address the data should be stored
     * @param data the data to be stored at the address
     */
    public static void write(int address, int data) {

        // Terminate the process if there is a memory violation
        if (address >= USER_STACK && !kernelMode){
            System.out.println("Memory violation: Cannot access system address 1000 in User Mode.");
            System.exit(-1);
        }

        // Send command to memory
        writer.printf("w %d %d\n", address, data);
        writer.flush();
    }

    /**
     * The function activates an interrupt be performing the following operations:
     *    - the system is switched to kernel mode
     *    - the SP and PC registers are pushed onto the system stack
     *    - the stack pointer is switched to the system stack
     */
    public static void interruptProcess() {
        kernelMode = true;
        int address = SP;
        SP = SYSTEM_STACK;
        push(address);
        push(PC);
    }

    /**
     * The function fetches the next instruction from memory that is currently stored in the program counter and
     * increments the PC to point to the next instruction.
     *
     * @return the next instruction stored in PC
     */
    public static int fetch() {
        return read(PC++);
    }

    /**
     * The function removes an element on top of the stack.
     *
     * @return the data on top of the stack
     */
    public static int pop() {
        int data = read(SP);
        SP++;
        return data;
    }


    /**
     * The function inserts new data at the top of the stack.
     *
     * @param data the data to be added to the stack
     */
    public static void push(int data) {
        SP--;
        write(SP, data);
    }

    private static void execute() {

        int operand;

        switch (IR) {

            /* Load the value into the AC */
            case 1:
                operand = fetch();
                AC = operand;
                break;

            /* Load the value at the address into the AC */
            case 2:
                operand = fetch();
                AC = read(operand);
                break;

            /* Load the value from the address found in the given address into the AC */
            case 3:
                operand = fetch();
                operand = read(operand);
                AC = read(operand);
                break;

            /* Load the value at (address + X) into the AC */
            case 4:
                operand = fetch();
                AC = read(operand + X);
                break;

            /* Load the value at (address + Y) into the AC */
            case 5:
                operand = fetch();
                AC = read(operand + Y);
                break;

            /* Load from (SP + X) into the AC */
            case 6:
                operand = SP + X;
                AC = read(operand);
                break;

            /* Store the value in the AC into the address */
            case 7:
                operand = fetch();
                write(operand, AC);
                break;

            /* Gets a random int from 1 to 100 into the AC */
            case 8:
                Random rand = new Random();
                AC = rand.nextInt(100) + 1;
                break;

            /*
             * If port = 1, writes AC as an int to the screen
             * If port = 2, writes AC as a char to the screen
             */
            case 9:
                operand = fetch();
                switch (operand) {
                    case 1:
                        System.out.print(AC);
                        break;
                    case 2:
                        System.out.print((char) AC);
                        break;
                    default:
                        System.out.printf("ERR0R: Invalid port number: %d.", operand);
                        System.exit(-1);
                }
                break;

            /* Add the value in X to the AC */
            case 10:
                AC += X;
                break;

            /* Add the value in Y to the AC */
            case 11:
                AC += Y;
                break;

            /* Subtract the value in X from the AC */
            case 12:
                AC -= X;
                break;

            /* Subtract the value in Y from the AC */
            case 13:
                AC -= Y;
                break;

            /* Copy the value in the AC to X */
            case 14:
                X = AC;
                break;

            /* Copy the value in X to the AC */
            case 15:
                AC = X;
                break;

            /* Copy the value in the AC to Y */
            case 16:
                Y = AC;
                break;

            /* Copy the value in Y to the AC */
            case 17:
                AC = Y;
                break;

            /* Copy the value in AC to the SP */
            case 18:
                SP = AC;
                break;

            /* Copy the value in SP to the AC */
            case 19:
                AC = SP;
                break;

            /* Jump to the address */
            case 20:
                operand = fetch();
                PC = operand;
                break;

            /* Jump to the address only if the value in the AC is zero */
            case 21:
                operand = fetch();
                if (AC == 0) {
                    PC = operand;
                }
                break;

            /* Jump to the address only if the value in the AC is not zero */
            case 22:
                operand = fetch();
                if (AC != 0) {
                    PC = operand;
                }
                break;

            /* Push return address onto stack, jump to the address */
            case 23:
                operand = fetch();
                SP--;
                write(SP, PC);
                PC = operand;
                break;

            /* Pop return address from the stack, jump to the address */
            case 24:
                operand = read(SP);
                PC = operand;
                SP++;
                break;

            /* Increment the value in X */
            case 25:
                X++;
                break;

            /* Decrement the value in X */
            case 26:
                X--;
                break;

            /* Push AC onto stack */
            case 27:
                push(AC);
                break;

            /* Pop from stack into AC */
            case 28:
                AC = pop();
                break;

            /* Perform system call */
            case 29:
                if (!kernelMode) {
                    interruptProcess();
                    PC = INTERRUPT_HANDLER;
                }
                break;

            /* Return from system call */
            case 30:
                PC = pop();
                SP = read(SP);
                kernelMode = false;
                break;

            /* End execution */
            case 50:
                System.exit(0);
                break;

            /* Invalid instruction */
            default:
                System.out.printf("ERROR: Invalid instruction code: %d. Exiting process...", IR);
                System.exit(-1);
        }
    }
}

