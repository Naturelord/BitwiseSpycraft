import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class PPMImageSecond
{
private String magicNumber;
private int width;
private int height;
private int maxColorValue;
private char[][][] raster;
private File InputImage;

public PPMImageSecond(File image)
{
	InputImage = image;
	readImage();
}

public void writeImage(File fileName)
{
	
}

private void readImage()
{
	try(Scanner scan = new Scanner(InputImage);) {
		
		//read the header of file and populate instance variables
		magicNumber = scan.next();
		width = scan.nextInt();
		height = scan.nextInt();
		maxColorValue = scan.nextInt();
		raster = new char[height][width][3];

		//variables to keep track of array while populating it
		int x = 0;
		int y = 0;
		int colorValue = 0;
		byte byteNumber = 0;
		char charBinary;

		//populate the array. It converts the byte to the corresponding char before it stores it
		while(scan.hasNext()) {
			byteNumber = scan.nextByte();
			charBinary = (char) byteNumber;
			raster[x][y][colorValue] = charBinary;

			colorValue++;
			if(colorValue == 3) {
				colorValue = 0;
				y++;
			}
			if(x != height + 1 && y != width + 1) {
				if(y == width) {
					x++;
					y = 0;
				}
			}
		}

		scan.close();

	} catch (Exception e) {
		System.out.println("An error has occured!");
		e.printStackTrace();
	}

}

public void hideData(String message)
{
	
}

public String recoverData()
{
	return "";
}

}
