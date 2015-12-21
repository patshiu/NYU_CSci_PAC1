//TO RUN ‘java InputOutputer'

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab X for PAC1 Fall 2015

Resources Used: 


- Check for negative number
- Invalid input (not a number)
- zero

----------------------------------
Lab 2: Time Difference
----------------------------------
Write a program that accepts user input of two time values and computes the difference between them.

The times are read into the program as integers of at most 6 digits (without leading zeros): HHMMSS, 
where HH represents hours according to a 24-hour clock (0-23), MM represents minutes (0-59) and 
SSrepresents seconds (0-59). The difference should be displayed in the same format.

The difference can be either positive or negative. It is obtained by subtracting the second time 
from the first time.

For the purposes of this assignment, you may assume that the inputs are correct--that is, the input 
values will be valid positive integers no more than 6 digits in length, and the hours, minutes and 
seconds will be within the proper ranges (e.g.: seconds will be within 0-59).

*/


import java.util.Scanner; 


public class TimeDifference
{

	final static String ANSI_CLS = "\u001b[2J";
	final static String ANSI_HOME = "\u001b[H";

	static Scanner inputScanner = new Scanner(System.in);

	static int sFull, sHour, sMin, sSecond, eFull, eHour, eMin, eSecond, timeDiff; 
	static boolean startIn, endIn;

   public static void main( String args[] ){
  	//Display instructions

  	//Ask user for input of first time
  	Boolean keepRunning = true;
  	

	printBanner();		
	System.out.print("\n\n\n Please input start time: ");

      	while (keepRunning){

	      	if ( inputScanner.hasNextInt() ){ //If there is user input
	      		sFull = inputScanner.nextInt(); 
	      		if (verifyInput(sFull)){ //If input is valid
	      			printBanner();
	      			System.out.println("The start time is " + sFull);
	      			System.out.println("\n|-------------------------------------|");
	      			System.out.print("\n\n\n Please input end time: ");
	      			
	      			//askForEndTime();
	      			inputScanner.nextLine();
	      			if ( inputScanner.hasNextInt() ){
	      				eFull = inputScanner.nextInt();
	      				//If 2nd input is valid
	      				if (verifyInput(eFull)){
	      					timeDiff = sFull - eFull;
			      			printBanner();
	      					System.out.println("The start time is " + sFull);
	      					System.out.println("The end time is " + eFull);
	      					System.out.println("|-------------------------------------|");
	      					System.out.println("And the time difference is " + timeDiff );
	      					System.out.println("\n\n|-------------------------------------|");
	      					System.out.print("\n\n|Go again? \n|Start time:");
	      				}

	      			} else {
	      				printInvalidInputErr(false, sFull);
	      				inputScanner.nextLine();//Clear scanner input and wait for user input
	      			}

	      		} else {
	      			printInvalidInputErr(true);
	      			inputScanner.nextLine();//Clear scanner input and wait for user input
	      		}
	      		

	      	} else {
	      		printInvalidInputErr(true);
	      		inputScanner.nextLine();//Clear scanner input and wait for user input
	      	}
      	}
	  	//Ask user for input of second time
	  		//Verify input
	  	//Convert both times to seconds
	  	//Calculate the difference in seconds
	  	//Convert seconds to HH:MM:SS
	  	//Print and ask for next input    

  	  	if (keepRunning == false){
	  		System.out.print("OK EXITING.\r\n");
	  		System.exit(0);
	  	}
 

  }

  //----------------- 

  public static void askForValues(boolean startIn, boolean endIn) {
  	if (startIn && endIn){
  		//nothing to ask for, just calculate
  		System.out.println("proceed to calculate");
  	} else if (startIn){
  		//ask for second value
  		System.out.println("asking for 2nd value");
  	} else {
  		//ask for first value
  		System.out.println("asking for 1st value");
  	}
  }

  public static boolean verifyInput(int verifyMe) {
    //check that all valuse within rage
    return true;
  }

  public static int convertToSeconds(int full) {
  	int h, m, s, totalS;
    //do the calculation here
    totalS = 100;
    return totalS;
  }

  public static void printBanner() {
	System.out.print(ANSI_CLS + ANSI_HOME);
	System.out.flush();
	System.out.println("\n\n|-------------------------------------|");
	System.out.println("|     CALCULATE TIME DIFFERENCE!      |");
	System.out.println("|-------------------------------------|\n\n\n");
  }

  public static void printInvalidInputErr(boolean isStartTimeInput, int startTime) {

	System.out.print(ANSI_CLS + ANSI_HOME);
	System.out.flush();

	System.out.println("\n\n|-------------------------------------|");
	System.out.println("|            WRONG FORMAT!            |");
	System.out.println("|-------------------------------------|\n\n\n");
	if (isStartTimeInput){
		System.out.println("Please input start time: ");
	} else {
		System.out.println("Start time: " + startTime);
		System.out.println("Please input end time: ");
	}
  }

  public static void printInvalidInputErr(boolean isStartTimeInput) {

	System.out.print(ANSI_CLS + ANSI_HOME);
	System.out.flush();

	System.out.println("\n\n|-------------------------------------|");
	System.out.println("|            WRONG FORMAT!            |");
	System.out.println("|-------------------------------------|\n\n\n");
	if (isStartTimeInput){
		System.out.println("Please input start time: ");
	} else {
		System.out.println("Start time: error");
		System.out.println("Please input end time: ");
	}
  }

} 

