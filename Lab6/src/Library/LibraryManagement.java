package Library;

import java.util.Scanner;

public class LibraryManagement implements Policies{
	public static void main(String[] args) {
		String name; //library name
	
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("What is the library's name? : ");
		name = sc.next();
	}
	
	
	@Override
	public void whenIsDueDate() { //calculate when is due date
		
		
	}

	@Override
	public void howMuchFine() { //calculate fine
		
		
	}
}