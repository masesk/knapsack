/**
 * Mases Krikorian
 * 6/1/2018
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Knapsack {
	private int[] weights;
	private int[] profits;
	private double[] ratios;
	private int capacity;
	private ArrayList<Integer> maxProfitObjects = new ArrayList<Integer>();
	public Knapsack(){
		
	}
	public Knapsack(String fileName, int capacity){
		this.capacity = capacity; //set capacity
		ArrayList<String> data = new ArrayList<String>(); //store data in arraylist
		File file = new File(fileName); 
		try (BufferedReader br = new BufferedReader(new FileReader(file))) { //open file
		    String line;
		    while ((line = br.readLine()) != null) { //loop through file until null
		       data.add(line);						//save to data arraylist
		    }
		    splitData(data);						//send to splitData function to convert to doubles
		    
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("File cannot be open.");
		}
	}
	
	private  void splitData(ArrayList<String> data){
		this.profits = new int[data.size()]; //profits array is of size data
		this.weights = new int[data.size()]; //weights array is of size data
		String dataStrings[];					//data arraylist will be converted to data strings at each line
		try{
			for(int i = 0; i < data.size(); i++){
				dataStrings = data.get(i).split(" ");	//split using space
				weights[i] = Integer.parseInt(dataStrings[0]);	//save first number in weights
				profits[i] = Integer.parseInt(dataStrings[1]);	//save second number in profits
			}
		}catch(NumberFormatException e){
			System.out.println("File format is incorrect.");
		}
		catch(Exception e){
			System.out.println("Unknown error occured.");
		}

	}
	
	private void calculateRatios(){
		ratios = new double[weights.length]; //create ratio array of size weights
		for(int i = 0; i < weights.length; i++){ 
			ratios[i] = (double)profits[i]/weights[i]; //divide value of profits over weights
		}
	}
	
	public int zeroOneKnaosack(){
		maxProfitObjects.clear();
		int[][] k = new int[(weights.length)+1][capacity+1];
		//j is available weight
		//i is number of objects available
		for(int i = 0; i < k.length; i++){
			for(int j = 0; j < k[i].length; j++){
				if (i==0 || j==0)
					k[i][j] = 0;														//if i or j is 0, set 0, this makes index 0 for row and col 0
				else if (weights[i-1] <= j)												//if weight of previous is less than or equal to j value (avaialbe weight)
					k[i][j] = max(profits[i-1] + k[i-1][j-weights[i-1]], k[i-1][j]);	//find max between (profit at previous object + value in 2D array at previous object 
																						//and j-weight at object col) or (value in 2D array at previous object)
				else
					k[i][j] = k[i-1][j];												//otherwise set to previous object.
			}
		}
		retraceZeroOne(k);																//retrace to find optimal solution
		return k[k.length-1][k[0].length-1];											//return to main.
	}
	
	static int max(int a, int b) { 
		return (a > b)? a : b;
	}
	public void retraceZeroOne(int [][]k){		
		int currRowIndex = k.length-1; //start from bottom right corner of 2D array
		int currColIndex = k[0].length-1;
		int currWeight = k[currRowIndex][currColIndex];
		while(currWeight != 0){											//until we reach 0, then we stop
			if(k[currRowIndex-1][currColIndex] != currWeight){ 			//check previous row, same colum, if not equal
				maxProfitObjects.add(currRowIndex-1);			   			//add that row index to a list
				currColIndex = currColIndex - weights[currRowIndex-1];	//move column to weight of current object minus current column
				currWeight = k[currRowIndex-1][currColIndex];			//set currentWeight to the next weight
				
			}
			else{
				currWeight = k[currRowIndex][currColIndex];				//otherwise, move up the row to next object
			}
			currRowIndex = currRowIndex-1;								//regardless, we need to move up a row at each iteration until 0
		}
	}

	public double fractionalKnapsack(){
		maxProfitObjects.clear();
	    calculateRatios();						//calculate their ratios
	    quickSort(ratios,0,ratios.length-1);	//sort them to nondecreasing order
		double remaining = capacity;			//set remaining to capacity
		double profit = 0;						//set profit to 0
		int count = 0;							//set counter to 0
		for(int i = weights.length-1; i >= 0; i--){ //start from end of list (since nondecreasing order) so we get highest value
			if(weights[i] <= remaining){		//if the weight at that index is less than or equal to remiang 
				remaining = remaining - weights[i]; //pick it and subtract it from remaining
				profit = profit + profits[i];		//add to profits
				maxProfitObjects.add(i);
			}else{									//otherwise
				while(weights[i] - count > remaining && weights[i]-count > 0){	//if weights minus count is greather than remaining and greater than 0
					count++;													//add to count
				}
				remaining = remaining - weights[i]-count;						//when done subtract weights minus count from remaining
				profit = profit + ((((double)weights[i]-count)/weights[i]) * profits[i]); //set profit to ratio of weights minus count divided by weights times profits
				if((((double)weights[i]-count)/weights[i]) * profits[i] > 0){
					maxProfitObjects.add(i);
				}
				count = 0;															//reset count for next iteration
			}
		}
		return profit;																//return profit
	}
	
	public String getObjects(){
		for(int i = 0; i < weights.length; i++){
			System.out.println("Object #" + (i+1) + " with weight " + weights[i] + " and profit " + profits[i]); //printing profits and objects
		}
		StringBuilder text = new StringBuilder();
		for(int i = 0; i < maxProfitObjects.size(); i++){
			text.append(("\nObject #" + (maxProfitObjects.get(i)+1)));												//keeping their indecies saved 
		}
		return text.toString();
	}
	
	/*START QUICKSORT*/
	public void quickSort(double[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;
 
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		double pivot = arr[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
 
			while (arr[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				//swap values in weights, ratio, and profits
				int temp1 = weights[i];
				int temp2 = profits[i];
				double temp = arr[i];
				arr[i] = arr[j];
				weights[i] = weights[j];
				profits[i] = profits[j];
				
				arr[j] = temp;
				weights[j] = temp1;
				profits[j] = temp2;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);
 
		if (high > i)
			quickSort(arr, i, high);
	}
	/*END QUICKSORT*/
	
}
