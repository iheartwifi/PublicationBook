/**
 * COMP 249:U - assignment 2 - Part2 - PublicationListingProcess2
 * Wan Lan He - 29496419
 */

package assignment2;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PublicationListingProcess2 {
	
	public enum PublicationTypes {PUBLICATIONCODE, PUBLICATIONNAME,PUBLICATIONYEAR,PUBLICATIONAUTHORNAME,PUBLICATIONCOST,
		PUBLICATIONNBPAGES}  
	Publication pub = new Publication ();
	public final static String PUB_DATA_2= "PublicationData_OutPut.txt";
	public static Publication [] pubArray = new Publication[numberOfRecord2()];
	public static Scanner kb = new Scanner(System.in);
	
	
	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String []  args) throws IOException {
		int records;
		String response;
		try{
			System.out.println("How many records would you like to add to the file?(max 20 records are allowed)");
			records=kb.nextInt();
			Publication [] pubArray = new Publication [records];
			if (records<20) {		
				for (int i=0; i<pubArray.length;i++){
					pubArray[i]=new Publication(pubCode());	
					insertRowToFile(pubArray);
				}	
			} else {
				do {
					System.out.println("Please enter a smaller records number");
					records=kb.nextInt();
				}while (records>20);
			}
			
			System.out.println("Enter the publication code for binary search");
			long pubCode = kb.nextLong();
			int [] array = new int[records];		
				for (int i=0; i<records; i++){
					array[i]=(int)pubCode;	
				}
			binaryPublicationsSearch(array, 0, records-1, pubCode);
			
	
			System.out.println("\nEnter the publication code for sequential search");
			long pubCode2 = kb.nextLong();
			int [] array2 = new int[records];		
				for (int i=0; i<records; i++){
					array2[i]=(int)pubCode2;	
				}
			sequentialPublicationSearch(array2, 0, records-1, pubCode2);
			
			printFileItems();
			binaryWriteFile();	
		}catch(Exception e){
			System.out.println("Records must be a number, please try again");
		}
				
	}
	
	/**
	 * Private that calculate the number of records of file
	 * 
	 * @return lineNumber
	 */
	private static int numberOfRecord2(){
		int lineNumber=0;
		try {
			File file = new File(PUB_DATA_2);
			if(file.exists()){
				//BufferedReader lnr = new BufferedReader(new FileReader(file));
				Scanner lnr = new Scanner (new FileReader(file));
					while (lnr.hasNextLine()){
						lnr.nextLine();
						lineNumber++;
					}
					lnr.close();
			}
		}catch (IOException e){
		}
		return lineNumber;
	}
	
	/**
	 * Method that returns an array of records added to the PublicationData_Output file
	 * @param pubArray
	 */
	public static void insertRowToFile(Publication [] pubArray){
		PrintWriter pw;
		Publication pub;
		
		try {
			pw=new PrintWriter(new FileWriter(PUB_DATA_2));	
			for(int i=0; i<pubArray.length; i++){
				pub=pubArray[i];
				
				if(pub!=null){
					pw.println(pub.getPublicationCode());
				}
			}
			pw.close();
		}catch(IOException ioe){
			System.out.println("Sorry input/output stream");
		}
		
	}
	
	/**
	 * Method that shows the contents of the file after modifications
	 * @return
	 */
	public static Publication [] printFileItems(){
		BufferedReader in;
		String line;
		Publication [] pub = new Publication [numberOfRecord2()];
		double pubCode;
		
		try {
			in = new BufferedReader(new FileReader(PUB_DATA_2));
			line=in.readLine();
			while(line!=null){
				pubCode=Double.parseDouble(in.readLine().trim());
				line=in.readLine();		
			}
			in.close();
		}catch(FileNotFoundException fnfe){
			System.out.println("File cannot be found");
		}catch (IOException ioe){
			System.out.println("Input/Output stream error");
		}
		return pub;
	}
	
	/**
	 * Method that save the publication code enter by the user
	 * @return pubCode
	 */
	public static long pubCode(){
		System.out.println("Please enter publication code");
		long pubCode = kb.nextLong();
			try {
				if (pubCode>0000000000 && pubCode<999999999)	{
					return pubCode;
				}
			} catch (NumberFormatException e){
				System.out.println("Format it is no accept");
			}
		return pubCode;
	}
	
	/**
	 * Methods that prompt user for publication binary search, it has four
	 * parameters. And show the iteration of the binary search
	 * @param pubArray
	 * @param start
	 * @param end
	 * @param pubCode
	 */
	public static void binaryPublicationsSearch(int [] pubArray, int start, int end, long pubCode){
		int position;
		int iteration=1;
		
		try {
			position = (start+end)/2;
			
			while((pubArray[position]!=pubCode)&&(start<=end)){
				iteration++;
				if(pubArray[position]>pubCode){
					end=position-1;
				} else {
					start=position+1;
				}
				position=(start+end)/2;
			}
			if (start<=end){
				System.out.println("The binary search was after: " + iteration + " iteration");
				return;
			}
		}catch (Exception e){
				
			}
		
	}
	
	/**
	 * Methods that prompt user for publication sequential search, it has four
	 * parameters. And show the iteration of the sequential search
	 * @param pubArray
	 * @param start
	 * @param end
	 * @param pubCode
	 */
	public static void sequentialPublicationSearch(int [] pubArray, int start, int end, long pubCode){
		int iteration=0;	
		for (int i=0; i<pubArray.length; i++){
			if (pubArray[iteration]==(int)pubCode){
				System.out.println(pubCode + " is at the index of " + (iteration+1));	
				return;
			}	
		}
		System.out.println("\nPublication code cannot be found");
		return;
	}
	
	/**
	 * Methods that store all the objects into a binary file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void binaryWriteFile() throws FileNotFoundException, IOException{
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Publications.dat"));
		
		Publication [] pub = new Publication[numberOfRecord2()];
		
		if(pub!=null){
			output.writeObject(kb.nextDouble());
		}
		output.close();
	}
	
	
	
}
