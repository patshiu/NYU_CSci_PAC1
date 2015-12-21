//TO RUN 'java CarArray'

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

******

You must use care to use the correct car's variables when calling the methods.

Each of the qualities will be represented by an instance variable in the Car class.

1. assignColor() 
Randomly picks one of the five colors (represented by a letter) and returns the corresponding char. 
This method should be called once from the Car's class constructor. The color will not change during 
the execution of the program.

2. moveCarHorizontally() 
  -- This method will need the number of spaces you would like to move the car (a negative value will move 
     the car left; positive value will move the car right) and will adjust the xPosition of the car.
  -- If the car's ignition is not on, this method tells the user that s/he must turn the ignition on first 
     and leaves the xPosition unchanged.
  -- If the user tries to move the car beyond the border of the 20 X 20 grid, this method should leaves the 
     xPosition unchanged.; instead you should report an error message.

2. moveCarVertically()
This method will need the number of spaces you would like to move the car (a negative value will move the car up; positive value will move the car down) and will adjust the yPosition of the car.
If the car's ignition is not on, this method tells the user that s/he must turn the ignition on first and leaves the yPosition unchanged.
If the user tries to move the car beyond the border of the 20 X 20 grid, this method should leaves the yPosition unchanged.; instead you should report an error message.

3. changeIgnitionStatus() 
This method will turn the car off when it is on or on when it is off. 
(i.e. change the value of the instance variable).

4. get color: This method will return the color of the car as string.

5. get ignition status: This method will return the ignition status of the car as a boolean.

6. get x position: This method will return the x position of the car as an int.

7. get y position: This method will return the y position of the car as an int.

8. toString: After each action, you should print the "state" of the currently modified Car. 
This method will call the “get” methods and report the status of each (including a copy of 
the cars position in the grid). For example:

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
public class Car{
  /*Instance vars*/
  private final char color; 
  private Boolean carStarted; 
  private int posX; 
  private int posY;

  /*For better printing*/
  private String colorStr; 

  Car(){
    this.posX = randomPosition(); 
    this.posY = randomPosition(); 
    this.carStarted = false; 
    this.color = colorAssignment();
  }
  

  /* GET FUNCTIONS
  -------------------------*/
  public String getColor(){
    String s = "" + this.color; 
    return s; 
  }

  public boolean getIgnition(){
    return this.carStarted; 
  }

  public int getPosX(){
    return this.posX; 
  }

  public int getPosY(){
    return this.posY; 
  }


  /* REPORT CAR STATUS FUNCTIONS
  -------------------------*/
  public String getSummary(){
    System.out.println("Ignition: " + this.carStarted + "\t\tColor: " + this.colorStr + "\tLocation: " + this.posX + "," + this.posY);
    String s = "";
    return s; 
  }

  public String toString(){
      //WELCOME MESSAGE
    System.out.print("\u001b[2J" + "\u001b[H");
    System.out.flush();
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\nCAR INFORMATION");
    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    if (getIgnition()) {
      System.out.println("Car ignition is ON.");
    } else {
      System.out.println("Car ignition is OFF.");
    }
    System.out.println("Color:\t" + this.colorStr );
    System.out.println("x: " + this.posX + "\t y: " + this.posY);
    printGrid(this.posX, this.posY, this.color);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    String s = "";
    return s; 
  }

  /* Print Grid
  -------------------------*/
  private void printGrid(int x, int y, char c){
    int row = 1;
    int col = 1; 
    while( row < 21 ){
      if (row != y){//check if this row contains car
        System.out.println("- - - - - - - - - - - - - - - - - - - - ");
        row++;
      } else {
        while ( col < 21 ){
          if (col == x ){ //if this column contains car
            if (col == 20){
              System.out.print( c + " \n");
              col++;  
            } else {
              System.out.print( c + " ");
              col++; 
            }
          } else if (col == 20) { //else is colum does not contain car
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

  /* SET FUNCTIONS
  -------------------------*/

  public void changeIgnitionState(){
    boolean newState = (carStarted ? false : true);
    carStarted = newState;
  }


  private void setPosX(int delta){
    this.posX = this.posX + delta;
  }

  private void setPosY(int delta){
    this.posY = this.posY + delta;
  }

  public void moveHorizontal(String userInput){
    if (carStarted){

      int distance;

      //Check if user input is integer
      try {
        distance = Integer.parseInt(userInput);
      } catch (NumberFormatException nfe)  {  
        System.out.println("Invalid input. Try again.");  
        return;
      } 

      //Check if integer is within range
      //REMEBER THE GRID IS FROM 1,1 to 20,20 NO 0,0
      //If moving East-West
      if ( (this.posX + distance) < 21 && (this.posX + distance) > 0 ){ //Check that there is space to move
        setPosX(distance); 
        this.toString();
      } else {
        System.out.println("Can't move that far. Try again.");
      }
    } else {
      System.out.println("Car's not started yet.");
    }

  }

  public void moveVertical(String userInput){
    if (carStarted){
      int distance;

      //Check if user input is integer
      try {
        distance = Integer.parseInt(userInput);
      } catch (NumberFormatException nfe)  {  
        System.out.println("Invalid input. Try again.");  
        return;
      } 

      //Check if integer is within range
      //REMEBER THE GRID IS FROM 1,1 to 20,20 NO 0,0
      //If moving North-South
      if( (this.posY + distance) < 21 && ((this.posY + distance) ) > 0){
        setPosY(distance); 
        this.toString();
      } else {
        System.out.println("Can't move that far. Try again.");
      }
    } else {
      System.out.println("Car's not started yet.");
    }

  }

  /* INITIALIZATION FUNCTIONS -- KEPT PRIVATE
  -------------------------*/
  private char colorAssignment() {
    char colors[] = {'R', 'G', 'B', 'W', 'S'};
    int rando = colorRando();

    String colorsReadable[] = {"Red", "Green", "Blue", "White", "Silver"};
    this.colorStr = colorsReadable[rando];

    return colors[rando];
  }

  private int colorRando(){ //Returns a random number between
    Random rando = new Random(); 
    int randomNum = rando.nextInt(5); //Returns value between 0 & 4 
    return randomNum; 
  } 

  private int randomPosition(){ //Returns a random number between 1 & 20 
    Random rando = new Random();
    int randoNum = rando.nextInt(20); //Returns a random value between 0 inclusive and 20 exclusive (So 0 to 19)
    randoNum++; //Increment by one to avoid returning 0 and include returning 20. 
    return randoNum; 
  }

}























