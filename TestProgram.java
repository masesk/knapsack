/**
 * Mases Krikorian
 * 6/1/2018
 */


import java.util.Scanner;

public class TestProgram {
	public static void main(String[] args) {
		String input;
		Scanner scan = new Scanner(System.in);
		int capacity = -1;
		boolean accepted = false;
		System.out.println("Enter knapsack capacity:");
		while(!accepted){
			try{
				input = scan.nextLine();
				capacity = Integer.parseInt(input);
				accepted = true;
			}catch(NumberFormatException e){
				System.out.println("Incorrect number or format. Try again:");
			}
		}
		Knapsack ns = new Knapsack("input.txt", capacity);
		System.out.println("0/1 Knapsack Maximum Profit: " + ns.zeroOneKnaosack());
		System.out.println("\nObjects used are: " + ns.getObjects());
		System.out.println();
		System.out.println("Fractional Knapsack Maximum Profit: " + ns.fractionalKnapsack());
		System.out.println("\nObjects used are: " + ns.getObjects());
		System.out.println();

		scan.close();
	}
}
