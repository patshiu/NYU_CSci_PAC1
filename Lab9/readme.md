#Grader's Comments

**Grade:  10.0    (max 10.0)**
Submitted Attachments  
File attachment RunCalculator.java ( 7 KB; Sep 29, 2015 10:49 pm )  

#####Additional instructor's comments about your submission
Great job.  Your program compiled and ran successfully according to the assignment's spec.

One note about the functioning of your program: the way the division by zero error is handled, the program assumes that the user meant to do proper division.  However, maybe the user meant to use another operator with zero but used "/" by accident.  The assignment spec did not require you to ask for a new operator per se (even though the sample output shows that), so I did not take any points off.  You handled the error and left the buffer unchanged - that was the priority, according to the spec.  But from a user experience point of view, it might be better to make no assumptions about the operator that the user wanted to use prior to the error.  The way that you handled the unknown operator was perfect - if your division by zero error handling could be the same, that would be great.



----------------------------------------------------
#Lab 3: Calculator

Write a simple calculator program that can do addition, subtraction, multiplication and division.

The program should initially accept 2 operands and a mathematical operator (+, -, *, /) then store the results. The succeeding steps should only ask for the operator and a new input number. The operator would then be applied to the previously stored result and the new input number. Also include two special special operators, c and x, where c would clear the buffer and x will exit the program.

Ex (the blue text indicates user input):

    1st input: 3
    op: +
    2nd input: 2.5
    ans: 5.5
    op: *
    more input: 3
    ans: 16.5
    op: c
    ans: 0.0
    op: +
    more input: 10
    ans: 10.0
    op: x
  
Your program should also display an error message if the user attempts to divide a number by 0. The buffer should not be cleared if this error occured.

Ex:

    1st input: -3
    op: *
    2nd input: 2
    ans: -6.0
    op: /
    more input: 0
    Error: division by zero
    op: +
    more input: 10
    ans: 4.0
  
Similarly, your program should display an error message for any unrecognized operator. It should also not affect the value of the buffer.

Ex:

    1st input: -3
    op: $
    2nd input: 2
    Error: Unknown operator $
    op: +
    more input: 6
    ans: 3.0
  
Good luck!

