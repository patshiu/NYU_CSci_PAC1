#Grader's Comments
File attachment TimeDifference.java ( 6 KB; Sep 19, 2015 8:56 pm )
Additional instructor's comments about your submission
-1 for incorrect output due to a calculation error: the difference between 230000 and 210001 should be 15959, not -15959.  For other input pairs as well, your values were correct but the signs were not.

Your program calculated the values of the differences but did not give the right positivity/negativity.  Your code shows a lot of work and thought, but unfortunately a small bug caused it to give incorrect answers for each output pair: you subtracted the first time from the second, rather than the other way around.  Please make sure to check that your code matches the sample output in future assignments.

----------------------------------------------------------


#Lab 2: Time Difference

Write a program that accepts user input of two time values and computes the difference between them.

The times are read into the program as integers of at most 6 digits (without leading zeros): `HHMMSS`, where `HH` represents hours according to a 24-hour clock (0-23), `MM` represents minutes (0-59) and SSrepresents seconds (0-59). The difference should be displayed in the same format.

The difference can be either positive or negative. It is obtained by subtracting the second time from the first time.

For the purposes of this assignment, you may assume that the inputs are correct--that is, the input values will be valid positive integers no more than 6 digits in length, and the hours, minutes and seconds will be within the proper ranges (e.g.: seconds will be within 0-59).

Ex (the blue text indicates user input):

``` 
    Enter first time: 230000
    Enter second time: 210001
    Time difference: 15959
 
    Enter first time: 123245
    Enter second time: 112955
    Time difference: 10250
 
    Enter first time: 23245
    Enter second time: 32815
    Time difference: -5530
 
    Enter first time: 245
    Enter second time: 235
    Time difference: 10

```

##Notes and Hints:

Please follow the format given in the example above.
The above example is for 4 separate runs corresponding to the following times.
11:00:00 PM and 9:00:01 PM [difference is 1:59:59, or 1 hr 59 mins 59 secs]
12:32:45 PM and 11:29:55 AM [= 1:02:50, or 1 hr 2 mins and 50 secs]
2:32:45 AM and 3:28:15 AM [= -0:55:30, or negative 55 mins 30 secs]
0:02:45 AM and 0:02:35 AM [= 0:00:10, or in0other words 10 seconds]

###Example algorithm for this assignment:
Isolate the different parts of each time: hours, minutes and seconds (hint: use the mod operator % and/or integer division).
Compute the times in terms of seconds (1 hour = 3600 seconds, 1 minute = 60 seconds).
Calculate the difference between the times in terms of seconds.
Isolate hours, minutes and seconds from the difference value (again, use the mod operator % and/or integer division, but with a different base ... hint, hint, hint).
Create the new integer time value in the form HHMMSS and output it.

You are NOT being asked to convert a PM or AM time to 24-hour time format. The user should be entering the time in 24-hour format.

You must evaluate the input and produce the output by isolating hours, minutes and seconds using mathematical computations (see example algorithm above). You are not allowed to use string manipulation techniques for this exercise. Examples of string manipulation techniques include:
parsing the input into separate strings (hours, minutes, seconds)
concatenating hours, minutes, seconds into a string for output
Good luck!

