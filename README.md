# CPU Simulator

## Project description

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

## **Project files**

### **Source code**

`**Memory.java`**

- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
- quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit

`**CPU.java**`

- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
- quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit

### Program i**nput files**

`**sample1.txt**`

- Tests the indexed load instructions.
- Outputs two tables:
    1. Characters A-Z
    2. Numbers 1-10

`**sample2.txt**`

- Tests the `call`/`ret` instructions.
- Outputs a smiley face where the lines are printed using subroutine calls.

`**sample3.txt**`

- Tests the `int`/`iret` instructions.
- The main loop is printing the letter A followed by a number that is being periodically incremented by the timer.
- The number will increment faster if the timer period is shorter.

`**sample4.txt**`

- Tests the proper operation of the user stack and system stack, and also tests that accessing system memory in user mode gives an error and exits.

---

## How to run

1. Compile all source code files in the directory using one of the following the commands:

    ```java
    javac Memory.java
    javac CPU.java
    ```

   or

    ```java
    javac *.java
    ```

2. Run the program using the following command, with an absolute path or input file name and valid timer value as arguments:

    ```
    java CPU sample<#>.txt <timer value>
    ```

   Example:

    ```java
    java CPU sample2.txt 10
    ```
