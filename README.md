# CPU Simulator

## Project Description

This project simulates a simple computer system that consists of a CPU and Memory. Both the CPU and Memory are simulated by separate processes that communicate with each other. The Memory file contains one program that the CPU executes and then the simulation ends.

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
