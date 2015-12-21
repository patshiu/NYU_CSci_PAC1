//TO RUN 'java CarArray'

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 5 for PAC1 Fall 2015

----------------------------------
Lab 5: Array of Cars
----------------------------------
Write a program that simulates and stores the qualities of an array 
of ten cars. The qualities (or state variables) you should store for 
each car are the following:

Color ('R'=red; 'G'=green; 'B'=blue; 'W'=white; 'S'=Silver)
Ignition (false=off; true=on)
Position (any X, Y coordinate in a 20 X 20 grid)

IMPLEMENTED METHODS

reportGarage();// Prints out list of all cars

pickCar(); Loads any car between #1 to #10 

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

public class CarArray_v2 {

	public static void main(String args[]){

		//Scanner
		final Scanner sc = new Scanner(System.in);

		//Quit functionality
		Boolean keepRunning = true;

		//DISPLAY KEYS
		char colors[] = {'R', 'G', 'B', 'W', 'S'}; //'R'=red, 'G'=green, 'B'=blue, 'W'=white, 'S'=silver 
		String colorsReadable[] = {"Red", "Green", "Blue", "White", "Silver"};

		final int TOTAL_CARS = 10; 

		//ABOUT garage[][]
		//This is a 2D array containing data on your cars
		//[Car Num],[IgnitionCode, ColorCode, xPos, yPos ]
		// Where Ignition Code 0 = Off
		// Ignition Code
		int[][] garage = new int[10][4];

		//DATA on CURRENT CAR
		int currentCar = -1; //current car number NOT IT'S ARRAY INDEX
		int currentCarIndex;
		boolean ignition; 
		int colorCode;  
		int xpos;
		int ypos; 


		//CREATE CAR BY INITIALIZING VARIABLES
		for (int i = 0; i < TOTAL_CARS; i++){
			garage[i][0] = 0; //Set ignition to false
			garage[i][1] = colorAssignment();
			garage[i][2] = randomPosition(); 
			garage[i][3] = randomPosition(); 
		}

		

		//INITIAL VIEW
		//ReportGarage
		reportGarage( garage, colorsReadable );
		askWhichCar();

		do{ currentCar = listenWhichCar(sc.nextLine(), TOTAL_CARS); } while(currentCar == -1);
		//STORE DATA OF CURRENTLY USED CAR IN FRIENDLY VARS
		currentCarIndex = currentCar-1; //Decrease by 1 to get array index	
		ignition = garage[currentCarIndex][0] == 0 ? false : true ;
		colorCode = garage[currentCarIndex][1];
		xpos = garage[currentCarIndex][2];
		ypos = garage[currentCarIndex][3];

		reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
		youCanHaveWhateverYouLike(currentCar);

		//JUST FOR DEBUGGING, set initial car to 1
		//currentCar = 1;
		//youCanHaveWhateverYouLike(currentCar);
		
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
					garage[currentCarIndex][0] = garage[currentCarIndex][0] == 0 ? 1 : 0; 
					ignition = changeIgnitionState(ignition);
					reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
					youCanHaveWhateverYouLike(currentCar);
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
							garage[currentCarIndex][3] = ypos;
						}
						if (cardinals == 1){//If moving East-West
							xpos = xpos + distance;
							garage[currentCarIndex][2] = xpos;
						}
						reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
						youCanHaveWhateverYouLike(currentCar);
						break;

					} else {
						System.out.println("Ignition is off. Please start vehicle first.");
						break;
					}

				case 3:
					//ReportGarage
					reportGarage( garage, colorsReadable );
					askWhichCar();
				do { currentCar = listenWhichCar( sc.next(), TOTAL_CARS); } while ( currentCar == -1 );
					//NEW CAR NOW SELECTED
					//Update all convenience vars
					currentCarIndex = currentCar-1; //Decrease by 1 to get array index
					ignition = garage[currentCarIndex][0] == 0 ? false : true ;
					colorCode = garage[currentCarIndex][1];
					xpos = garage[currentCarIndex][2];
					ypos = garage[currentCarIndex][3];

					//REPORT NEW CAR STATUS, ASK USER FOR NEXT INSTRUCTIONS
					reportCarStatus(ignition, colorsReadable[colorCode], colors[colorCode], xpos, ypos);
					youCanHaveWhateverYouLike(currentCar);
					break;

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
	3 = change cars
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
		}
		if (userInput.equals("C") || userInput.equals("c") ){
			return 3; 
		} else {
			return -1; 
		}
	}

	/* LISTENER — HORIZONTAL OR VERTICAL
	-------------------------*/

	/* ASK - What would you like to do? 
	-------------------------*/
	public static void youCanHaveWhateverYouLike(int carNum){
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("You are currently in car #" + carNum);
		System.out.println("What would you like to do?");
		System.out.println("\t1: turn the ignition on/off");
		System.out.println("\t2: change the position of car");
		System.out.println("\tC: change cars");
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

	/* Ask which car
	-------------------------*/
	public static void askWhichCar(){
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Which car would you like to use (1-10)?");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}

	/* LISTEN & VERITY car user picks 
	If input is valid, the select car number will be returned
	-------------------------*/
	public static int listenWhichCar(String userInput, int MAX_CARS){
		int pickedCar; 
		try{
			pickedCar = Integer.parseInt(userInput);
		} catch(NumberFormatException nfe){
			System.out.println("Invalid input. Try again.");
			return -1; 
		}
		if (pickedCar > MAX_CARS || pickedCar <= 0){
			System.out.println("Invalid input. Try again.");
			return -1; 	
		}
		return pickedCar; 
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

	/* PRINT ALL CARS IN GARAGE
	-------------------------*/
	public static void reportGarage(int[][] garage, String[] colorsReadable){
		System.out.print("\u001b[2J" + "\u001b[H");
		System.out.flush();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nALL YOUR RIDES");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (int i = 0; i < garage.length; i++){
			int carNum = i + 1; 
			String ignition = garage[i][0] == 0 ? "OFF" : "ON"; 
			String color = colorsReadable[garage[i][1]];
			int posx = garage[i][2];
			int posy = garage[i][3];
			System.out.println("\nCAR #" + carNum);
			System.out.println("Ignition: " + ignition + "\tColor: " + color + "\tLocation: " + posx + "," + posy);
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