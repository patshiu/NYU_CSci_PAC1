//TO RUN ‘java Calculator '

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 10 for PAC1 Fall 2015

----------------------------------
Lab 10: Infix to Postfix Calculator
----------------------------------
*/

import java.util.*;

public class Calculator {
	public static void main(String[] args) {
		Converter converter = new Converter(); 
		PostfixEval pfe = new PostfixEval();
		
		//GLOBAL UTILS
		boolean keepRunning = true;
		Scanner sc = new Scanner(System.in);

		displayCalcMsg();
		while(keepRunning == true){
			
			System.out.println("\n\nEnter infix exp: ");
			if(sc.hasNextLine()){
				String input = sc.nextLine();
				if(input.equals("x") || input.equals("X")){
					keepRunning = false; 
					break;
				} else {
					converter.setInfixExp(input);
					float answer = pfe.evaluate(converter.getPostfixStack());
					System.out.println("THE ANSWER IS: " + answer);
				}
			}
		}

		if(keepRunning == false){
			System.out.println("ok, bye.");
		}
	}

	public static void displayCalcMsg(){
		System.out.print("\u001b[2J" + "\u001b[H");
		System.out.flush();
		System.out.println("******************************");
		System.out.println("          CALCULATOR");
		System.out.println("******************************");
		System.out.println("\'x\' to exit \n");
	}

}