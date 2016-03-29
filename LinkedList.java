	/**@Author: Stan Bozhinov
	 * //insertion/selection sort code written by Tom Capaul in class
	 *  Circular doubly linked represenatation http://moodle.bracu.ac.bd/pluginfile.php/2775/mod_resource/content/1/DHDLC-list-notes.html
	 *  idea to multiply by 10x-6 for nanoseconds: http://penguin.ewu.edu/cscd300/Topic/LinkedList/index.html   
	 * 
	 * */

import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
public class LinkedList  {
	
				private class Node{
					Comparable data;
					Node next;
					Node prev;
					
					public Node(Comparable data, Node next, Node prev){
						this.data = data;
						this.next = next;
						this.prev = prev;
					}		
				}//end class Node
				
			private Node head;
			private int size;
			
			public LinkedList(){
				this.head = new Node(null, null, null);
				this.head.prev = this.head;
				this.head.next = this.head;
				
				this.size = 0;
			}
	
	
	public void addFirst(Comparable data){
				
		Node newNode = new Node(data, this.head.next, this.head.prev);
		this.head.next = newNode;
		this.head.prev = newNode;
		       
		this.size++;
		
	}
	
	
	public void add(Comparable data){
	    
			if(size == 0){
				addFirst(data);
			}
			else{
			Node newN = new Node(data, null, null);
	        newN.next = this.head;
	        newN.prev = this.head.prev;
	        this.head.prev.next = newN;
	        this.head.prev = newN;
	        this.size++;
			}
		
	}
	

	//precondition: list is sorted	
	public void addOrdered(Comparable dataToAdd){
		Node cur;
		for(cur = this.head.next; cur !=this.head && cur.data.compareTo(dataToAdd)<0; cur = cur.next){
			//empty loop body
		}
		Node newNode = new Node(dataToAdd, cur, cur.prev);
		cur.prev.next = newNode;
		cur.prev = newNode;
		this.size++;
		
	}
	
	public boolean isEmpty(){
		return this.head.next == this.head;
	}
	
//	public boolean equals(Object other){
//		if(this.getClass().getSimpleName().equals(other.getClass().getSimpleName()))
//			return this.compareTo(other)<0;
//		return false;
//				
//	}
	
	public Object[] selectionSort()
	   {

		//get timings by calling timed sort on the current list
		long startTime = (long) ((LinkedList) this.clone()).timedSelectionSort()[0];
	    long endTime = (long) ((LinkedList) this.clone()).timedSelectionSort()[1];
		
		int dataAssigns = 0;
		int dataCompares = 0;
		int loopAssigns = 0;
		int loopCompares = 0;
		int other = 0;
		
		   if(size>0){
			   
	      Node cur, start, smallest;
			Comparable temp;
			 other+=4;// declare 3 nodes +1 data type
				loopAssigns++;//start = this.head.next
			for(start = this.head.next; start != this.head.prev; start = start.next)
			{
				loopCompares++;//start != this.head.prev
				loopAssigns ++;//start = start.next
				
				smallest = start;
				dataAssigns++;//smallest = start;
				loopAssigns++;//cur = start.next;
				for(cur = start.next; cur != head; cur = cur.next)
				{
					loopCompares++;//cur != head;
					loopAssigns++;//cur = cur.next
					dataCompares++;//cur.data.compareTo(smallest.data)<0
				
					if(cur.data.compareTo(smallest.data)<0)
						smallest = cur;
						dataAssigns++;//smallest = cur;

				}
				
				temp = start.data;
				start.data = smallest.data;
				smallest.data = temp;	
				
				dataAssigns+=3;
				
			}// end for
		   }//end if(size>0)
		   

	
			   Object[] arr = {dataAssigns, dataCompares,loopAssigns, loopCompares, other, startTime, endTime};
		   
		   return arr;

	   }// end sort
	
	



