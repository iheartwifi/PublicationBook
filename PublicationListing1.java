import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PublicationListing1 {

        public final static String PUB_DATA="PublicationData_Input.txt";
	public static Scanner kb = new Scanner(System.in);
	public static Publication [] pubArray = new Publication[numberOfRecord()];
	
	public enum PublicationTypes {PUBLICATIONCODE, PUBLICATIONNAME,PUBLICATIONYEAR,PUBLICATIONAUTHORNAME,PUBLICATIONCOST,
		PUBLICATIONNBPAGES}  
	
	
	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args) throws FileNotFoundException, IOException{
		
		if (numberOfRecord()>=2){
			try {
				correctListOfItems(new FileInputStream(PUB_DATA),new FileOutputStream(outputFileName()));
			}catch (FileNotFoundException e){
				System.out.println("Error, output file cannot be found, the program will exit");
				System.exit(0);
			}
		} else {
			System.out.println("Input file is empty or has 1 line, cannot open the files");
		}
		
		try {
			
			System.out.println("\nHere is the input file before modification:\n");
			printListItems(new FileReader(PUB_DATA));
			System.out.println("\nHere is the output file after modification:\n");
			printListItems(new FileReader(outputFileName()));
			
		}catch(FileNotFoundException fnfe){
			System.out.println("\nCannot find the input file\n");
			System.exit(0);
		}
	}
	
	/**
	 * Private that calculate the number of records of file
	 * 
	 * @return lineNumber
	 */
	private static int numberOfRecord(){
		int lineNumber=0;
		try {
			File file = new File(PUB_DATA);
			if(file.exists()){
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
					while (lnr.readLine()!=null){
						lineNumber++;
					}
					lnr.close();
			}
		}catch (IOException e){
		}
		return lineNumber;
	}
	
	/**
	 * Method that able to correct the duplicate publication code.
	 * And store the corrected version into an array
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public static void correctListOfItems(FileInputStream input, FileOutputStream output) throws IOException {
		Scanner sc = new Scanner(input);
        PrintWriter pw = new PrintWriter(output);
        int i=0;
        String line;
        boolean again=false;
        
        while (sc.hasNextLine() ){ 
        		long pubCode=0;
        		String title=null;
        		int bkYear=0;
        		String authorName=null;
        		double cost=0;
        		int nbpages=0;
        		
        		try {
	    			pubCode=sc.nextLong();
	        		title=sc.next();
	        		bkYear=sc.nextInt();
	        		authorName=sc.next();
	        		cost=sc.nextDouble();
	        		nbpages=sc.nextInt();
	            		
            		     
        		} catch (InputMismatchException e){
        			sc.close();
	                pw.close(); 
        			System.out.println("Error, the program will restart");
        			System.exit(0);
        	}
        		
		        	pubArray[i]= new Publication(pubCode, title, bkYear,authorName, cost, nbpages);
		        	i++;
		        
        }
        
        for (i=0; i<pubArray.length; i++){
        	if(samePubCode(pubArray[i].getPublicationCode(),i)){
        		System.out.println("The book: " + pubArray[i].getPublicationName() + " has the same publication code, "
        				+ "please enter a new publication code");
        		long newCode = kb.nextLong();
        		
        		while(again) {
        			try {
	        				if(samePubCode(newCode,i)){
	        					throw new CopyException();
	        			}
	        				again=false;    
        			} catch(CopyException cce){
	        				System.out.println("The new publication entered is already"
	        						+ " existed, please enter a new code");
	        				newCode = kb.nextLong();
        			}
        		}
        			pubArray[i].setPublicationCode(newCode);
        	} 
        	pw.println(pubArray[i]); 	
        }
        sc.close();
        pw.close(); 
	}
	
	/**
	 * Method that takes an input file and print 
	 * the corrected version on the screen
	 * @param in
	 * @throws IOException
	 */
	
	public static void printListItems(FileReader in) throws IOException{
		BufferedReader input = new BufferedReader(in);
		
		String line = input.readLine();
		
		while (line != null) {
			System.out.println(line);
			line = input.readLine();
		}
		
		input.close();
	}
	
	/**
	 * Method that goes through the array to detect the duplicate code
	 * Return false if the duplicate code is not affected
	 * @param newCode
	 * @param var
	 * @return
	 */
	public static boolean samePubCode(long newCode, int var){
		for(int i=0; i<var; i++){
			if(pubArray[i].getPublicationCode()==newCode){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that takes the name entered by user for the output file.
	 * Reject if the name entered already existed. And show the info of the current file.
	 * Return the output file name
	 * @return
	 */
	public static String outputFileName(){
		
		System.out.println("Please enter a name for the output file");
		String s2 = kb.next();
		File out = new File(s2);
		File in = new File(PUB_DATA);
		
		while(out.exists()){	
			System.out.println("Sorry the file name already exists");
	    	System.out.println("The size of the file in bytes is: " + in.length());	
	    	System.out.println("Please enter a name for the output file");
			s2 = kb.next(); 	
			out = new File(s2);
		}
		return s2;
	}
	
	
}

    
