//TO RUN 'java testCar'

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 7 for PAC1 Fall 2015

----------------------------------
Lab 7: Car with OOP
----------------------------------
Write a program that creates Car objects. Your Car class should 
store the following qualities of a car:

Color ('R'=red; 'G'=green; 'B'=blue; 'W'=white; 'S'=Silver)
Ignition (false=off; true=on)
Position (any X, Y coordinate in a 20 X 20 grid)

main() will work much the same way as in previous assignments. 
It should create an array of 10 Car instances with random color, 
ignition set to off/false, and position randomly assigned. 

Once you assign the beginning state to the cars you should have 
a loop which asks the user what she wants to do next. For example:

* Which car would you like to use next (1-10)?

* Then ask the user what she wants to do to the car she selected. For example:

------
What would you like to do next (1 - change ignition; 2 - change position 
of car; 3 - quit this program)?
------

* If the user wants to change the car's ignition status it will call 
  the car’s instance method which changes the ignition state (explained below).

* If the user wants to change the car's position, main() will prompt the user 
  for a direction e.g. see below Then the appropriate method (move horizontal 
  or move vertical -- see below) would be called.

------
What direction would you like to move the car (1 - horizontal; 2 - vertical)?) 
and the distance (e.g. How far (negative value to move left)?; How far (negative 
value to move up)?). 
------

* You must use care to use the correct car's variables when calling the methods.

*/

import java.util.*;

public class testCar {

	public static void main(String args[]){

		//Scanner
		final Scanner sc = new Scanner(System.in);

		//Quit functionality
		Boolean keepRunning = true;


		final int TOTAL_CARS = 10; 

		//ABOUT garage[]
		//This is a Car object array containing your cars
		Car[] garage = new Car[TOTAL_CARS];


		//CREATE CAR BY INITIALIZING VARIABLES
		for (int i = 0; i < TOTAL_CARS; i++){
			garage[i] = new Car();
		}

		

		//INITIAL VIEW
		//ReportGarage
		reportGarage( garage );
		askWhichCar();
		int currentCar = -1; 
		int currentCarIndex = 0; 

		do{ currentCar = listenWhichCar(sc.nextLine(), TOTAL_CARS); } while(currentCar == -1);
		//STORE DATA OF CURRENTLY USED CAR IN FRIENDLY VARS
		currentCarIndex = currentCar-1; //Decrease by 1 to get array index	
		garage[currentCarIndex].toString();
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
					garage[currentCarIndex].changeIgnitionState();
					garage[currentCarIndex].toString();
					youCanHaveWhateverYouLike(currentCar);
					break;

				case 2:
					
					//Ask user which direction 
					//Ask user how far
					int cardinals = -1; // -1: not set yet 	0: North-South 	1:East-West
					whereYouGoing();
					do { cardinals = listenWhere(sc.next()); } while ( cardinals == -1);
					if (cardinals == 0 ){//If moving North-South
						System.out.println("How far would you like to move?");
						garage[currentCarIndex].moveVertical(sc.next());
					}
					if (cardinals == 1){//If moving East-West
						System.out.println("How far would you like to move?");
						garage[currentCarIndex].moveHorizontal(sc.next());
					}
					youCanHaveWhateverYouLike(currentCar);
					break;

				case 3:
					//ReportGarage
					reportGarage( garage );
					askWhichCar();
				do { currentCar = listenWhichCar( sc.next(), TOTAL_CARS); } while ( currentCar == -1 );
					//NEW CAR NOW SELECTED
					currentCarIndex = currentCar-1; //Decrease by 1 to get array index
				

					//REPORT NEW CAR STATUS, ASK USER FOR NEXT INSTRUCTIONS
					garage[currentCarIndex].toString();
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


	/* PRINT ALL CARS IN GARAGE
	-------------------------*/
	public static void reportGarage(Car[] garage){
		System.out.print("\u001b[2J" + "\u001b[H");
		System.out.flush();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nALL YOUR RIDES");
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (int i = 0; i < garage.length; i++){
			int carNum = i + 1; 
			System.out.println("\nCAR #" + carNum);
			garage[i].getSummary();
		}
	}


}