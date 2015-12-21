#Grader's Comments

**Grade:  10.0    (max 10.0)**

Submitted Attachments  
File attachment SpiceUpYourLife.java ( 3 KB; Sep 14, 2015 12:00 am )  

#####Additional instructor's comments about your submission
Good work.  Your code compiled and it contained at least one escape sequence and one comment.

Please try to avoid using any online resources to generate code for you.  If you used it to check your work, that's OK.  Either way, it will be assumed in future assignments that you know how to create formatted strings yourself.

I noticed that you had one very, very long line of code for the print function.

You might want to consider using concatenation so that you can break up the string over several shorter lines:

For instance,

System.out.println("This is the first line\n" +
                             "This is the second line")

You can use the + symbol to concatenate strings.

--------------------
#Lab 1 'Song Lyrics':

Write a program that prints out the lyrics to one or several verses of your favorite song. Be sure to include at least one comment in the program and one escape sequence . Pay attention to formatting using the following codes:

`\n` line break
`\t` tab
`\"` double quote
`\'` single quote
`\\` backslash
Make sure you can run the program on the machine you will use for your homework this semester. That means you can compile (i.e. no syntax errors) and run the program.

Please also refer to Oracle's Java style guide for an authoritative guide about writing properly formatted Java code.

Resources:
[Java Style Guide](http://www.oracle.com/technetwork/java/codeconvtoc-136057.html)
[manual page for the Java language compiler](http://bit.ly/javac-man)
[manual page for the Java application launcher](http://bit.ly/java-man)
