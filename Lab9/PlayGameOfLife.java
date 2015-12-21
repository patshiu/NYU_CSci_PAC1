//TO RUN ‘java PlayGameOfLife '

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 9 for PAC1 Fall 2015

----------------------------------
Lab 9: Game Of Life
----------------------------------

*/


import java.util.Scanner;
import java.io.*;


public class PlayGameOfLife {
	public static void main(String args[]){
		
		boolean keepRunning = true; 

		//DECLARE GRID VARS
		final int M = 25; //ROWS (y)
		final int N = 75; //COLS (x)

		GameOfLife gol; 

		Scanner sc = new Scanner(System.in);
		boolean validFileOpened = false;
		String filePath = "";  
		

		while(keepRunning == true){	
			//PROMPT USER TO OPEN A FILE
			//-----------------------------
			while(validFileOpened == false){
				System.out.println("\n\nWhich file do you want to open?");
				filePath = sc.next();
				validFileOpened = tryOpenFile(filePath); 
			}
			//If a valid file has been loaded:
			if(validFileOpened == true){

				//CREATE GOL OBJ WITH VALID FILEPATH
				//----------------------------- 
				gol = new GameOfLife(M, N, filePath);


				//PROMPT USER FOR INSTRUCTIONS
				//----------------------------- 
				while(validFileOpened == true){
					System.out.println("\nEnter any to load next generation.");
					System.out.println("\nEnter \'X\' to quit this program.");
					System.out.println("\nEnter \'N\' to load a new data file.");
					if(sc.hasNext()){
						String next = sc.next();
	
						if(next.equals("X") || next.equals("x")){
							keepRunning = false; 
							validFileOpened = false;
							System.out.println("Bye!");
						} else if (next.equals("N") || next.equals("n")) {
							validFileOpened = false; 
						} else {
							gol.mkNextGen();
							if(gol.worldIsDead){
								keepRunning = false; 
								validFileOpened = false;
							}
						}
					}
				}
			}
		}



		
	}

	public static boolean tryOpenFile(String input){
			String filename = input;
			File file = new File(filename);
			Scanner fileReader = null;

			try 
			{
			    fileReader = new Scanner (file);		
			} 
			catch (Exception e) 
			{
			    System.out.println("file " + file + " does not exist");
			    return false;
			}
			//for (int i = 1; i <= M; i++)
			// while(fileReader.hasNextLine()){
			// 	String line = fileReader.nextLine();
			// 	System.out.println(line);
			// }
			return true; 
	}
}