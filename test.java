
public class Test {


    public static void main(String [] args) {
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
                if(x != height && y != width) {
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
}