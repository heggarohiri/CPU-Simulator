# CPU Simulator

## Project Description

This project simulates a simple computer system that consists of a CPU and Memory. Both the CPU and Memory are simulated by separate processes that communicate with each other. The Memory file contains one program that the CPU executes and then the simulation ends.

### Built With

## **Project Files**

### **Source Code**

`Memory.java`

- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
- quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit

`CPU.java`

- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
- quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit

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

2. Execute and run the program using the following command, with an absolute path or input file name and valid timer value as arguments:

    ```
    java CPU sample<#>.txt <timer value>
    ```

   Example:

    ```
    java CPU sample2.txt 10
    ```
