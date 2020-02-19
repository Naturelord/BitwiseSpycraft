
import java.io.IOException;
import java.util.Scanner;

public class Main {

	// PPMIMage class for PPM image manipulation
	public PPMImage Manipulator;
	
	public static void main(String[] args)
	{
		String InputFileName;
		String OutPutFileName;
		String Message;
		
		boolean End = false;
		Scanner in = new Scanner(System.in);
		
		
		PrintBoilerPlate();
		Boolean end = false;
		while(!end) 
		{
			String choice ="";
			try
			{
				choice = in.next();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(choice.equals(""))
			{
				System.exit(-1);
			}
			else if(choice.equals("a")) {
				// Print out request for Input Image File Name - then store the string entered
				System.out.println("Please Specify the source PPM fileName:");
				try{InputFileName = in.next();}
				catch(Exception e){e.printStackTrace();}
				// Print out request for Desired Output Image File Name - Then store the string entered
				System.out.println("Please Specify the output PPM fileName:");
				try{OutPutFileName = in.next();}
				catch(Exception e){e.printStackTrace();}
				System.out.println("Please Enter the Phrase to Hide:");
				try{Message = in.next();}
				catch(Exception e){e.printStackTrace();}
				
				
				// End with new Boiler Plate
				PrintBoilerPlate();
			}
			else if(choice.equals("b")) {
				// Do Something
				
				// End with new Boiler Plate
				PrintBoilerPlate();
			}
			else if(choice.equals("c")) {
				end = true;
				System.out.println("\n\nThank you for using SPYCRAFT\n\n");
			}
			
			
		}
				
			
	}
	
	public static void PrintBoilerPlate()
	{
		String Boiler =
				"\nWhat would you like to do?\n"
				+ "		a.) Hide a Message\n"
				+ "		b.) Recover a Message\n"
				+ "		c.) Exit\n"
				+ "Enter your Selection:";
		
		System.out.print(Boiler);
	}

}

