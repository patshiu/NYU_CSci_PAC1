#Grader's Comments

**Grade:  ?**  
Submitted Attachments  
File attachment  Pat_SHIU_Lab11.zip ( 8 KB; Dec 20, 2015 11:36 pm )

#####Additional instructor's comments about your submission
?~



----------------------------------------------------
#Lab 11 : Linked List Problems

This homework assignment consists of three problems to solve involving linked lists. For each problem, you'll also have to analyze the running time. Place your analysis in a file called runtimeAnalysis.txt and submit it with your Java code.

Download the supporting code for this assignment [here](http://cs.nyu.edu/courses/fall14/CSCI-GA.1133-001/HW/linked-lists.zip).

####Part 1: Equality of Linked Lists
There is a static function in LLHomeworkFunctions called equalLists. Currently, it compared the two lists by calling "list1 == list2". We know that's incorrect because it's only comparing the addresses of the lists, and not the contents. We want two lists with equal contents to be equal.

Implement to `equalLists()` function. Analyze the running time of `equalLists()`

__Examples__
 

2 -> 4 -> 5 -> NULL

2 -> 4 -> 5 -> 1 -> NULL

`false`

2 -> 4 -> 5 -> NULL

2 -> 5 -> 4 -> NULL

`false`

2 -> 4 -> 5 -> NULL

2 -> 4 -> 5 -> NULL

`true`

 

####Part 2: Checking if a list is recursive
Some lists refer back to themselves, making them tricky to process. For example, consider the following list, where the addresses are indexed by capital letters:

A value=4 next=B  

B value=2 next=C  

C value=5 next=A  

If you keep calling list.next, you will never reach a NULL pointer. Instead, looking at the values, you'll keep repeating the sequence 4,2,5,4,2,5,4,2,5...  

Your goal is to implement the function terminates(ListNode list), which returns true if you'll ever reach a null pointer by continually calling list.next. In order to get this to work, you'll need to actually use "==" to compare addresses. A list will not terminate if a node points back to an address that was previously found. Do not use a Hash Table or Tree for your implementation, since we haven't gone over them yet; using a less efficient implementation is okay. Analyze the running time of your algorithm.

Examples 

2 -> 4 -> 5 -> NULL  

`true`

2 -> 4 -> 5 -> (points back to previous node) 4 -> 5 -> 4 -> 5 -> 4 -> etc  

`false`

####Part 3: Implementing a Max-Stack
A MaxStack is like a stack, except in addition to push() and pop(), we also have a function called getMaxSoFar(), which returns the maximum value that's in the stack.

Provided is an implementation of MaxStack called SlowMaxStack, but it's inefficient in terms of time. In order to implement getMaxSoFar(), it reads all of the values of the stack (implemented as a linked list) and returns the maximum. Analyze the running time of SlowMaxStack.

Your goal is to create a new class called FastMaxStack where the running time of push, pop, and getMaxSoFar are all constant, or O(1). You'll do this by having a second stack in parallel with the first one that keeps track of the maximum value of stack at every level. Analyze your running time and describe why it's constant for all three methods.

Testing: ensure that FastMaxStack always returns the same answer as SlowMaxStack

