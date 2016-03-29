import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class SortTester {

	/**@Author: Stan Bozhinov
	 * @param args
	 * @throws IOException 
	 * @credit Tom Capaul, doubly linked circular list, insertion sorts
	 */

	
	public static void main(String[] args) throws IOException {

		
		FileWriter output= getOutPut();

		int[] listSizes = {500, 1000, 5000, 10000};
		for(int i = 0; i < listSizes.length; i++){
			
			int currentSize = listSizes[i];
				String msg;
				output.write("\r\n");
			    output.write("List size "+currentSize+" =================================================== List size "+currentSize+"\r\n");
				output.write("\r\n");
				output.write("Sorted list >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\r\n");
				output.write("\r\n");
//sorted List	
			    //construct original linked list, and extra copies for each sort
				LinkedList originalSorted = genAscendList(currentSize); 
				LinkedList sortd1  = (LinkedList) originalSorted.clone(), sortd2 = (LinkedList) originalSorted.clone(), sortd3 = (LinkedList) originalSorted.clone();
				
				//Selection Sort
				msg = "Sorted list, Selection Sort \r\n";
				output.write(report(originalSorted.selectionSort(), msg ) );
				output.write("\r\n");
				//Smart Bubble Sort
				msg = "Sorted list , Smart Bubble Sort\r\n";
				output.write(report(sortd1.smartBubbleSort(), msg ) );
				output.write("\r\n");
				//Shift-Insertion Sort
				msg = "Sorted list , Shift Insertion Sort\r\n";
				output.write(report(sortd2.shiftingInsertionSort(), msg ) );
				output.write("\r\n");
				//Insertion Sort
				msg = "Sorted list , Insertion Sort\r\n";
				output.write(report(sortd3.insertionSort(), msg ) );
				

//descending List	
				output.write("\r\n");
				output.write("Descending List <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\r\n");
				output.write("\r\n");
				LinkedList originalDescending = genDescList(currentSize);
				LinkedList desc1 = (LinkedList) originalDescending.clone(), desc2= (LinkedList) originalDescending.clone(), desc3 = (LinkedList) originalDescending.clone();
				
				//Selection Sort
				msg = "Descending list , Selection Sort\r\n";
				output.write(report(originalDescending.selectionSort(),msg ) );	
				output.write("\r\n");
				//smart bubble sort
				msg = "Descending list , Smart Bubble Sort\r\n";
				output.write(report(desc1.smartBubbleSort(), msg) );
				output.write("\r\n");
				//Shift-Insertion Sort
				msg = "Descending list , Shift Insertion Sort\r\n";
				output.write(report(desc2.shiftingInsertionSort(), msg) );
				output.write("\r\n");
				//Insertion Sort
				msg = "Descending list , Insertion Sort\r\n";
				output.write(report(desc3.insertionSort(), msg) );


				
//Random list

				
				output.write("\r\n");
				output.write("Random List ?????????????????????????????????????????\r\n");
				output.write("\r\n");
				LinkedList originalRandom = genRandList(currentSize);
				LinkedList rand1= (LinkedList) originalRandom.clone(), rand2= (LinkedList) originalRandom.clone(), rand3 = (LinkedList) originalRandom.clone();
				//Selection Sort
				msg = "Random list , Selection Sort\r\n";				
				output.write(report(originalRandom.selectionSort(), msg ) );
				output.write("\r\n");
				//smart bubble sort
				msg = "Random list , Smart Bubble Sort\r\n";
				output.write(report(rand1.smartBubbleSort(), msg) );
				output.write("\r\n");
				//Shift-Insertion Sort
				msg = "Random list , Shift Insertion Sort\r\n";
				output.write(report(rand2.shiftingInsertionSort(), msg) );
				output.write("\r\n");
				//Insertion Sort
				msg = "Random list , Insertion Sort\r\n";
				output.write(report(rand3.insertionSort(), msg) );



			}//list size loop
			

			


		
		output.close();
	}//end main()
	
	public static LinkedList genDescList(int size) {
		LinkedList list = new LinkedList();	
		for(int i = size; i > 0; i--){
			list.add(i);
		}

		return list;
	}

	public static LinkedList genAscendList(int size){
		LinkedList list = new LinkedList();
		for(int i = 0; i < size; i++){
			list.add(i);
		}
		return list;
	}
	
	public static LinkedList genRandList(int size){
		LinkedList list = new LinkedList();
		Random rand = new Random();
		for(int i = 0; i < size; i++){
			
			list.add(rand.nextInt(size));
		}

		return list;
	}
	
	private static String report(Object[] objects, String msg)  {
		    String str="";
		    double elapsed = (((long)objects[6]-(long)objects[5]))/10E-9;

			int dataAsgn = (int) objects[0];
			int totalOps = (int)objects[0]+(int)objects[1]+(int)objects[2]+(int)objects[3]+(int)objects[4];
			str+=msg;
			str+="Time elapsed: "+elapsed+" seconds\r\n";
			str+="Total data assigns: "+dataAsgn+" Total Operations: "+totalOps+"\r\n";
			
			return str;
		
	}

	
	private static FileWriter getOutPut() throws IOException {
		FileWriter output= new FileWriter(new File("sort_results.txt"), true);
		return output;
	}

}//end SortTester
