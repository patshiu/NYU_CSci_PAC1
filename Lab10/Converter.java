/* ------------------
TO DO MAYBE LOL: 
Exceptions to account for: 
- square and curly brackets
- Accept division by zero to return NaN (currently input treated as invalid)
- Report total number of errors in expression
- Handing decimal points
------------------*/

import java.util.*;

public class Converter{
	private String infixExp; 

	private Deque<String> infixStack; 
	private Deque<String> postfixStack; 

	Converter() {
		infixStack = new ArrayDeque<String>();
		postfixStack = new ArrayDeque<String>();
		infixExp = ""; 
	}

	Converter(String input) {
		this();
		this.setInfixExp(input);
	}

	public void setInfixExp(String input){
		if(infixValid(input)){
			infixStack = new ArrayDeque<String>();
			postfixStack = new ArrayDeque<String>();
			this.infixExp = input.replaceAll("\\s",""); //removes any blank spaces
			System.out.println("Saved infix expression: " + this.infixExp);
			pushToStack(this.infixExp);//push to stack 
			toPostFix();
			System.out.println("Converted postfix String: " + getPostfixStr());
			//reportPostfixStack();

		} else {
			System.out.println("\nBad Expression. Try again.");
		}
	}


	//Provided only for assignment requirements to return postfix as space-separated string
	public String getPostfixStr(){
		String pfStr = "";
		for(Iterator itr = postfixStack.descendingIterator(); itr.hasNext();){
			pfStr = pfStr + itr.next() + " ";
		}
		return pfStr; 
	}


	public Deque<String> getPostfixStack(){
		return postfixStack;
	}

	private void toPostFix(){
		//Temporary stack for holding operands while we convert
		Deque<String> tempStack = new ArrayDeque<String>();

		for(Iterator itr = infixStack.	descendingIterator(); itr.hasNext();){
			//System.out.println(itr.next());
			String next = "" + itr.next();
			if(next.equals("(")){ //Is opening bracket
				tempStack.addFirst(next);

			} else if(next.equals(")")){ //Is closing bracket

				//Pop the auxStack until '(' is found
				boolean paraPairFound = false; 
				while(paraPairFound == false){
					String pop = "" + tempStack.removeFirst();
					if(pop.equals("(")){
						paraPairFound = true;
					} else {
						postfixStack.addFirst(pop);
					}
				}
			} else if(isOperator(next)){ //Is an operator
				if(tempStack.size()==0 ){ //If tempStack is empty, add operator
					tempStack.addFirst(next);
				} else {
					//check operator precedence
					//IF tempStack.peekFirst()  => next, 
					//   push tempStack.removeFirst() to postfixStack, 
					//	 then push next to postfix stack
					//ELSE just push next to postfixStack
					String auxOp = tempStack.peekFirst();
					if (auxOp.equals("(")){
						tempStack.addFirst(next);
					} else {
						if(isLowerPrecedence(next, auxOp)){ //If current op is of lower precedence
							//pop the auxOp, push next to tempStack
							String higherOrEqualOp = tempStack.removeFirst();
							postfixStack.addFirst(higherOrEqualOp);
						}
						tempStack.addFirst(next);
					}
				}
			} else { //Is a number
				postfixStack.addFirst(next);
			}
		}

		if(tempStack.size() > 0 ){
			for(Iterator itr = tempStack.iterator(); itr.hasNext();){
				String s = "" + itr.next();
				postfixStack.addFirst(s);
			}
		}
	}

	private boolean isLowerPrecedence(String mainOp, String auxOp){
		//Assign precedence values by operator 
		int mainOpWeight = evalPrecedence(mainOp);
		int auxOpWeight = evalPrecedence(auxOp);
		if(mainOpWeight < auxOpWeight){
			return true;
		} else {
			return false;
		}
	}

	private int evalPrecedence(String input){
		char i = input.charAt(0);
		switch(i){
			case '+':
				return 0; 

			case '-':
				return 0; 

			case '/':
				return 1; 
 
			case '*':
				return 1; 
 
			case '^':
				return 2; 

			default:
				System.out.println("ERROR: Precedence evaluation error triggerd by \'" + input + "\'");
				return -1; //ERROR
		} 
	}

