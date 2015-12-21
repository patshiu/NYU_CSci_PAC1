//TO RUN 'java Car_v2'

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 4 for PAC1 Fall 2015

----------------------------------
Lab 4: Car
----------------------------------

hen your program will call the appropriate method (move horizontal or move vertical -- see below) with suitable arguments.

IMPLEMENTED METHODS
randomPosition(); //BETWEEN 1 to 20 NOT 0 to 20 

colorAssignment();

moveCarHorizontally();

moveCarVertically();

changeIgnitionState();

reportTheStateOfTheCar(); 

For example, the output might look something like this (indicating car location on the grid with 'R' because the car's color is red):
  Car Information
  Color: red
  Ignition: on
  Location: (5, 17)
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - R - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
  - - - - - - - - - - - - - - - - - - - -
*/

import java.util.*;

public class Car_v2 {


	public static void main(String args[]){

		//Scanner
		final Scanner sc = new Scanner(System.in);
		//Quit boolean
		Boolean keepRunning = true;

		//CAR VARIABLES
		boolean ignition; 
		char colors[] = {'R', 'G', 'B', 'W', 'S'}; //'R'=red, 'G'=green, 'B'=blue, 'W'=white, 'S'=silver 
		String colorsReadable[] = {"Red", "Green", "Blue", "White", "Silver"};
		int colorCode;  
		int xpos;
		int ypos; 

		//CREATE CAR BY INITIALIZING VARIABLES
		ignition = false; 
		colorCode = colorAssignment(); //Assign random color
		System.out.println("Car color: " + colorsReadable[colorCode] + "\tColorChar: " + colors[colorCode] + "\tColorCode: " + colorCode);
		xpos = randomPosition(); 
		ypos = randomPosition();
		

		//LISTEN FOR COMMANDS, RETURN KEY CODE IF CORRECT
		reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
		youCanHaveWhateverYouLike();
		while(keepRunning == true){		
			switch (utilListener(sc.next())){
				case -1: 
					System.out.println("Bad input, try again.");
					break; 

				case 0:
					System.out.println("Exiting...");
					keepRunning = false;
					break;

				case 1:
					ignition = changeIgnitionState(ignition);
					reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
					youCanHaveWhateverYouLike();
					break;

				case 2:
					//CHECK IGNITION
					if (ignition == true){
						//Ask user which direction 
						//Ask user how far
						int cardinals = -1; // -1: not set yet 	0: North-South 	1:East-West
						whereYouGoing();
						do { cardinals = listenWhere(sc.next()); } while ( cardinals == -1);
						int distance = -100; // -100: not set yet
						howFar();
						do { distance = listenDistance(sc.next(), xpos, ypos, cardinals); } while ( distance == -100);
						if (cardinals == 0 ){//If moving North-South
							ypos = ypos + distance; 
						}
						if (cardinals == 1){//If moving East-West
							xpos = xpos + distance;
						}
						reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
						youCanHaveWhateverYouLike();
						break;

					} else {
						System.out.println("Ignition is off. Please start vehicle first.");
						break;
					}

				default:
					System.out.println("Something has gone terribly wrong.");
			}
		}


	}

	/* -------------------------
	UTILITY FUNCTIONS 
	-------------------------*/

	/* LISTENTER — IGNITE, DRIVE or QUIT
	This listener evaluates user input. 
	-1 = invalid input
	0 = exit();
	1 = toggleIgnition();
	2 = moveCarVertically / moveCarHorizontally
	-------------------------*/
	public static int utilListener(String userInput){
		//Listen for exit
		if (userInput.equals("Q") || userInput.equals("q")){
			return 0;
		}
		if (userInput.equals("1")){
			return 1; 
		}
		if (userInput.equals("2")){
			return 2;
		} else {
			return -1; 
		}
	}

	/* LISTENER — HORIZONTAL OR VERTICAL
	-------------------------*/

	/* ASK - What would you like to do? 
	-------------------------*/
	public static void youCanHaveWhateverYouLike(){
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("What would you like to do?");
		System.out.println("\t1: turn the ignition on/off");
		System.out.println("\t2: change the position of car");
		System.out.println("\tQ: quit this program");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	/* Toggle Ignition
	-------------------------*/
	public static boolean changeIgnitionState(boolean carStarted){
		boolean newState = (carStarted ? false : true);
		return newState;
	}

	/* ASK - In which direction would you like to move the car? 
	-------------------------*/
	public static void whereYouGoing(){
		System.out.println("In which direction would you like to move the car?");
		System.out.println("\tH: horizontal");
		System.out.println("\tV: vertical");
	}

	/* LISTEN & VERIFY user cardinal directions
	-------------------------*/
	public static int listenWhere(String userInput){
		if (userInput.equals("V") || userInput.equals("v")) {
			return 0; //North-South
		} 
		if (userInput.equals("H") || userInput.equals("h")) {
			return 1; //East-West
		}
		else {
			System.out.println("Invalid direction. Try again.");
			return -1;
		}
	}


	/* ASK - How far (negative value to move left or up)?  */
	public static void howFar(){
		System.out.println("How far? Use negative value to move left or up.");
	}

	/* LISTEN & VERIFY user distance directions
	-------------------------*/
	public static int listenDistance(String userInput, int x, int y, int cardinals ){

		//Check if user input is integer
		int distance;

		try {
			distance = Integer.parseInt(userInput);
		}	catch(NumberFormatException nfe)  {  
			System.out.println("Invalid input. Try again.");
			return -100;  
		} 

		//Check if integer is within range
		//REMEBER THE GRID IS FROM 1,1 to 20,20 NO 0,0
		if (cardinals == 0 ) { //If moving North-South
			if( (y + distance) < 21 && ((y + distance) ) > 0){
				return distance; 
			} else {
				System.out.println("Can't move that far. Try again.");
				return -100;
			}
		} 
		if (cardinals == 1) { //If moving East-West
			if ( (x + distance) < 21 && (x + distance) > 0 ){ //Check that there is space to move
				return distance; 
			} else {
				System.out.println("Can't move that far. Try again.");
				return -100;
			}
		}
		else {
			System.out.println("Invalid cardinals.");
			return -100;
		}
	}


	/* REPORT CAR STATUS FUNCTIONS
	-------------------------*/
	public static void reportCarStatus(Boolean ignition, String c, char cShort, int x, int y ) {
	    //WELCOME MESSAGE
		System.out.print("\u001b[2J" + "\u001b[H");
		System.out.flush();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nCAR INFORMATION");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (ignition) {
			System.out.println("Car ignition is ON.");
		} else {
			System.out.println("Car ignition is OFF.");
		}
		System.out.println("Color:\t" + c );
		System.out.println("x: " + x + "\t y: " + y);
		printGrid(x,y,cShort);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	/* Print Grid
	-------------------------*/
	public static void printGrid(int x, int y, char c){
		int row = 1;
		int col = 1; 
		while( row < 21 ){
			if (row != y){//check if this row contains car
				System.out.println("- - - - - - - - - - - - - - - - - - - - ");
				row++;
			} else {
				while ( col < 21 ){
					if (col == x ){ //if this column does not contain car
						if (col == 20){
							System.out.print( c + " \n");
							col++; 	
						} else {
							System.out.print( c + " ");
							col++; 
						}

					} else if (col == 20) {
						System.out.print("- \n"); //Start new line if at end of row
						col++;
					} else {
						System.out.print("- ");
						col++; 
					}
				}
				row++;
			}
		}		
	}


	/* INITIALIZATION FUNCTIONS
	-------------------------*/
	public static int colorAssignment(){ //Returns a random number between
		Random rando = new Random(); 
		int randomNum = rando.nextInt(5); //Returns value between 0 & 4 
		return randomNum; 
	} 

	public static int randomPosition(){ //Returns a random number between 1 & 20 
		Random rando = new Random();
		int randoNum = rando.nextInt(20); //Returns a random value between 0 inclusive and 20 exclusive (So 0 to 19)
		randoNum++; //Increment by one to avoid returning 0 and include returning 20. 
		return randoNum; 
	}


}