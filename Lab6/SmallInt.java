//TO RUN 'java TestSmallint'

/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 6 for PAC1 Fall 2015

----------------------------------
Lab 6: Small Int Class
----------------------------------
*/

public class SmallInt {

	public static final int MAXVALUE = 1000; 
	private int value; 

	SmallInt(int input) {
		this.setDec(input);
	}

	SmallInt() {
		this.value = 0; 
	}

	public String getDec(){
		String dec = "" + this.value;
		return dec; 
	}

	public void setDec(int input){
		if(input <= MAXVALUE){
			this.value = input;
		} else if (input > MAXVALUE){ //Value too large
			this.value = 0;
			System.out.println("\n!!!ERROR: That value is too large. Value set to 0.\n");
		} else if (input < 0){
			this.value = 0;
			System.out.println("\n!!!ERROR: Negative values are not accepted. Value set to 0.\n");
		}
	}


	/*BINARY CONVERTER 

	The Binary representation of a number is the number expressed 
	as the sum of powers of 2. 

	Powers of 2
	2^0 = 1
	2^1 = 2
	2^2 = 4
	2^3 = 8
	2^4 = 16
	2^5 = 32
	2^6 = 64
	...

	So a binary number "1000111" means

	1	0	0	0	1	1	1	
	2^6	2^5	2^4	2^3	2^2	2^1	2^0
	64+	0+	0+	0+	4+	2+	1 
	___________________________		
	DECIMAL REP = 71
	-----------------------------------*/
	public String getBin(){
		String bin = ""; 
		int decNum = this.value;
		do {
			int rem = decNum % 2; 
			decNum = decNum / 2; 
			bin = rem + bin; 
		} while (decNum > 0);
		return bin; 
	}




	/*HEXADECIMAL CONVERTER 

	The Binary representation of a number is the number expressed 
	as the sum of powers of 16. 

	Powers of 16
	16^0 = 1
	16^1 = 16
	16^2 = 256	
	16^3 = 4,096
	16^4 = 16
	16^5 = 65,536
	...

	And the representation of the occurances of the powers of 
	16 are symbolically represented as follows: 

	Number	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15
	Symbol	1	2	3	4	5	6	7	8	9	a	b	c	d	e	f

	So a binary number "3e7" means

	3		e		7	
	16^0	16^1	16^2	
	3+		224		
	___________________________		
	DECIMAL REP = 999 
	-----------------------------------*/
	public String getHex(){
		String hex = ""; 
		int decNum = this.value; 
		do {
			int rem = decNum % 16; 
			String remHex = ""; 
			//Convert rem form int to hex
			if (rem > 9){
				switch(rem){
					case 10:
						remHex = "a";
						break; 
					case 11: 
						remHex = "b";
						break; 
					case 12: 
						remHex = "c";
						break; 
					case 13: 
						remHex = "d";
						break; 
					case 14: 
						remHex = "e";
						break; 
					case 15: 
						remHex = "f";
						break; 

				}
			} else {
				remHex = "" + rem; 
			}
			decNum = decNum / 16; 
			hex = remHex + hex; 
		} while (decNum > 0);
		return hex; 
	}
}