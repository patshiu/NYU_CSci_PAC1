import java.util.Scanner;
import java.io.*;

public class GameOfLife {
	private int[][] prevGen; 
	private int[][] currGen;
	private char[][] prevGenChar; 
	private char[][] currGenChar;
	private int rows;
	private int cols;
	private int currentGen; 
	public boolean worldIsDead; 
	

	GameOfLife(int rows, int cols, String filePath){ //GOOD CONSTRUCTOR
		this.rows = rows + 2; 
		this.cols = cols + 2;
		prevGen = new int[this.rows][this.cols];
		currGen = new int[this.rows][this.cols]; 
		prevGenChar = new char[this.rows][this.cols];
		currGenChar = new char[this.rows][this.cols]; 		
		loadData(filePath);	
		currentGen = 0; 
		worldIsDead = false; 
		copyGen();
		printCurrGen();
	}

	//CONSTRUCTORS TO HANDLE BAD INPUTS
	//--------------------------------------
	GameOfLife(){ //BAD INPUT, CONSTRUCTOR INITS TO DEFAULTS
		new GameOfLife(0,0, null);
		currentGen = -1; //GEN -1 BECAUSE NO DATA IS LOADED
		System.out.println("-----");
		System.out.println("ERROR:GameOfLife object requires initial state data file & world size.");
		System.out.println("-----");
	}

	GameOfLife(int rows, int cols){ //BAD INPUT, CONSTRUCTOR INITS TO DEFAULTS
		new GameOfLife(0,0, null);
		currentGen = -1; //GEN -1 BECAUSE NO DATA IS LOADED
		System.out.println("-----");
		System.out.println("ERROR:GameOfLife object requires initial state data file & world size.");
		System.out.println("-----");
	}

	GameOfLife(File file){
		new GameOfLife(0,0, null);
		currentGen = -1; //GEN -1 BECAUSE NO WORLD ARRAYS ARE OF SIZE 0,0
		System.out.println("-----");
		System.out.println("ERROR:GameOfLife object requires initial state data file & world size.");
		System.out.println("-----");
	}

	//GOL PUBLIC METHODS
	//--------------------------------------
	public void mkNextGen(){
		//Iterate through prevGen array, calculate currGen
		for (int row = 1; row < (this.rows - 1); row++){
			for (int col = 1; col < (this.cols - 1); col++){
				currGen[row][col] = getNewState(prevGen[row][col], row, col); //Calculate nextGen based on neighborhood totals
				//update the currGenChar array based on currGen int array
				currGenChar[row][col] = (currGen[row][col] == 1 ? 'X' : ' ');
			}
		}
		worldIsDead = worldIsDead(); //before copying new currGen into prevGen, check if the new world is dead. 
		copyGen();//Copy new currGen to prevGen for next round of computation (copies both int & char arrays)
		currentGen++; //increment current gen
		printCurrGen(); //print out the new generation & generation number
	}

	public boolean worldIsDead(){
		//CASE 1: World is in stasis
		int totalCells = (this.rows - 2) * (this.cols - 2);
		int totalMatchingCells = 0; 
		for(int row = 1; row < (this.rows -1); row++){
			for(int col = 1; col < (this.cols -1); col++){
				if(currGen[row][col] == prevGen[row][col]){
					totalMatchingCells++; 
				}
			}
		}

		//CASE 2: World is empty
		int totalLife = 0; 
		for (int row = 1; row < (this.rows - 1); row++){
			for (int col = 1; col < (this.cols - 1); col++){
				if(currGen[row][col] > 0){
					totalLife++; 
				}
			}
		}
		// System.out.println("totalLife counted: " + totalLife);
		// System.out.println("worldIsDead: " + worldIsDead);
		//Evaluate results 
		if (totalCells == totalMatchingCells){
			//System.out.println("totalCells: " + totalCells + "\ttotalMatchingCells: " + totalMatchingCells );
			return true; 
		} else if (totalLife == 0) {
			return true;
		} else {
			return false;
		}
	}



