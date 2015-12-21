#Grader's Comments

**Grade:  9.0    (max 10.0)**
Submitted Attachments  
File attachment  Car_v2.java ( 9 KB; Oct 4, 2015 3:32 pm ) 

#####Additional instructor's comments about your submission
-1 for using class/static variables.  The assignment spec told you not to do this: IMPORTANT: You may not use any class variables. This means that you are not allowed to declare static variables outside of a method. The variables representing the car's properties must be declared within main().  

Otherwise, great job.  Your program compiled and ran according to the assignment's spec.  


----------------------------
#Lab 3: Car

Write a program that simulates a car and stores the following 3 properties:
- **Ignition:** `false`=off, `true`=on
- **Color:** `'R'`=red, `'G'`=green, `'B'`=blue, `'W'`=white, `'S'`=silver
- **Position:** any `(X, Y)` coordinate in a 20x20 grid *(with position **(1, 1)** at the top left)*  


###main() should start by doing the following:
- Set the ignition to false.
- Choose a random X position between 1 and 20 and a random Y position between 1 and 20, and set this as the initial position of the car.
- Set a random color ('R', 'G', 'B', 'W', 'S') for the car.
After this beginning state is assigned to the car, you should then have a loop which asks the user what he/she wants to do next. For example:

```

  What would you like to do?
  1: turn the ignition on/off
  2: change the position of car
  Q: quit this program


```

If the user selects '1' to turn the car's ignition on or off, then your program will call the method that changes the ignition state (explained below), and save the new ignition value in place of the old one.

If the user selects '2' to change the car's position, you will prompt the user for a direction. For example:

```
  In which direction would you like to move the car?
  H: horizontal
  V: vertical


```

And then prompt for the distance. For example:


```
  How far (negative value to move left)?    or    How far (negative value to move up)?

```

Then your program will call the appropriate method (move horizontal or move vertical -- see below) with suitable arguments.

**IMPORTANT:** This program should be a single class with a main() method and the six methods described below.

**IMPORTANT:** You may not use any class variables. This means that you are not allowed to declare static variables outside of a method. The variables representing the car's properties must be declared within main().

**IMPORTANT:** You should verify that the user entered a valid choice, but this is the only error checking you are allowed to do in main(). All other error checking must be handled within the called method. For example, once the user chooses to move the car, the error checking to ensure the ignition is on and that the boundaries have not been exceeded must be done within the method that updates the car's position.

You should write a method for each of the following:  
  - random position: Randomly picks a number between 1 and 20 to set as an X or Y coordinate. This method should only be called once for X and once for Y at the beginning of the program.

  - color assignment: Randomly picks one of the five colors and returns the corresponding char ('R', 'G', 'B', 'W', 'S') to main(). This method should be called once at the beginning of the program. The color will not change during the execution of the program.

  - move car horizontally: This method will take as arguments the current ignition state, the current X coordinate, the number of spaces you would like to move the car (a negative value will move the car left; positive value will move the car right). It will return the new horizontal position of the car after it is moved.

    - If the car's ignition is not on, this method tells the user that he/she must turn the ignition on first and returns the current X position (does not move the car). 

    - If the user tries to move the car beyond the border of the 20x20 grid, this method outputs an error message and returns the current X position (does not move the car).

  - move car vertically: This method will take as arguments the current ignition state, the current Y coordinate, the number of spaces you would like to move the car (a negative value will move the car up; positive value will move the car down). It will return the new vertical position of the car after it is moved. 

  - If the car's ignition is not on, this method tells the user that he/she must turn the ignition on first and returns the current Y position (does not move the car). 

  - If the user tries to move the car beyond the border of the 20x20 grid, this method outputs an error message and returns the current Y position (does not move the car).

  - change ignition state: This method will return the opposite of the ignition value which is given to it as a parameter (e.g. it will turn the car off when it is on or on when it is off). The new ignition value is returned for storage in main().

  - report the state of the car: After each action, you should print the "state" of the car. This method will take all the car's characteristics as arguments and report the status of each, including a visual representation of the car's position in the grid. 

  - For example, the output might look something like this (indicating car location on the grid with 'R' because the car's color is red):

  ```
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

  ```
