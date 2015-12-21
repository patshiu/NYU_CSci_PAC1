#Grader's Comments

**Grade:  10.0    (max 10.0)**
Submitted Attachments  
File attachment  Pat_SHIU_small_int.zip ( 3 KB; Nov 4, 2015 6:44 pm ) 

#####Additional instructor's comments about your submission
Great job.  Your code compiled and ran successfully according to the assignment's spec.


----------------------------------------------------
#Lab 6 : Small Int Class 

Please read the assignment carefully. If you need help, don't hesitate to contact the TA or the Professor during their office hours.  

This assignment asks you to write a Java class called SmalInt (similar to a wrapper class). SmallInt contains the following:  

- A private instance variable named value (type int) is where the value of an integer is stored by the class constructor (see below). Although it's tempting to think of this as the decimal representation of the number, since that is how System.out.println displays it, it is actually stored internally in a binary format that you cannot see (we will change that in this assignment). 

- A public static CONSTANT named MAXVALUE. MAXVALUE should be used as an upper limit for the values SmallInt can represent. You should set MAXVALUE to 1000. However, keep in mind, the grader may change the value of MAXVALUE when testing your program. 

- A constructor (one parameter; remember that a constructor has the the same name as the class name and does not return a value) assigns an integer between 0 and MAXVALUE to value. That's all the constructor has to do, using the int parameter that is passed to it. However, the constructor must check to make sure the parameter value is between 0 and MAXVALUE. If it is not, it should print an informative error message (using System.out.println) and store the number 0 instead. 

- An instance getter method getDec (returns a String, no parameters) which will return a String representation of the decimal integer. The easiest way to create this String is to concatenate the int representation to an empty String.
An instance setter method setDec (returns void, accepts one int parameter) which assigns an integer between 0 and MAXVALUE to value. However, this setter must check to make sure the parameter value is between 0 and MAXVALUE. If it is not, it should print an informative error message (using System.out.println) and store the number 0 instead. You should use the good programming practices we discussed in class for the error checking (hint: It is better to write code just one time if you find yourself writing the same code in two methods (ie this setter and the constrcutor) 

- An instance getter method getBin (returns a String, no parameters) should take the number stored in the instance variable value, generate the corresponding binary string and return that string. YOU MUST WRITE THIS METHOD BY YOURSELF. YOU MAY NOT CALL A METHOD FROM THE JAVA LIBRARY TO DO THIS JOB. YOU MAY NOT COPY FROM A CLASSMATE. HOWEVER, YOU MAY TALK TO YOUR CLASSMATES ABOUT HOW TO DO IT. YOU HAVE TO FIGURE OUT HOW TO EXPRESS THIS IDEA USING A LOOP THAT WORKS AND IS WELL DOCUMENTED, EXPLAINING HOW IT WORKS ***WITH AN EXAMPLE***. Since the grader may change your MAXVALUE constant, you must generate the bits from RIGHT to LEFT. One way to do so is to use repeated division. See Liang Appendix F. Remember YOU MUST EXPLAIN WHY IT WORKS, USING AN EXAMPLE. Hint: the first division by 2 tells you whether the number is even or odd.  
For your explanation, choose a number, say between 70 and 250 for your example. Don't use the same example as a classmate, if you can avoid it. (Of course, some people may choose the same example by accident - that's OK.) This explanation MUST APPEAR IN YOUR COMMENTS.  
As each bit is generated, you have to concatenate it to the current value of the String you will return. Start with the empty string.
Here are some examples of decimal numbers and their corresponding binary representations: 
 
Decimal     | Binary
------------|-----------
0           |00000000
1           |00000001
2           |00000010
3           |00000011
7           |00000111
15          |00001111
16          |00010000
25          |00011001
128         |10000000
129         |10000001
254         |11111110
255         |11111111

    - Thus, for example, the decimal number 25 is represented in binary as 00011001, meaning 0 times 128 plus 0 times 64 plus 0 times 32 plus 1 times 16 plus 1 times 8 plus 0 times 4 plus 0 times 2 plus 1 times 1. Notice that EACH BIT CORRESPONDS TO A POWER OF 2. Make sure you understand how this works before you go any further.
 
- An instance getter method getHex (returns a String, no parameters) should take the number stored in the instance variable value, generate the corresponding hexadecimal string and return that string. YOU MUST WRITE THIS METHOD BY YOURSELF. YOU MAY NOT CALL A METHOD FROM THE JAVA LIBRARY TO DO THIS JOB. YOU MAY NOT COPY FROM A CLASSMATE. HOWEVER, YOU MAY TALK TO YOUR CLASSMATES ABOUT HOW TO DO IT. YOU HAVE TO FIGURE OUT HOW TO EXPRESS THIS IDEA USING A LOOP THAT WORKS AND IS WELL DOCUMENTED, EXPLAINING HOW IT WORKS ***WITH AN EXAMPLE***. Since the grader may change your MAXVALUE constant, you must generate the digits from RIGHT to LEFT. One way to do so is to use repeated division. See Liang appendix F. Remember YOU MUST EXPLAIN WHY IT WORKS, USING AN EXAMPLE.  
For your explanation, choose a number, say between 300 and 1000 for your example. Don't use the same example as a classmate, if you can avoid it. (Of course, some people may choose the same example by accident -that's OK.) This explanation MUST APPEAR IN YOUR COMMENTS.  
As each digit is generated, you have to concatenate it to the current value of the String you will return. Start with the empty string.  
Here are some examples of decimal numbers and their corresponding hexadecimal representations: 

Decimal     | Hexadecimal
------------|-------------
0           |0
1           |1
2           |2
9           |9
10          |a
11          |b
15          |f
16          |10
17          |11
26          |1a
100         |64
256         |100 


    - Thus, for example, the decimal number 100 is represented in hexadecimal as 64, meaning 6 times 16 plus 4 times 1. Notice that EACH DIGIT CORRESPONDS TO A POWER OF 16. Make sure you understand how this works before you go any further.  



You need to write a second public class (that means you will need two .java files) called TestSmallInt. TestSmallInt will have just one main method. It will prompt the user (using Scanner) for a number in the range 0 - MAXVALUE (note: You must use the static public constant MAXVALUE from the SmallInt class. Since you will not be in that class, you will need to use the ClassName.VarName syntax). Then it will simply instantiate a SmallInt object using SmallInt's constructor and print the decimal representation, the binary representation and the hexadecimal representation of the number entered by the user.

