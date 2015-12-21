//TO RUN ‘java TimeDifference '

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 2 for PAC1 Fall 2015

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
	static boolean startIn, endIn, lastInputErr;


   public static void main( String args[] ){
  	//Display instructions

  	//Ask user for input of first time
  	Boolean keepRunning = true;
  	startIn = false;
  	endIn = false;
  	lastInputErr = false;

      	while (keepRunning){
  			askForValues(startIn, endIn);
      	}
	  	
  	  	if (keepRunning == false){
	  		System.out.print("OK EXITING.\r\n");
	  		System.exit(0);
	  	}
	}

  //----------------- 

  public static void askForValues(boolean start, boolean end) {
  	if (start && end){
  		//nothing to ask for, just calculate
		printBanner();
		int dHours, dMins, dSecs;
		dHours = convertToHours(timeDiff);
		dMins = convertToMinutes(timeDiff, dHours);
		dSecs = timeDiff - (dHours * 3600) - (dMins * 60);
		System.out.println("| Start time :\t" + formatToTime(toSixDigitStr(sFull)) + "\t\t\t|" );
		System.out.println("| End time :\t" + formatToTime(toSixDigitStr(eFull)) + "\t\t\t|" );
		System.out.println("|-----------------------------------------------|");
		System.out.println("| And the time difference is " + dHours + ":" + dMins + ":" + dSecs + "\t|");
		System.out.println("|\t\t\t\t\t\t|");
		System.out.println("+-----------------------------------------------+");
		System.out.print("> Go again? Input Start Time ");
		if ( inputScanner.hasNext() ){ //on userInput
			//Reset input bools to false
			startIn = false;
			endIn = false;
			lastInputErr = false;	
		}
  	} else if (start){
  		printBanner();
  		//ask for second value
  		System.out.println("| Start time :\t" + formatToTime(toSixDigitStr(sFull)) + "\t\t\t|" );
  		System.out.println("|\t\t\t\t\t\t|");
  		System.out.println("+-----------------------------------------------+");
  		System.out.print("| Please input end time: ");
  		inputScanner.nextLine();
  		if ( inputScanner.hasNextInt() ){ 		  			
			eFull = inputScanner.nextInt();
			//If 2nd input is valid
			if (verifyInput(eFull)){
				endIn = true;
				lastInputErr = false;
				timeDiff = convertToSeconds(eFull) - convertToSeconds(sFull);
				askForValues(startIn, endIn);
			} else {
				startIn = true;
				lastInputErr = true;
			}

  		} else {
  			startIn = true;
  			lastInputErr = true;		
  		}

  	} else {
  		//ask for first value
  		printBanner();
  		System.out.print("| Start time: \t");
		if ( inputScanner.hasNextInt() ){ //If there is user input
			sFull = inputScanner.nextInt(); 
			if (verifyInput(sFull)){ //If input is valid
				startIn = true;
				lastInputErr = false;
				askForValues(startIn, endIn);
			} else {
				lastInputErr = true;
			}
	  	} else {
	  		lastInputErr = true;
	  		inputScanner.nextLine();	
	  	}
	}
}
  public static boolean verifyInput(int verifyMe) {
    if (verifyMe > 240000){ //if larger than 24hr, reject
    	return false;
    }
    String timeString = String.valueOf(verifyMe); //turn numbers into string
    if (timeString.length() > 6){	//If string is more 6 characters
    	return false;
    }
    timeString = toSixDigitStr(verifyMe); //convert to 6-digit time
	int h = Integer.parseInt(timeString.substring(0,2));
	int m = Integer.parseInt(timeString.substring(2,4)); 
	int s = Integer.parseInt(timeString.substring(4,6)); 
	System.out.println("h:" + h + "m:" + m + "s:" + s + " ...verifying");
	if (h > 24 || m > 59 || s > 59){ //If any field out of range, reject. 
		return false;
	} 
    return true;
  }

  public static int convertToSeconds(int fullFormat) {
  	//HOURS so return 00|00|00
  	int h, m, s, totalS;
  	h = fullFormat / 10000;
  	m = (fullFormat - h * 10000)/100;
  	s = (fullFormat - h * 10000)%100;
  	totalS = s;
  	totalS += m * 60; 
  	totalS += h * 60 * 60; 
    //do the calculation here
    return totalS;
  }

  public static int convertToHours (int secondsFormat) {
  	int h = secondsFormat/3600;
  	return h;
  }
  public static int convertToMinutes(int secondsFormat, int hours){
  	int m = (secondsFormat - 3600 * hours)/60;
  	return m;
  }

  public static void printBanner() {
	System.out.print(ANSI_CLS + ANSI_HOME);
	System.out.flush();
	System.out.println("\n\n+-----------------------------------------------+");
	if (lastInputErr){	
		System.out.println("|            WRONG FORMAT!              \t|");
		lastInputErr = false; //Reset to false	
	} else {
		System.out.println("|     CALCULATE TIME DIFFERENCE!        \t|");
	}
	System.out.println("|-----------------------------------------------|");
	System.out.println("|\t\t\t\t\t\t|");
  }

  public static String formatToTime(String sixDigiStr) {
  		String timeStr = String.valueOf(sixDigiStr); 
  		String h = timeStr.substring(0,2); 
  		String m = timeStr.substring(2,4);
  		String s = timeStr.substring(4,6);
  		timeStr =  h + ":" + m + ":" + s;
  		return timeStr; 
  }

  public static String toSixDigitStr(int rawTime) {
  		String rawTimeStr = String.valueOf(rawTime);
  	    if (rawTimeStr.length() < 6){ //Standardize time to 6-digit string
    		int zerosToAdd = 6 - rawTimeStr.length();
	    	for(int i = 0; i < zerosToAdd; i++){
	    		rawTimeStr = "0" + rawTimeStr; 
			}
		}
		return rawTimeStr; 
  }

} 

