package CollegeSystem;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;

/**
 * {@summary : Student class (extends Person, implements Policies) : read student info, read user input / read, update file}
 * 
 */
public class Student extends Person implements Policies{
	protected String typeOfStu;
	
	public static String name;
	public int number;
	protected String programName;
	protected int studentNumber;
	protected int numberOfCourses;
	protected double total;
	protected double gpa;
	
	Student(){} //initialize

	@Override
	void readInfo(Scanner sc) { //read Student info with scanner (user input)
		System.out.print("Enter program name: ");
		programName = sc.next();
		System.out.printf("Enter student number: ");
		studentNumber = sc.nextInt();

		super.PersonInfo(sc); //scan personal info from Person class
		readMarks(sc);
	}
	/**
	 * {@summary : readMarks(sc) : scan total courses and calculate Gpa}
	 * @param sc
	 */
	void readMarks(Scanner sc) {
		System.out.print("Enter number of courses: ");
		numberOfCourses = sc.nextInt();
		
		ArrayList<Double> marks = new ArrayList<Double>();
		for(int i=0;i<numberOfCourses;i++) {
			System.out.printf("Enter mark " + (i+1) + ": ");
			double m = sc.nextDouble();
			marks.add(m);
			calculateGpa(marks.get(i));
		}
	}
	
	@Override
	public void calculateGpa(Double...m) {
		for(Double i : m) {
			total += i;
		}
		gpa = total/(numberOfCourses*maxMarks)*maxGpa;
	}
	/**
	 * {@summary : openFile() : open students.txt file}
	 */
	public void openFile(Scanner sc, int num) {	
		try { //open file
			sc = new Scanner(Paths.get("src\\students.txt")); //get file
			do{//if in the same line
			while(sc.hasNext()) {//scanning one by one
				ArrayList<Student> students = new ArrayList<Student>();
				Student s = new Student();
				typeOfStu = sc.next() + " ";
				studentNumber = sc.nextInt();
				
				firstName = sc.next() + " ";
				lastName = sc.next() + " ";
				email = sc.next() + " ";			
				phoneNumber = sc.nextLong();
				programName = sc.next();
				gpa = sc.nextDouble();
				if(typeOfStu.equals("f")) {
					FullTimeStudent.tuition = sc.nextDouble();
				} else if(typeOfStu.equals("p")) {
					ParttimeStudent.totalCourseFee = sc.nextDouble();
					ParttimeStudent.credits = sc.nextDouble(); 
				}
				students.add(s); //error here how to add to arraylist???howhowhowhowhowhowhowhowhowhowhowhowhowhow
			}//while loop end
			sc.close();
			}while(sc.hasNextLine());//while ends
		}catch(FileNotFoundException fe) {
			System.err.println("File not found or file not accessible");
		}catch(IOException e) {
			System.err.println("Error opening file");
		}//try-catch end
	}//openFile ends
	@Override
	void printInfo() {
		System.out.printf("%8s|%8d|%10s %s|%15s|%14d| %4.2f|", programName, studentNumber, firstName, lastName, email, phoneNumber, gpa);
	}
}