	Object[] smartBubbleSort(){
		//get timings by calling timed sort on the current list
		long startTime = (long) ((LinkedList) this.clone()).timedSmartBubbleSort()[0];
	    long endTime = (long) ((LinkedList) this.clone()).timedSmartBubbleSort()[1];
		
		int dataAssigns = 0;
		int dataCompares = 0;
		int loopAssigns = 0;
		int loopCompares = 0;
		int other = 0;
		
		boolean swaps;
		Node left, right;
		other+=3; //declare nodes/boolean
		do{
			swaps = false; loopAssigns++;
			other++; //left = head.next;
			loopAssigns+=2;//left= head.next, right = left.next;
			for(left= head.next, right = left.next; right !=head; left = left.next, right = right.next){
				loopCompares++;//right !=head;
				loopAssigns+=2; //left = left.next, right = right.next
				if(left.data.compareTo(right.data)>0){
					 dataCompares++;// sortedWalker.compareTo(dataToInsert)
					Comparable temp = left.data;
					left.data = right.data;
					right.data = temp;
					swaps = true;
					dataAssigns+=3;
					other++;//swaps = true
				}
			}
			loopCompares++;//while(swaps);
		}		
		while(swaps);

	
			   Object[] arr = {dataAssigns, dataCompares,loopAssigns, loopCompares, other, startTime, endTime};
		   
		   return arr;
	}//end SmartBubbleSort
	
	Object[] shiftingInsertionSort(){
		//get timings by calling timed sort on the current list
		   long startTime = (long) ((LinkedList) this.clone()).timedShiftingInsertionSort()[0];
		   long endTime =(long) ((LinkedList) this.clone()).timedShiftingInsertionSort()[1];

		
		int dataAssigns = 0;
		int dataCompares = 0;
		int loopAssigns = 0;
		int loopCompares = 0;
		int other = 0;
		
		Node lastSorted;
		Node sortedWalker;

		Comparable dataToInsert;
		other += 3; //Declare data types
		loopAssigns++;//lastSorted = this.head.next;
		for(lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next){
				dataToInsert = lastSorted.next.data;
				loopCompares++;//lastSorted != this.head.prev;
				dataAssigns++; //dataToInsert = lastSorted.next.data;
				loopAssigns+=2;//lastSorted = lastSorted.next, sortedWalker = lastSorted; for loop below
				for(sortedWalker = lastSorted; sortedWalker != this.head
						&& sortedWalker.data.compareTo(dataToInsert) > 0; 
						sortedWalker = sortedWalker.prev){
					loopCompares+=2;// sortedWalker.compareTo(dataToInsert), sortedWalker != this.head
							sortedWalker.next.data = sortedWalker.data;
							dataAssigns++;//sortedWalker.next.data = sortedWalker.data;
					}
				sortedWalker.next.data = dataToInsert;
				dataAssigns++;//sortedWalker.next.data = dataToInsert;
			}//end outer for loop
			

		   Object[] arr = {dataAssigns, dataCompares,loopAssigns, loopCompares, other, startTime, endTime};
		   
		   return arr;
		}//end shift-insertion-sort


	  public Object[] insertionSort()
	  {
		  //get timings by calling timed sort on the current list
		   long startTime = (long) ((LinkedList) this.clone()).timedInsertionSort()[0];
		   long endTime = (long) ((LinkedList) this.clone()).timedInsertionSort()[1];


			int dataAssigns = 0;
			int dataCompares = 0;
			int loopAssigns = 0;
			int loopCompares = 0;
			int other = 0;

	    Node lastSorted,sortedWalker,nodeToInsert;
	    other += 3; //declare nodes
	    
	    loopAssigns++;//lastSorted=this.head.next;

	    for(lastSorted=this.head.next; lastSorted.next!=this.head; )
	    {
		  loopCompares++;// lastSorted.next != this.head
	      nodeToInsert = lastSorted.next;
	      other++; //nodeToInsert = lastSorted.next
	      loopAssigns ++;//sortedWalker = lastSorted;
	      for(sortedWalker = lastSorted; sortedWalker!= this.head && sortedWalker.data.compareTo(nodeToInsert.data)>0; 
	    		  sortedWalker=sortedWalker.prev){
	      dataCompares+=2;//sortedWalker!= this.head && sortedWalker.data.compareTo(nodeToInsert.data)
	      }
	      dataCompares++;// sortedWalker == lastSorted
	      if(sortedWalker == lastSorted)
	      {
	        lastSorted = lastSorted.next; 
	        loopAssigns++; // lastSorted = lastSorted.next
	      }
	      else
	      {// cut
	        nodeToInsert.next.prev = nodeToInsert.prev;
	        nodeToInsert.prev.next = nodeToInsert.next;
	        //paste
	        nodeToInsert.next = sortedWalker.next;
	        nodeToInsert.prev = sortedWalker;
	        nodeToInsert.next.prev = nodeToInsert;
	        nodeToInsert.prev.next = nodeToInsert;
	        dataAssigns += 6; // cut and paste
	      }

	    }//end outer for loop
	    
	    

		   Object[] arr = {dataAssigns, dataCompares,loopAssigns, loopCompares, other, startTime, endTime};
		   
		   return arr;
	    
	  }//end insertionSort
	  

	
	   public Object clone() {
			LinkedList list = new LinkedList();	
			Node cur = this.head.next;
			for(int i = 0; i < this.size; i++){
				
				list.add(cur.data);
				cur = cur.next;
			}

			return list;
	   }
	   
