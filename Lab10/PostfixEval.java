import java.util.*;



public class PostfixEval{

	PostfixEval(){
	}

	public float evaluate(Deque<String> postfixStack){
		float answer = 0; 
		Deque<String> auxStack = new ArrayDeque<String>(); 

		for(Iterator itr = postfixStack.descendingIterator(); itr.hasNext();){
			String next = "" + itr.next();
			// System.out.println("INCOMING STACK SIZE: " + postfixStack.size());
			// System.out.println("ITERATOR NEXT: " + next);
			//FIFO
			//pop and transfer to auxStack until you hit an operator
			
			if(isOperator(next)){
				//pop 2 from aux stack and evaluate
				float secondOp = Float.parseFloat("" + auxStack.removeFirst()); 
				float firstOp = Float.parseFloat("" + auxStack.removeFirst());
				answer = doMath(firstOp, next, secondOp); 
				//put answers back on auxStack
				auxStack.addFirst(answer + "");
			} else {
				//push to auxStack
				auxStack.addFirst(next);
			}
		}
		return answer; 
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

	private float doMath(float firstOp, String operator, float secondOp){
		if(operator.equals("+")){
			return firstOp + secondOp; 
		}
		if(operator.equals("-")){
			return firstOp - secondOp;
		}
		if(operator.equals("*")){
			return firstOp * secondOp; 
		}
		if(operator.equals("/")){
			return firstOp / secondOp; 
		}
		if(operator.equals("^")){
			float answer = (float) Math.pow(firstOp, secondOp); //casting to float because pow() returns double. 
			return answer;
		}
		System.out.println("MATH ERROR: Cannot evaluate " + firstOp + operator + secondOp);
		return 0;
	}

}