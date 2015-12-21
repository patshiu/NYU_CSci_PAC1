#Grader's Comments

**Grade:  10.0    (max 10.0)**
Submitted Attachments  
File attachment  CarArray.java ( 13 KB; Oct 10, 2015 4:46 pm )

#####Additional instructor's comments about your submission
Great job.  Your program compiled and ran successfully according to the assignment's spec.


----------------------------------------------------
#Lab 5 : Array of Cars

Write a program that simulates and stores the qualities of an array of ten cars. The qualities (or state variables) you should store for each car are the following:

Color ('R'=red; 'G'=green; 'B'=blue; 'W'=white; 'S'=Silver)
Ignition (false=off; true=on)
Position (any X, Y coordinate in a 20 X 20 grid)

Each of these qualities will be represented by a state variable in main().

main() should start by assigning the initial position of each car randomly (choose an X position between 1 and 20 and a Y position between 1 and 20). Then call the method which assigns a color to each car (see below). You shoud initialize the ignition to false. Once you assign the beginning state to the cars you should have a loop which asks the user what she wants to do next. For example:

```
Which car would you like to use next (1-10)?

```

Then ask the user what she wants to do to the car she selected. For example:

```
What would you like to do next (1 - change ignition; 2 - change position of car; 3 - quit this program)?

```

If the user wants to change the car's ignition status it will call the method which changes the ignition state (explained below) and save the new ignition in place of the old ignition.  

If the user wants to change the car's position, main() will prompt the user for a direction (e.g. What direction would you like to move the car (1 - horizontal; 2 - vertical)?) and the distance (e.g. How far (negative value to move left)?; How far (negative value to move up)?). Then the appropriate method (move horizontal or move vertical -- see below) would be called with suitable arguments.  

- You must use care to use the correct car's variables when calling the methods.

- You cannot use any class variables.

**Note:** The only error checking in main() verifies that you have a correct choice. Once the user chooses to move the car, the check for the ignition and for boundaries is executed in the called method.

You should write a method for each of the following:

- __color assignment:__ Randomly picks one of the five colors (represented by a letter) and returns the corresponding char to main(). This method should be called once at the beginning of the program. The color will not change during the execution of the program.  

- __move car horizontally:__ This method will need the current X coordinate and the number of spaces you would like to move the car (a negative value will move the car left; positive value will move the car right). It will return the new horizontal position of the car after it is moved.
If the car's ignition is not on, this method tells the user that s/he must turn the ignition on first and returns the current X position.
If the user tries to move the car beyond the border of the 20 X 20 grid, this method should not move the car; instead you should report an error message and return the current X position.  
Hints: You will also have to pass this method the ignition status.  

- __move car vertically:__ This method will need the current Y coordinate and the number of spaces you would like to move the car (a negative value will move the car up; positive value will move the car down). It will return the new vertical position of the car after it is moved.
If the car's ignition is not on, this method tells the user that s/he must turn the ignition on first and returns the current Y position. 
If the user tries to move the car beyond the border of the 20 X 20 grid, this method should not move the car; instead you should report an error message and return the current Y position.  
Hints: You will also have to pass this method the ignition status.  

- __change ignition status:__ This method will return the opposite of the ignition value which is given to it as a parameter (e.g. it will turn the car off when it is on or on when it is off). The new ignition value is returned for storage in main()  

- __report the state of the car:__ After each action, you should print the "state" of the car. This method will take all the car's characteristics as parameters and report the status of each (including a copy of the cars position in the grid). Note: since you are only printing one car at a time is does not matter if more than one is in the same position.  

**For example:**


```
Car Stats:
Color: Red
Ignition: On
Location: 5, 17

_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ R _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 

```

*(the R corresponds to the color of the car)

