//TO RUN ‘java RunCalculator '

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 3 for PAC1 Fall 2015

----------------------------------
Lab 3: Calculator
----------------------------------
Write a simple calculator program that can do addition, subtraction, 
multiplication and division.

The program should initially accept 2 operands and a mathematical 
operator (+, -, *, /) then store the results. The succeeding steps 
should only ask for the operator and a new input number. The operator 
would then be applied to the previously stored result and the new 
input number. Also include two special special operators, c and x, 
where c would clear the buffer and x will exit the program.
*/


import java.util.Scanner; 


public class RunCalculator {
  //GLOBAL VARIABLES
  final static String ANSI_CLS = "\u001b[2J";
  final static String ANSI_HOME = "\u001b[H";
  final static Scanner sc = new Scanner(System.in);
  static Boolean keepRunning = true;

  //KEY MAPPINGS
  final static String exit = "x"; 
  final static String clear = "c";

  public static void main ( String args[] ){
    //WELCOME MESSAGE
    System.out.print(ANSI_CLS + ANSI_HOME);
    System.out.flush();
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\nHI-TEK ABACUS MATH MACHINE");
    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("COMMANDS:");
    System.out.println("c - reset\tx - exit");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    System.out.print("\n1st number:\t");
    Calculator calc = new Calculator();
    while (keepRunning){
      utilListener(sc.next(), calc);
    }
    if (keepRunning == false){
      System.out.println("\nMATH IS FUN! C U LTR ALIG8T0R! \r\n");
      System.exit(0);
    }
  }

  //LISTENER
  //----------------------------
  public static void utilListener(String lastInput, Calculator c) {
      //LISTEN FOR EXIT
    if (exit.equals(lastInput)){     
      keepRunning = false;
    } else if (clear.equals(lastInput)){
      //LISTENS FOR RESET
      c.calcState = 0;
      System.out.println("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("\nALL RESET!");
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
      System.out.print("\n1st number:\t");
    } else {
      //PASSES VALUE ALONG TO CALCULATOR OBJECT
      c.tabulate(lastInput); 
    }
  }
}

class Calculator {
  static double firstNum, secondNum;
  static byte opKey;
  static Boolean timeToCrunch;
  static byte calcState; 
  
  public Calculator(){ 	
      firstNum = 0; 
      secondNum = 0;
      opKey = 0; 
      timeToCrunch = false;
      calcState = 0;
      //CALCULATOR VARIABLES
      //calcState
      // 0 - firstNum needed
      // 1 - operator needed
      // 2 - secondNum needed
      // 3 - crunch and change clac state to 1 
      // clear() sets calcState to 0
	}

  //CALCULATOR METHODS
  //----------------- 

  //THE MATHNESS
  public static double theMathness(byte opKeyCode, double x, double y) {
    //SWITCH STATEMENT TO CARRY OUT OPERATIONS ON NUMBERS
    //mathematical operator (+, -, *, /)
    double theAnswer = 0; 
    switch(opKeyCode) {
      //case 1 +
      case 1: theAnswer = x + y;
              break;

      //case 2 -
      case 2: theAnswer = x - y;
              break;

      //case 3 *
      case 3: theAnswer = x * y;
              break;

      //case 4 /
      case 4: theAnswer = x / y;
              break;
      default: System.out.println("\nOh no something awful has happened. :( \n"); //ATTN: Add clear fucntion after this
               break;
    }
    return theAnswer;
  }

  //TABULATE
  //-----------------------------------------------
  //This function gathers info for calculation.
  //It keeps tracks of whether the user has input the needed data
  //On collecting x (if needed) , an operator and y, it calls theMathness and
  //gives us the answer.
  //If info is still needed, timeToCrunch will be false.
  //If info is complete, timeToCrunch will the true and triggered. 
  public static void tabulate(String lastInput){
    switch(calcState) {
        case 0: //awaiting first num
                if(isNumeric(lastInput)){//Evaluate the input
                  firstNum = Double.parseDouble(lastInput);
                  calcState = 1; //Increment calcState
                  System.out.print("\nOperator:\t");
                } else {
                  System.out.println("\nThat isn't a valid number, please re-enter.\n"); //else, print warning
                  System.out.print("\n1st number:\t");
                }
                break;//break

        case 1: //awaiting operator
                if (isOperator(lastInput)){
                  //Evaluate the input 
                  //input should be 1 char long
                  //input should be "+"" || "-"" || "/"" || "*""
                  calcState = 2; //Increment calcState
                  System.out.print("\n2nd number:\t");
                } else {
                  System.out.println("\nOperators must be +, -, * or /. please re-enter.\n"); //else, print warning
                  System.out.print("\nOperator:\t");
                }
                break;
                //break

        case 2: //awaiting second num
                if(isNumeric(lastInput)){//Evaluate the input — is number
                  secondNum = Double.parseDouble(lastInput);
                  if(verifyComputable(secondNum,opKey)) {
                    calcState = 3; //Increment calcState
                    /*PROCEED TO DO MATH*/
                    System.out.println("---------------------------");
                    System.out.println("\nANSWER: \t" + theMathness(opKey,firstNum,secondNum));
                    System.out.println("---------------------------\n");
                    //setCalcState back to 0 on successful evalution.

                    //STORE ANSWER AS firstNum AND CONTINUE TO ASK FOR OPERAND
                    firstNum = theMathness(opKey,firstNum,secondNum);
                    calcState = 1;
                    System.out.println("\n1st number:\t" + firstNum);
                    System.out.print("\nOperator:\t");
                    break;
                  } else {
                    System.out.println("\nWill return NaN solution, please re-enter.\n"); //else, print warning
                    System.out.print("\n2nd number:\t");
                  }
                } else {
                  System.out.println("\nThat isn't a valid number, please re-enter.\n"); //else, print warning
                  System.out.print("\n2nd number:\t");
                }
                break;//break

        default: 
              System.out.println("\nSomething has gone terribly wrong. :(\n");
              break;
    }

  }

  //
  //VERIFICATION METHODS
  //----------------------------------
  public static Boolean verifyComputable(double someNum, byte opKeyCode){
    //if op is divide, don't accept zero
    if (opKeyCode == 4 && someNum == 0.){
      return false;
    }
    return true;
  }


  public static boolean isNumeric(String str){  
    try  
    {  
      double d = Double.parseDouble(str);  
    }  
    catch(NumberFormatException nfe)  
    {  
      return false;  
    }  
    return true;  
  }


  public static boolean isOperator(String str){  
    if (str.equals("+")){
      opKey = 1;
      return true;
    } else if (str.equals("-")){
      opKey = 2;
      return true;   
    } else if (str.equals("*")){
      opKey = 3;
      return true;   
    } else if (str.equals("/")){
      opKey = 4;
      return true;   
    } else {
      return false;
    }      
  }

} 

