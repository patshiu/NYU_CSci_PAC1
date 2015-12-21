public class SlowMaxStack<T> implements MaxStack<T> {

	private final Maximizer<T> maximizer;
	private ListNode<T> top;

	
	public SlowMaxStack(Maximizer<T> maximizer) {
		this.maximizer = maximizer;
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
        } else {
        	//Create a new ListNode obj and point it to current top
            ListNode<T> addMe = new ListNode<T>(value, top);
            //Update top reference to latest node addition
            top = addMe; 
        }
    }

	@Override
	public T pop() {
		T value = top.value;
		top = top.next;
		return value;
	}

	@Override
	public T getMaxSoFar() {
		T currentMax = maximizer.getGlobalMin();
		
		for(ListNode<T> node = top; node != null; node = node.next) {
			currentMax = maximizer.getMax(currentMax, node.value);
		}
		
		return currentMax;
	}

}