	public String toString()
	{
		String s = "";
		//iterate through linked list
		if(!isEmpty()){
			Node cur;
			for(cur = this.head.next; cur != this.head; cur = cur.next)
			{
				//append all values to the string
				s += cur.data + "  \n";
			}
			
		}

		return s;
	}
	public void toString2()
	{
		String s = "";

		//iterate through linked list
		if(!isEmpty()){
			for(Node cur = this.head.prev; cur != this.head; cur = cur.prev)
			{
				//append all values to the string
				s += cur.data + "  \n";
			}
		}
		System.out.println(""+s) ;
	
	}

//	@Override
//	public int compareTo(Object other) {
//		if(this.equals(other))
//	        return this.compareTo(other);
//	    return this.compareTo(other);
//
//	}
	
	
	public Object[] timedSelectionSort()
	   {
		long startTime = System.nanoTime();

		
		   if(size>0){

	      Node cur, start, smallest;
			Comparable temp;
	
			for(start = this.head.next; start != this.head.prev; start = start.next)
			{

				
				smallest = start;
		
				for(cur = start.next; cur != head; cur = cur.next)
				{
				
					
					if(cur.data.compareTo(smallest.data)<0)
						smallest = cur;
			
				}
				
				temp = start.data;
				start.data = smallest.data;
				smallest.data = temp;	
				
	
			}// end for
		   }
		   long endTime = System.nanoTime() ;

		   Object[] arr = {startTime, endTime};
		   
		   return arr;

	   }// end sort
	
	



	Object[] timedSmartBubbleSort(){
		long startTime = System.nanoTime();
		
		boolean swaps;
		Node left, right;

		do{
			swaps = false; 
			for(left= head.next, right = left.next; right !=head; left = left.next, right = right.next){

				if(left.data.compareTo(right.data)>0){
					Comparable temp = left.data;
					left.data = right.data;
					right.data = temp;
					swaps = true;

				}
			}

		}		
		while(swaps);

		   long endTime = System.nanoTime() ;

		   Object[] arr = {startTime, endTime};
		   
		   return arr;
	}//end SmartBubbleSort
	
	Object[] timedShiftingInsertionSort(){
		long startTime = System.nanoTime();
				
		Node lastSorted;
		Node sortedWalker;

		Comparable dataToInsert;

		for(lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next){
			dataToInsert = lastSorted.next.data;
				
			for(sortedWalker = lastSorted; sortedWalker != this.head
					&& sortedWalker.data.compareTo(dataToInsert) > 0; 
					sortedWalker = sortedWalker.prev){
				
						sortedWalker.next.data = sortedWalker.data;
				}
			sortedWalker.next.data = dataToInsert;
			}
		
		   long endTime = System.nanoTime() ;

		   Object[] arr = {startTime, endTime};
		   
		   return arr;
		}//end shift-insertion-sort


	  public Object[] timedInsertionSort()
	  {
		  	long startTime = System.nanoTime();

	    Node lastSorted,sortedWalker,nodeToInsert;

	    for(lastSorted=this.head.next; lastSorted.next!=this.head; )
	    {
	      nodeToInsert = lastSorted.next;

	      for(sortedWalker = lastSorted; sortedWalker!= this.head && sortedWalker.data.compareTo(nodeToInsert.data)>0; 
	    		  sortedWalker=sortedWalker.prev);

	      if(sortedWalker == lastSorted)
	      {
	        lastSorted = lastSorted.next; 

	      }
	      else
	      {// cut
	        nodeToInsert.next.prev = nodeToInsert.prev;
	        nodeToInsert.prev.next = nodeToInsert.next;
	        //paste
	        nodeToInsert.next = sortedWalker.next;
	        nodeToInsert.prev = sortedWalker;
	        nodeToInsert.next.prev = nodeToInsert;
	        nodeToInsert.prev.next = nodeToInsert;

	      }

	    }//end for loop
	    
		   long endTime = System.nanoTime() ;

		   Object[] arr = {startTime, endTime};
		   
		   return arr;
	    
	  }//end insertionSort	
	




	
}