	private boolean isOperator(String input){
		if(	input.equals("+") ||
			input.equals("-") ||
			input.equals("/") ||
			input.equals("*") ||
			input.equals("^") ){
			return true; 
		} else {
			return false;
		}
	}

	private void reportInfixStack(){
		System.out.println("Size of infixStack is " + infixStack.size());
		System.out.println("Elements: ");
		for(Iterator itr = infixStack.descendingIterator(); itr.hasNext();){
			System.out.print(itr.next() + " ");
		}
		System.out.println("\r\n");
	}

	private void reportPostfixStack(){
		System.out.println("Size of postfixStack is " + infixStack.size());
		System.out.println("Elements: ");
		for(Iterator itr = postfixStack.iterator(); itr.hasNext();){
			System.out.print(itr.next() + " ");
		}
		System.out.println("\r\n");
	}

	private void pushToStack(String input){

		char[] infixChar = input.toCharArray(); 
		String lastNumRead = ""; 

		for(int i = 0; i < infixChar.length; i++){
			//If char is operand, push last read num and this operand to stack
			if( infixChar[i] == '(' || infixChar[i] == ')'){
				if(lastNumRead.length() > 0){ //push the last num if there's one in memory
					infixStack.addFirst(lastNumRead);
					lastNumRead = "";
				}

				String bracket = "" + infixChar[i];
				infixStack.addFirst(bracket);
			}
			else if(	infixChar[i] == '+' ||
				infixChar[i] == '-' || 
				infixChar[i] == '/' || 
				infixChar[i] == '*' || 
				infixChar[i] == '^' ){
				
				if(lastNumRead.length() > 0){ //push the last num if there's one in memory
					infixStack.addFirst(lastNumRead);
					lastNumRead = "";
				}

				String operator = "" + infixChar[i];
				infixStack.addFirst(operator);
				
			} else {
				lastNumRead = lastNumRead + infixChar[i];
			}
		}
		if(lastNumRead.length() > 0){ //push the last num if there's one in memory
			infixStack.addFirst(lastNumRead);
		}	
	}


