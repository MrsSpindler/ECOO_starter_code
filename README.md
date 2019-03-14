# ECOO Contest Prep: 2019

The ECOO programming contest is divided into three rounds, with the top teams from each round progressing to the next:

- Round 1: Board Wide Contest

  - Date: Friday March 22, 2019
  - Location: Derry Byrne Center

- Round 2: Regional Contest

  - Date: Saturday April 27th, 2019 (TBC)
  - Location: Queen’s University, Kingston. 

- Round 3: Final Contest

  - Date: Saturday, May 11th, 2018 (TBC)
  - Location: York University, Toronto

Each school is permitted a specified number of teams. Each team will consist of up to four members. **The teams are permitted to prepare for the contest.** Previous contest questions can be found at <http://www.ecoocs.org/problems.php>. The most important rules and considerations are as follows:

- Each team has their own computer (1 per team) with a working USB port. 
- NO INTERNET IS PERMITTED DURING THE EVENT.
- NO PERSONAL ELECTRONIC DEVICES ARE PERMITTED.
- Previously created / existing code and libraries are permitted.
- Hard copies of code and samples are permitted.

Full contest rules are available here: http://ecoocs.org/pdf/Contest_Rules.pdf

Each team must bring their own computer - preferably a laptop with the appropriate SDK and IDE pre-loaded. You may program in Java or Python, depending on your comfort level with each language.  **Only one computer** can be used by each team during the contest. See me for help in setting up your laptop.

Each round is **3 hours long** and all teams will be given **4 problems**  to solve. Discuss with your group ahead of time how you will divide the work.  Some observations from past contests to consider:

- The first 2 problems tend to be a bit easier to solve than that last 2.
- The practice data files provided only contain 2 or 3 test cases, however the judging files contain 10 test cases.
- For scoring purposes, both the **number of cases** correctly processed and the **time at which you present your solution** will count towards your score for that problem.

These contests always require input from a text file (usually on a usb drive) into the program. All output will be to the screen. Each line in the text file can have **one item of data** (number or string) or a **set of data**, usually separated by spaces. The data format is usually shown in the problem sheet.  Starter code with basic file input can be obtained from the following repository: **https://github.com/MrsSpindler/ECOO_starter_code**

## Reading from a File

The ECOO programming contest requires you to access a text file and read in the data one line (or one character) at a time. This type of file access is called ***sequential file access***.

To read information from a file, you need the following:

- the name of the file you want to read (In this case, we have a *res* folder containing a text file named DATA.txt)
- an input stream object for the file (`FileReader`: This reads the bytes from the file on the system)
- an input buffer (`BufferedReader`: This converts the bytes into Strings)

```java
BufferedReader fileIn = new BufferedReader ( new FileReader(“res/DATA.txt”));
```

To read a single line from the file, use this code:

```java
String myLine = fileIn.readLine();
```
If the data contained is a number, you can convert it use the Integer or Double wrapper class:

```java
int intNumber = Integer.parseInt(myLine);
double dblNumber = Double.parseDouble(myLine);
```

If the line contains a series of items separated by spaces, you can split the line into an array of Strings, then process each String separately

```java
String[] listOfStrings = myLine.split(" ");
//First items is an integer
int intNumber = Integer.parseInt(listOfStrings[0]);
//Second item is a floating-point
double dblNumber = Double.parseDouble(listOfStrings[1]);

```


Every sample file contains 10 testcases, so it would be a good idea to put your file reading into a for loop that will execute 10 times.

## Error Control in Java  (Exception Handling)

When a Java program encounters an error, such as trying to open a file that doesn’t exist, or it is unable to convert a String into a number, the JVM will send an error, or an exception, and the program will stop. 

### Throwing Exceptions

Java will output errors automatically for many exceptions. However, there are also instances where Java requires that the programmer explicitly state that certain errors may occur. File input/output is one of these cases. 

The most common exception packages (errors) and their locations are as follows:

| **Exception Class** | **Handles/Throws an Error When** | **Base Class** |
| -------------------- | --------------------------------- | ------------------- |
| IOException | An error condition when there are input or output errors | java.io |
| RuntimeException | The class that handles exceptions when a program is running | java.lang |
| ArithmeticException | Errors when a program attempts to divide by 0 | java.lang |
| IllegalArgumentException | An illegal argument has been passed to a method | java.lang |
| NumberFormatException | When an attempt has been made to convert a string to a number, but the string is in the wrong format. | java.lang |
| IndexOutofBoundsException | An index for an array or other data type is out of bounds | java.lang |

When a file is opened, and data is retrieved you must indicate in the **method header** which type of exceptions are to be ***thrown.*** 

Throwing an exception just means that the Java virtual machine will output the correct error message when the error occurs.   In such a case, instead of using the exception handling clauses, the programmer will add the statement `throws Exception_Class` in the method header. 

For example, you could add the following to a main method that reads from a file:

```java
public static void main (String args []) throws IOException {
```

This header sets the method up to send error messages for input/output exceptions. 

Programming IDEs such as Eclipse will indicate an error and suggest that the header is modified to throw the appropriate exceptions if it is necessary. 

### Catching Exceptions

Java allows programmers to 'catch' some of the expected errors that users, or system problems, may generate.  The programmer can deal with them, allowing the program to exit gracefully, or give the user some knowledge about what happened.

Not every aspect of a program is prone to errors.  The actual logic and internal syntax of the program itself should be fully tested so that there is no opportunity for errors in this area.  

Thus the first step in the error control process in Java is to set up a structure to allow a command or series of commands to be managed for errors.  The command for this first step is **try**.  The code to be watched for errors follows in a block of code following the **try** command.  The following code segment sets a line for reading from a file:

```java
try {
    BufferedReader fileIn = new BufferedReader ( new FileReader(“res/DATA.txt”));
	String myLine = fileIn.readLine();	
}
```
Once a command that is within a try block fails – it is said to throw an exception.  When the programmer handles the exception the programmer uses the **catch** command to divert the flow control to the block that follows the **try** command.  The type of exception is a parameter of the catch block.  In the following example **e** is the **IOException** error type.  It can be used in the  **catch**  block.

```java
catch (IOException e) {
	System.out.println("Input error!");
	System.out.println("Exiting program.");
	System.exit(1);
}
```
An optional clause can be added to this structure.  If there is some code that **must** be completed, this can be included in the finally clause.  The code in the **finally** clause will be executed at the end of the try clause, even if the command(s) in the try clause fails.  

## Reading a File into an ArrayList

If you are unsure of the number of items in a file you can use an **ArrayList** to load the file. A while loop can be used to test if the input is null (see below), and an element can be added to an ArrayList for each item retrieved from the file. (This is a good idea even if you have to parse the line after the file is read.) The following method will read an entire file into an ArrayList and return the ArrayList that is created..

```java
private ArrayList readFileContents(String filename) {
    
    //List of all the lines in the file
    ArrayList lines = new ArrayList();
    
    try {
        BufferedReader fileIn = new BufferedReader(new FileReader(filename));
        String myLine;
        
        while ((myLine = fileIn.readLine()) != null) {
            lines.add(myLine);
        }
        
        fileIn.close();
    } catch (FileNotFoundException e) {
        // Let the user know, but continue the program;
        Console.print("File not found");
    } catch (IOException e) {
        System.err.println("Exception: " + e);
        System.exit(0);
    }

    return lines;
}
```
