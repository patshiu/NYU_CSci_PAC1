/* 
Pat Shiu
pat.shiu@nyu.edu
Lab 11 for PAC1 Fall 2015

----------------------------------
Lab 11 : Linked List Problems
----------------------------------

*/

public class FastMaxStack<T> implements MaxStack<T> {

	private final Maximizer<T> maximizer;
	private ListNode<T> top;

	//maxTracker is an auxillary stack that keeps track of the current max value
	//in the list everytime a push() or pop() is performed
	protected FastMaxStack<T> auxMaxStack;  

	
	public FastMaxStack(Maximizer<T> maximizer){
		this.maximizer = maximizer;  //Requires user to implement a max value comparison. 
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
    public void push(T value)
    {
        if (top == null) {
            top = new ListNode<T>(value, null);
            auxMaxStack.top = new ListNode<T>(value, null);
        } else {
        	//Create a new ListNode obj and point it to current top
            ListNode<T> addMe = new ListNode<T>(value, top);
            //Compare if value is larger than previous node val
            //And add data to aux stack 
            T currentMax = auxMaxStack.top.value; 
        	ListNode<T> addMeAux = new ListNode<T>(maximizer.getMax(currentMax, value), auxMaxStack.top);    	
        	auxMaxStack.top = addMeAux; 
            //Update top reference to latest node addition
            top = addMe; 
        }
    }

	@Override
	public T pop() {
		//Get the value and pop the stack
		T value = top.value;
		top = top.next;

		//pop the aux stack to match
		auxMaxStack.top = auxMaxStack.top.next; 

		return value;
	}

	@Override
	public T getMaxSoFar() {		
		T currentMax = auxMaxStack.top.value;
		return currentMax;
	}

}

