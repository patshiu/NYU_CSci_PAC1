//TO RUN 'java TestSmallint'

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 6 for PAC1 Fall 2015

----------------------------------
Lab 6: Small Int Class
----------------------------------
*/

import java.util.Scanner; 

public class TestSmallInt {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		boolean keepRunning = true; 
		String x = "x"; //exit
		System.out.println("WELCOME");
		System.out.println("Enter \"x\" to exit.");
		System.out.println("---------------------------------\n");

		while(keepRunning == true){
			System.out.print("Enter a number between 0 and " + SmallInt.MAXVALUE + ": ");
			if (sc.hasNext()){
				if (sc.hasNextInt()){
					SmallInt newInt = new SmallInt(sc.nextInt()); //For scanner you will need to Integer.parseInt()
					String binary = newInt.getBin();
					String decimal = newInt.getDec();
					String hexadecimal = newInt.getHex();
					System.out.println("---------------------------------");
					System.out.println("Decimal: \t" + decimal);
					System.out.println("Binary: \t" + binary);
					System.out.println("Hexadecimal: \t" + hexadecimal);
					System.out.println("---------------------------------");
				} else if (x.equals(sc.next())){
					keepRunning = false; 
					System.out.println("Bye!");
				} else {
					//skip
					System.out.println("Not a number... try again!");
				}
			} 

		}
	}
}