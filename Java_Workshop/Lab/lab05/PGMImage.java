import java.io.*;
import java.util.Scanner;

/**
 *  The class creates an image in form of a greyscale image. An image
 *  is represeented by its width, its height, the maximal value of the
 *  greyscale values, the type of the file (2), and an array of the
 *  pixels, consisting of values between 0 and 255 included. The class
 *  contains methods to read in an image from a file, write it to a
 *  file, and crop the left upper corner of an image, invert it,
 *  darken it, and shrink it by a given factor (along the width and
 *  the height).
 *
 *  @version 2019-10-28
 *  @author Manfred Kerber and Alexandros Evangelidis
 */
public class PGMImage{
    /**
     *  The field variables represent the width and the height of the
     *  image (measured in the number of pixels), the maximal
     *  greyvalue (between 0 and 255, incl.), the type of the file (2
     *  for greyscale), a rectangular array of height x width many
     *  values of numbers between 0 and 255 (incl.).
     */
    private int width;
    private int height;
    private int maxShade;
    private String typeOfFile;
    private short[][] pixels;

    /**
     *  Standard constructor
     *  @param width The width of the image in pixels.
     *  @param height The height of the image in pixels.
     *  @param maxShade The maximal value of the greyvalues (between 0
     *  and 255, incl.)
     *  @param typeOfFile (given as 2 for greyvalue pictures)
     *  @param pixels An array of width times height many pixels
     *  values, each between 0 and 255 (incl.)
     */
    public PGMImage(int width, int height, int maxShade,
                    String typeOfFile, short[][] pixels) {
        
        this.width = width;
        this.height = height;
        this.maxShade = maxShade;
        this.typeOfFile = typeOfFile;
        this.pixels = pixels;
    }
    
    /**
     *  @param filename The name of a file that contains an image in
     *  pgm format of type P2 that is read.
     *  @return The image corresponding to the data in the file.
     */
    public static PGMImage readPGMImage(String filename) {
        // try since the file may not exist.
        try {
            // we read from the scanner s which is linked to the file filename.
            Scanner s = new Scanner(new File(filename));

            /* The field variables are assigned by reading in from a
               file. The file should start with something like
               P2
               150 176
               255

               where P2 is the file type, 150 the width of the image, 176
               the height, and 255 the maximal grey value. Then follow
               150*176 grey values between 0 and 255.
            */
    
            // We read the initial element that is in our case "P2"
            String typeOfFile = s.next();
            // Next we read the width, the height, and the maxShade.
            int width = s.nextInt();
            int height = s.nextInt();
            int maxShade = s.nextInt();
            //We initialize and read in the different pixels.
            short[][] pixels = new short[height][width];
            for (int i=0; i<height; i++){
                for (int j=0; j<width; j++) {
                    pixels[i][j] = s.nextShort();
                }
            }
            s.close();
            return new PGMImage(width, height, maxShade,
                                typeOfFile, pixels);
        }
        catch (IOException e){
            //If the file is not found, an error message is printed,
            //and an empty image is created.
            System.out.println("File not found.");

            String typeOfFile = "P2";
            int width = 0;
            int height = 0;
            int maxShade = 0;
            short[][] pixels = new short[width][height];
            return new PGMImage(width, height, maxShade,
                                typeOfFile, pixels);
        }
    }

    /**
     *  @return The width of the image.
     */
    public int getWidth(){
        return width;
    }

    /**
     *  @return The height of the image.
     */
    public int getHeight(){
        return height;
    }
    
    /**
     *  @return The maximal grey value of the image.
     */
    public int getMaxShade(){
        return maxShade;
    }

    /**
     *  @return The file type of the image.
     */
    public String getTypeOfFile(){
        return typeOfFile;
    }

    /**
     *  @return The matrix representing the pixels of the image.
     */
    public short[][] getPixels(){
        return pixels;
    }

    /**
     *  The this object, representing an image, is written to a file
     *  so that it is a valid pgm image.
     *  @param filename The file to which the image should be written.
     */
    public void writePGMImage(String filename){
	try {
	    BufferedWriter out = 
		new BufferedWriter(new FileWriter(filename));
	    // We write the file type to out.
	    out.write(getTypeOfFile() + "\n");

	    // We write the dimensions to out.
	    out.write((getWidth()) + " " + (getHeight()) +"\n");
	    
	    // We write maximal number.
	    out.write(getMaxShade() + "\n");
	    
	    byte counter = 0;
            //The lines are written one by one. 
	    for (int i=0; i<getHeight(); i++){
                //Each indiviual pixel in the lines is written.
		for (int j=0; j<getWidth(); j++){
		    out.write(getPixels()[i][j] + " ");
		    counter++;
                    //We put in some linebreaks so that the lines don't get too long.
		    if (counter == 15){		 
                        out.write("\n");
                        counter = 0;
                    }
		}
	    }
	    out.write("\n");
	    // We close the file.
	    out.close();
	}
	catch (IOException e){
            //Errors are caught.
            System.out.println("File not found.");
        }
    }
    
    /**
     *  The method crops the left upper half of an image.
     *  @return An image with the cropped left upper half.
     */
    public PGMImage crop(){
        int width = getWidth()/2;
        int height = getHeight()/2;
        int maxShade = getMaxShade();
        String typeOfFile = getTypeOfFile();
        short[][] pixels = new short[height][width];

        //The image array is written line by line.
        for (int i=0; i<height; i++){
            //Each individual pixel is determined.
            for (int j=0; j<width; j++){
                pixels[i][j] = (short) 0; // ************ ADD CODE HERE
            }
        }
        return new PGMImage(width, height, maxShade,
                            typeOfFile, pixels);
    }

    /**
     *  The method takes the image and produces the same image, but
     *  every pixel value is reduce so that the image looks much
     *  darker.
     *  @return The darkened image is returned.
     */
    public PGMImage darken(){
        return this;     // ****************************** ADD CODE HERE
    }

    /**
     *  The method inverts the grey scale in a picture so that dark
     *  goes to bright and vice versa.
     *  @return The inverted image is returned.
     */
    public PGMImage invert(){
        return this;     // ****************************** ADD CODE HERE
    }

    /**
     *  The method shrinks the grey scale in a picture by the given
     *  factor. That is, with the factor, factor times factor many
     *  pixels are averaged and replaced by a single pixel.
     *  @return The shrunk image is returned.
     */
    public PGMImage shrink(int factor){
        return this;     // ****************************** ADD CODE HERE
    }


    /*
     * main method with an initial test of the methods.
     */
    public static void main(String[] args) {
        PGMImage cs = readPGMImage("ComputerScience.pgm");
        cs.crop().writePGMImage("ComputerScienceCrop.pgm");
        cs.invert().writePGMImage("ComputerScienceInvert.pgm");
        cs.darken().writePGMImage("ComputerScienceDarker.pgm");
        cs.shrink(30).writePGMImage("ComputerScienceShrink.pgm");
    }
}