	//SUPPORTING PRIVATE UTILITY METHODS
	//----------------------------------------------------
	private void copyGen(){
		for (int row = 0; row < this.rows; row++){
			for (int col = 0; col < this.cols; col++){
				prevGen[row][col] = currGen[row][col];
				prevGenChar[row][col] = currGenChar[row][col];
			}
		}
	}

	private int totalNeighbors(int row, int col){
		//REMEMBER NOT TO COUNT THE CELL ITSELF!!!
		int totalNeighbors = 	prevGen[row-1][col-1] + prevGen[row-1][col] + prevGen[row-1][col+1] + 
								prevGen[row][col-1] + prevGen[row][col+1] + 
								prevGen[row+1][col-1] + prevGen[row+1][col] + prevGen[row+1][col+1];

		return totalNeighbors; 
	}

	private int getNewState(int currState, int x, int y){
		int totalNeighbors = totalNeighbors(x,y); 
		if(currState == 0){ //If dead			
			if(totalNeighbors == 3){
				return 1; 
			} else {
				return 0; 
			}
			
		} else { //If alive
			//System.out.println("Live cell at (" + x + "," + y +"). Total neighbors = " + totalNeighbors);
			if(totalNeighbors == 2 || totalNeighbors == 3){
				return 1; 
			} else {
				return 0;
			} 
		}
	}

	private void printCurrGen(){
		//Clear screen
		String ANSI_CLS = "\u001b[2J";
  		String ANSI_HOME = "\u001b[H";
	   	System.out.print(ANSI_CLS + ANSI_HOME);
    	System.out.flush();

		//Print dashed line the size of grid
		for (int col = 0; col < (this.cols - 1); col++){
			System.out.print("-");
		}
		System.out.print("\r\n");
		System.out.println("CURRENT GENERATION: " + currentGen);
		//Print dashed line the size of grid
		for (int col = 0; col < (this.cols - 1); col++){
			System.out.print("-");
		}
		System.out.print("\r\n");


		for (int row = 1; row < (this.rows - 1); row++){
			for (int col = 1; col < (this.cols - 1); col++){
				if(col == this.cols -2){
					// char printMe = (currGen[row][col] == 1 ? 'X' : '_');
					// System.out.println(printMe);
					System.out.println(currGenChar[row][col]);
				} else {
					// char printMe = (currGen[row][col] == 1 ? 'X' : '_');
					// System.out.print(printMe);
					System.out.print(currGenChar[row][col]);
				}
			}
		}

		if (this.worldIsDead == true){
			System.out.println("");
			System.out.println("**********************************************");
			System.out.println("APOCALYPSE: World is empty or in stasis. Bye!");
			System.out.println("**********************************************");
		}
	}

	private void loadData(String filePath){
		File file = new File(filePath);
		Scanner fileReader = null;
		try 
		{
		    fileReader = new Scanner (file);		
		} 
		catch (Exception e) 
		{
		    System.out.println("file " + file + " does not exist");
		    return; 
		}
		//LOAD DATA
		for (int row = 0; row < this.rows; row++){ //Iterate thru rows
		//for (int row = 0; row < this.rows; row++){ //Iterate thru rows
			if(row == 0 || row == this.rows-1){ //If this is top / bottom row
				for(int col = 0; col < this.cols; col++){
					this.currGen[row][col] = 0; 
					this.currGenChar[row][col] = '-';
				}
			} else { 
				String line = fileReader.nextLine(); //Read next line in file
				for (int col = 0; col < this.cols; col++){ //Iterate thru cols
					if(col == 0 || col == this.cols-1){ //If this is left / right edge
						this.currGen[row][col] = 0; 
						this.currGenChar[row][col] = '-';
					} else {
						char currentChar = line.charAt(col-1); 
						if(currentChar == '.'){
							this.currGen[row][col] = 0;
							this.currGenChar[row][col] = ' '; 
						} else {
							this.currGen[row][col] = 1;
							this.currGenChar[row][col] = 'X'; 
						}
					}
				}
			}
			//System.out.println("Line " + row + " of data file loaded into currGen array.");
		}
		//System.out.println("CURRENT GEN LOADED FROM FILE.");
	}



}