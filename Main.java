
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main 
{
	// Global Variables
	private static String InputFileName;
	private static String OutPutFileName;
	private static String Message;
	private static Scanner in;
	// PPMIMage class for PPM image manipulation
	public PPMImage Manipulator;
	
	// Main Method
	public static void main(String[] args)
	{
		// Initialize Scanner
		in = new Scanner(System.in);
		// Print BoilerPlate to show Available Options to User
		PrintBoilerPlate();
		// Loop Boiler Plate after User finishes a request UNLESS user wishes to end.
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
			else if(choice.equals("a"))
			{
				EncodeMessage();
			}
			else if(choice.equals("b"))
			{
				DecodeMessage();
			}
			else if(choice.equals("c")) {
				end = true;
				System.out.println("\n\nThank you for using SPYCRAFT\n\n");
			}
			else
			{
				PrintBoilerPlate();
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
	
	public static void EncodeMessage()
	{
		// Print out request for Input Image File Name - then store the string entered
		System.out.print("Please Specify the source PPM fileName:");
		try{InputFileName = in.next();}
		catch(Exception e){e.printStackTrace();}
		// Print out request for Desired Output Image File Name - Then store the string entered
		System.out.print("Please Specify the output PPM fileName:");
		try{OutPutFileName = in.next();}
		catch(Exception e){e.printStackTrace();}
		System.out.print("Please Enter the Phrase to Hide:");
		Message  = "";
		Message = in.nextLine();
		// Create a new PPM Image based on the file information at InputFileName
		PPMImage Image = new PPMImage(new File(InputFileName));
		// Hide the Data in the Raster
		Image.hideData(Message);
		// Output the new PPM Image based on the File Location OutPutFileName
		try {
			Image.writeImage(new File(OutPutFileName));
		} catch (IOException e) {
			System.out.println("Could Not Encode The Message into a new PPM");
			e.printStackTrace();
		}
		// End with new Boiler Plate
		PrintBoilerPlate();
	}
	public static void DecodeMessage()
	{
		// Request the File Path
		System.out.print("\nPlease specify the source PPM filename:");
		try{InputFileName = in.next();}
		catch(Exception e){e.printStackTrace();}
		PPMImage In = new PPMImage(new File(InputFileName));
		// Decode and Display the Message
		System.out.print("The Message Recovered is: "+In.recoverData());
		// End with new Boiler Plate
		PrintBoilerPlate();
	}
}