	//checks infix Exp and checks that
	//1. It's not empty
	//2. It does not contain invalid characters
	//3. brackets match up 
	//4. There are no consequtive operators (eg. 5+/2  or 5*^2)
	private boolean infixValid(String input) {
		
		input = input.replaceAll("\\s",""); 
		boolean expValid = true; 

		if(input.length() < 1){
			System.out.println("Looks like that statement's too short! Try Again.");
			return false; 
		}

		char[] infixChar = input.toCharArray(); 
		int paraPairCheck = 0; //Parenthesis pairing checker
		boolean prevIsOperator = false;
		boolean seqOperators = false; //Sequential operator checker
		boolean unresolvedMinusAftOp = false; //Gets flagged when minus follows an op (eg. /-? *-?), unflagged when number follows
		boolean divByZero = false; //Gets flagged when division by zero
		boolean containsInvalidChar = false; 
		boolean hangingOperators = false; 
		
		for(int i = 0; i < infixChar.length; i++){

		
			char c = infixChar[i];

			if(i-1 >= 0){
				char prev = infixChar[i-1]; 
				prevIsOperator = (prev == '+' || prev == '-'|| prev == '*'|| prev == '/'|| prev == '^');
			}

			switch(c){

				case '(':
					paraPairCheck++; 
					break; 

				case ')':
					paraPairCheck--; 
					break; 

				case '0':
					if(i-1 >= 0){
						if(infixChar[i-1] == '/'){
							divByZero = true;
						}
					}
					break; 

				case '1':
					break; 

				case '2':
					break; 

				case '3':
					break; 

				case '4':
					break; 

				case '5':
					break; 

				case '6':
					break; 

				case '7':
					break; 

				case '8':
					break; 

				case '9':
					break; 

				case '-':

					//SPECIAL CASE: valid if a number follows (eg. 9--7 is valid 9---7 is not)
					if(i+1 < infixChar.length){ //Check if number follows
						char d = infixChar[i+1]; 
						Boolean nextIsDigit = (d >= '0' && d <= '9');
						if(nextIsDigit == false && prevIsOperator == true){ //If number does NOT follow a minus, exp is invalid
							unresolvedMinusAftOp = true;
						}
					} else { //if '-' is at end of expression, exp is invalid
						unresolvedMinusAftOp = true;
					}
					//Expressions may begin with '-'
					//Minus may appear without a number preceeding it
					break; 

				case '+':
					if(prevIsOperator == true){
						seqOperators = true; 
					}

					if(i - 1 < 0){ //If this is at start of expression, expression is invalid
						hangingOperators = true;
					} else {
						char d = infixChar[i-1];
						Boolean prevIsDigit = (d >= '0' && d <= '9');
						Boolean prevIsEndBracket = (d == ')');
						if(prevIsDigit == false && prevIsEndBracket == false){ //If number or end bracket does not preceed this operator, exp is invalid
							hangingOperators = true;
						}
					}
					break; 

				case '/':
					if(prevIsOperator == true){
						seqOperators = true; 
					}

					if(i - 1 < 0){ //If this is at start of expression, expression is invalid
						hangingOperators = true;
					} else {
						char d = infixChar[i-1];
						Boolean prevIsDigit = (d >= '0' && d <= '9');
						Boolean prevIsEndBracket = (d == ')');
						if(prevIsDigit == false && prevIsEndBracket == false){ //If number or end bracket does not preceed this operator, exp is invalid
							hangingOperators = true;
						}
					}
					break; 

				case '*':
					if(prevIsOperator == true){
						seqOperators = true; 
					}

					if(i - 1 < 0){ //If this is at start of expression, expression is invalid
						hangingOperators = true;
					} else {
						char d = infixChar[i-1];
						Boolean prevIsDigit = (d >= '0' && d <= '9');
						Boolean prevIsEndBracket = (d == ')');
						if(prevIsDigit == false && prevIsEndBracket == false){ //If number or end bracket does not preceed this operator, exp is invalid
							hangingOperators = true;
						}
					}
					break; 

				case '^':
					if(prevIsOperator == true){
						seqOperators = true; 
					}

					if(i - 1 < 0){ //If this is at start of expression, expression is invalid
						hangingOperators = true;
					} else {
						char d = infixChar[i-1];
						Boolean prevIsDigit = (d >= '0' && d <= '9');
						Boolean prevIsEndBracket = (d == ')');
						if(prevIsDigit == false && prevIsEndBracket == false){ //If number or end bracket does not preceed this operator, exp is invalid
							hangingOperators = true;
						}
					}
					break;

				default: //Invalid Char
					containsInvalidChar = true; 
					break;
			}

		}

		//Update expression validity based on results of checks
		if(hangingOperators == true || unresolvedMinusAftOp == true || seqOperators == true || containsInvalidChar == true || paraPairCheck != 0 || divByZero == true){
			expValid = false; 
			//System.out.println( "unresolvedMinusAftOp: " + unresolvedMinusAftOp  + "\tseqOperators: " + prevIsOperator + "\thangingOperators: " + hangingOperators);
		}

		//REPORT THE ERRORS
		if(expValid == false){
			System.out.println("\n---------------------------");
			System.out.println("\nERROR:");
			if(containsInvalidChar == true){
				System.out.println(">> Expression contains invalid character(s).");
			}
			if(hangingOperators == true || unresolvedMinusAftOp == true || seqOperators == true){
				System.out.println(">> Expression contains operator(s) without preceeding operands.");
			}
			if(paraPairCheck != 0){
				System.out.println(">> Unpaired parenthesis in expression.");
			}

			//REPORT THE WARNINGS
			if(divByZero == true){
				System.out.println("\nWARNING:\n>> Division by zero in expression results in NaN.");
			}
			System.out.println("\n---------------------------");
		}
		return expValid; 

	}

}