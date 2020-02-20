import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PPMImage 
{
public String magicNumber;
public int width;
public int height;
public int maxColorValue;
public char[][][] raster;

public int startLocation;

public byte[] All;
public byte[] precursorInfo;
public byte[] info;


public PPMImage(File image)
{
	// Read the Image into the Raster for Future Manipulation
	readImage(image);
	// If so Load into memory by assigning to InputImage
}

public void writeImage(File fileName) throws IOException
{
	// Transform the raster into new raw data
	FileOutputStream fos = new FileOutputStream(fileName);
	for(int writeIndex = 0; writeIndex <startLocation; writeIndex++)
	{
		fos.write(All[writeIndex]);
	}
	fos.write(info);
}

private void readImage(File file)
{
try
{

byte[] chars = Files.readAllBytes(file.toPath());
All = chars;
String s ="";

for(int i=0; i<chars.length && i < 25;i++)
{
		s+= (char) chars[i];
}

String[] values = s.split("\n");
// Get the Magic Number
magicNumber =  values[0];
// Get the Height and Width
	// First Split Values[1]
String[] WH = values[1].split(" ");
	// Then assign based on split
width  = Integer.parseInt(WH[0]);
height = Integer.parseInt(WH[1]);
// Assign MaxValue and Initialize Raster
raster = new char[height][width][3];
maxColorValue = Integer.parseInt(values[2]);
// Find Where StartLocation is Located in chars[]
int StartLocation = s.indexOf("255")+4;
startLocation = StartLocation;
info = new byte[chars.length-StartLocation];
int index =0;
for(int set=StartLocation; set<chars.length; set++)
{
info[index] = chars[set];
index++;
}
//System.out.print("First Index:"+firstReturnIndex+" SpaceIndex:"+spaceIndex+" secondReturnIndex:"+secondReturnIndex);
for(int y=0;y<height;y++)
{
	for(int x=0;x<width;x++)
	{
		for(int i=0;i<3;i++)
		{
			raster[y][x][i] = (char) ( chars[y*width*3+x*3+i+StartLocation] & 0xFF);
		}
	}
}

}
catch(Exception e)
{
	System.out.println("Your Scanning in Failed");
	e.printStackTrace();
}
}

public void hideData(String message)
{
	message +='\0';
	// Concat the Ending Byte
	byte[] bits = new byte[message.length()];
	for(int i =0; i<message.length();i++)
	{
		bits[i] = (byte) message.charAt(i);
	} 
	
	for(int i =0; i<bits.length;i++)// Go through Bits using each byte 8 times
	{
		for(int n=1;n<=8;n++)// Check the Nth bit and compare it to the LSD at that location
		{
			// LSD location will be at info[i*8 + n-1]
			char positivemask = (char) (1 << n-1);
			
			// if the nth digit of bits is 1 and LSD at info[i*8 + n-1] == 0
			if(  (bits[i] & positivemask) > 0 && info[(i+1)*8 - n]%2 ==0)
			{
				// Then we need to add 1 to info by Or'ing a 0000 0001
				info[(i+1)*8 - n] = (byte) (info[(i+1)*8 - n] | 0x01);
				System.out.println("We added a 1 at Index: "+((i+1)*8 - n));
			}
			// // if the nth digit of bits is 0 and LSD at info[i*8 + n-1] == 1
			else if(  (bits[i] & positivemask) == 0 && info[(i+1)*8 - n]%2 ==1)
			{
				// Then we need to remove 1 to info by And'ing a 1111 1110
				info[(i+1)*8 - n] = (byte) (info[(i+1)*8 - n] & 0xFE);
				System.out.println("We added a 0 at Index: "+((i+1)*8 - n));
			}
			else
			{
				System.out.println("There is a "+(info[(i+1)*8 - n] & 0x01)+" at Index: "+((i+1)*8 - n));
			}
		}
	}	
}

public String recoverData()
{
	// Work On Info to rebuild the characters from the LSD's
	String ret = "";
	// Get LSD's in groups of 8
	/*
	char[] LSDs = new char[8];
	Boolean end = false;
		for(int i =0; i <info.length && !end;i++)
		{
			if(i != 0 && i%8 ==0)// Have reached the beginning of a new Character
			{
				char add = 0;
				for(int j=0; j<LSDs.length;j++)
				{
					add += (int) (LSDs[j] << (7-j));
					System.out.print((int) LSDs[j]);
				}
				System.out.println();
				ret.concat( String.valueOf(add));
				if(add == '\0')
				{
					end = true;
				}
				LSDs[0] = (char) (info[i] & 0x01);
			}
			else
			{
				LSDs[i%8] = (char) (info[i] & 0x01);
			}
		}*/
		
	Boolean end = false;
	int multiplier = 0;
	while(!end)
	{
	char add = 0x00;
	add+= (int)( (info[0+(multiplier*8)] & 0x01) << 7);
	add+= (int)( (info[1+(multiplier*8)] & 0x01) << 6);
	add+= (int)( (info[2+(multiplier*8)] & 0x01) << 5);
	add+= (int)( (info[3+(multiplier*8)] & 0x01) << 4);
	add+= (int)( (info[4+(multiplier*8)] & 0x01) << 3);
	add+= (int)( (info[5+(multiplier*8)] & 0x01) << 2);
	add+= (int)( (info[6+(multiplier*8)] & 0x01) << 1);
	add+= (int)( (info[7+(multiplier*8)] & 0x01));
	multiplier++;
	System.out.println(add);
	if(add == 0x00)
	{
		end=true;
	}
	else
	{
		ret += ""+add+"";
	}
	}
	
	return ret;
	
}



}
