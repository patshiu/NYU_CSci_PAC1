public class LLHomeworkFunctions {

	public static void main(String [] args) {
        // TODO: Part of this assignment is to implement this correctly.
	}
	


	/**
	 * @param <T>
	 * @param list1
	 * @param list2
	 * @return true if the lists are equal.  Assume both lists terminate.
	 */

    public static <T> boolean equalLists(ListNode<T> list1, ListNode<T> list2) {
    	//BIG OH — O(n)

		//First check if lists are empty
		if(list1 == null || list2 == null){
			//returns true if both lists are empty
			//false if only one is empty
			return (list1 == null && list2 == null); 
		}

		//If neither lists are empty, let's iterate through and check
		//This implementation assumes both lists have been checked to terminate.
		int location = 0; 
		boolean tailReached = false; 
		boolean listsMatch = true; //Assume lists match until mismatch found

		while (tailReached == false){
			listsMatch = ( list1.value.equals(list2.value) ? true : false );

			if(list1.next == null || list2.next == null){
				tailReached = true; 
			}
		}
		return listsMatch; 	
	}
	
	/**
	 * @param <T>
	 * @param list
	 * @return true if the list eventually terminates, and false if the list points back at one of it's
	 *  previous nodes.
	 */
	public static <T> boolean terminates(ListNode<T> list) {
        /*BIG OH — O(n)
        ***
        The below solution uses Floyd's Cycle Detecting Algorithm
        ------ 
       	* Iterate through the list at 2 different speeds, 
        * If a loop exists, the two iterators will eventually point to the 
        * same node. 
        */

        ListNode<T> tortise, hare; 
        tortise = hare = list; //Both iterators start at same point

       	while(hare != null && tortise != null){ //If either is null, list end has been reached
			tortise = tortise.next;
			hare = hare.next.next; 

			if(tortise == hare){
				return false; 
			}        		
       	}
        return true; // fast reached null, so the list terminates
	}
}
