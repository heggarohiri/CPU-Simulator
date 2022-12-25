# CPU Simulator

## Project Description

This code is a Java program that simulates a central processing unit (CPU) for a computer. It has several static variables that represent the different registers in a CPU, such as the program counter (PC), stack pointer (SP), and accumulator (AC). The program also has several static constants that represent different memory addresses in the computer's memory, such as the user address, system stack, user stack, and interrupt handler.

The program has a main method that initializes the CPU and memory, and then runs a program stored in memory until it reaches the end instruction (50). The program uses input and output streams to communicate with a separate program called "Memory" that simulates the computer's memory. The CPU can read and write data to memory, and can also perform various instructions such as loading and storing data, performing arithmetic operations, and branching to different parts of the program.

The program also has an interrupt process that can be activated by a timer interrupt or an input/output interrupt. When an interrupt is activated, the program saves the current state of the CPU and switches to kernel mode, in which it can execute special instructions that are only available to the operating system. When the interrupt is finished, the program restores the previous state of the CPU and switches back to user mode. Overall, this code simulates the basic functions of a CPU and how it interacts with memory and interrupts.

### Built With

- Java

## **Project Files**

### **Source Code**

`Memory.java`

- The Memory.java class reads an input file containing a program from the command-line, before the CPU instruction cycle begins. It contains an integer array of 2000 elements — indices 0-999 for user mode and 1000-1999 for system mode. It supports read and write operations using the `read(int address, int data)` and `write(int data)` functions.

`CPU.java`

- The CPU.java class simulates the instruction cycle of a computer program. The class decodes the instruction set contained in the input program read from the command-line. It executes each instruction read from memory using the `read(int address)` and `write(int address, int data)` functions. It supports both timer and system call interrupts. The program ends when the end instruction (50) is reached.
### Program I**nput Files**

`sample1.txt`

- Tests the indexed load instructions.
- Outputs two tables:
    1. Characters A-Z
    2. Numbers 1-10

`sample2.txt`

- Tests the `call`/`ret` instructions.
- Outputs a smiley face where the lines are printed using subroutine calls.

`sample3.txt`

- Tests the `int`/`iret` instructions.
- The main loop is printing the letter A followed by a number that is being periodically incremented by the timer.
- The number will increment faster if the timer period is shorter.

`sample4.txt`

- Tests the proper operation of the user stack and system stack, and also tests that accessing system memory in user mode gives an error and exits.

`sample5.txt`

- Tests load instructions.
- Prints ascii art of the statue of Venus de Milo when the timer value is 500.

---

## How to Run

1. Compile all source code files in the directory using one of the following the commands:

    ```java
    javac Memory.java
    javac CPU.java
    ```

   or

    ```java
    javac *.java
    ```

2. Execute and run the program using the following command, with an absolute path or input file name and valid integer timer value as arguments:

    ```
    java CPU sample<#>.txt <timer value>
    ```

   Example:

    ```
    java CPU sample2.txt 10
    ```
