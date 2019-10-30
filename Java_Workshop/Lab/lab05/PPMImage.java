import java.io.*;
import java.util.Scanner;

/**
 *  The class creates an image in form of a colour image. An image is
 *  represeented by its width, its height, the maximal value of the
 *  pixel values, the type of the file (3), and a three dimensional
 *  array of the pixels. Note that each pixel is represented by three
 *  values between 0 and 255 included for the three base colours red,
 *  green, and blue. The class contains methods to read in an image
 *  from a file, write it to a file, and create a greyscale image from
 *  a colour image.
 *
 *  @version 2019-10-28
 *  @author Manfred Kerber
 */
public class PPMImage{
    /**
     *  The field variables represent the width and the height of the
     *  image (measured in the number of pixels), the maximal
     *  greyvalue (between 0 and 255, incl.), the type of the file (3
     *  for colour), a three dimensional array of height x width x 3
     *  many values of numbers between 0 and 255 (incl.), 3 values for
     *  each pixel for the the red, green, and blue value, (0,0,0)
     *  representing black, (255,0,0) bright red, (0,255,0) bright
     *  green, (0,0,255) bright blue, and (255,255,255) white.
     */
    private int width;
    private int height;
    private int maxShade;
    private String typeOfFile;
    private short[][][] pixels;

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
    public PPMImage(int width, int height, int maxShade,
                    String typeOfFile, short[][][] pixels) {
        
        this.width = width;
        this.height = height;
        this.maxShade = maxShade;
        this.typeOfFile = typeOfFile;
        this.pixels = pixels;
    }

    /**
     *  @param filename The name of a file that contains an image in
     *  pgm format of type P3 that is read.
     *  @return The image corresponding to the data in the file.
     */
    public static PPMImage readPPMImage(String filename) {
        // try since the file may not exist.
        try {
            // we read from the scanner s which is linked to the file filename.
            Scanner s = new Scanner(new File(filename));

            /* The field variables are assigned by reading in from a
               file. The file should start with something like
               P3
               150 176
               255

               where P3 is the file type, 150 the width of the image, 176
               the height, and 255 the maximal value of all entries. Then follow
               3*150*176 values between 0 and 255.
            */
    
            /* We read the initial element that is in our case it
             * should be "P3" As a possible improvement of the method,
             * we could terminate the method abruptly if it does not
             * read "P3".
             */
            String typeOfFile = s.next();
            // Next we read the width, the height, and the maxShade.
            int width = s.nextInt();
            int height = s.nextInt();
            int maxShade = s.nextInt();
            /* We initialize and read-in the different pixels into the
             * pixels array 
             */
            short[][][] pixels = new short[height][width][3];
            // We read every row.
            for (int row=0; row<height; row++){
                // In each row we read the pixels for each column.
                for (int column=0; column<width; column++) {
                    /* For each pixel we read the three values for the
                     * three colours.
                     */
                    for (int colour=0; colour<3; colour++){
                        pixels[row][column][colour] = s.nextShort();
                    }
                }
            }
            s.close();
            return new PPMImage(width, height, maxShade,
                                typeOfFile, pixels);
        }
        catch (IOException e){
            //If the file is not found, an error message is printed,
            //and an empty image is created.
            System.out.println("File not found.");

            int width = 0;
            int height = 0;
            int maxShade = 0;
            String typeOfFile = "P3";
            short[][][] pixels = new short[width][height][3];

            return new PPMImage(width, height, maxShade,
                                typeOfFile, pixels);
        }
    }

    /**
     *  Getter for the width.
     *  @return The width of the image.
     */
    public int getWidth(){
        return width;
    }

    /**
     *  Getter for the height.
     *  @return The height of the image.
     */
    public int getHeight(){
        return height;
    }
    
    /**
     *  Getter for the maxShade.
     *  @return The maximal pixel value of the image.
     */
    public int getMaxShade(){
        return maxShade;
    }

    /**
     *  Getter for the file type.
     *  @return The file type of the image.
     */
    public String getTypeOfFile(){
        return typeOfFile;
    }

    /**
     *  Getter for the pixel array.
     *  @return The matrix representing the three values for each
     *  pixels of the image.
     */
    public short[][][] getPixels(){
        return pixels;
    }
    
    /**
     *  The method creates a PGM image from the colour image and returns it.
     *  @return The PGM image corresponding to the colour image.
     */
    public PGMImage makeGrey(){
        int width = this.width;
        int height = this.height;
        int maxShade = this.maxShade;
        String typeOfFile = "P2";
        short[][] greyMatrix = new short[height][width];

        /* Set all elements in the greyMatrix by iterating over all
         * rows and columns. The outer loop iterates over the rows.
         */
        for (int row = 0; row < height; row++){
            // The inner loop iterates over the column.
            for (int column=0; column < width; column++){
                /* Next we read the three colour values corresponding
                 * to the pixel at position [row][column].
                 */
                short[] rgb = getPixels()[row][column];
                /* totalGrey is the sum of the three colour values
                 * used to compute the rounded average. Note that we
                 * do not use a short for the value but int, since the
                 * sum of three positive short values may fall outside
                 * the range of positive short values.
                 */
                int totalGrey = 0;
                // Add up the three colour values.
                for (int colour = 0; colour < 3; colour++) {
                    totalGrey += rgb[colour];
                }
                /* Pixel value as the rounded average of the colour
                 * values, converted to short.
                 */
                greyMatrix[row][column] = (short) Math.round(totalGrey/3.0);
            }
        }
        return new PGMImage(width, height, maxShade,
                            typeOfFile, greyMatrix);
    }

        
    /*
     * main method with an initial test of the class.
     */
    public static void main(String[] args) {
        PPMImage cs = readPPMImage("ComputerScience.ppm");
        cs.makeGrey().writePGMImage("ComputerScience.pgm");
    }
}
