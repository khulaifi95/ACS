import java.io.*;

/** There are different ways how to read in from and write out to
 * files. One way is via BufferedReader and BufferedWriter. We declare
 * a BufferedReader in, linked to the file test1.in, and a
 * BufferedWriter out, linked to test1.out. We read from in by
 * readLine() until the end of the buffer, print out the strings and
 * write them with out.write to the test1.out file.  Don't forget to
 * close the buffers, in particular when writing, since otherwise not
 * everything may be written to the file.
 *
 * The exception handling kicks in if the files are not found, or not
 * readable/writable.
 *
 * @version 2016-10-19
 * @author Manfred Kerber
 *
 */
public class ReadFromFileTest{
    public static void main(String[] args) {
        try {
            String readString;

            BufferedReader in = 
                new BufferedReader(new FileReader("test1.in"));

            BufferedWriter out = 
                new BufferedWriter(new FileWriter("test1.out"));
            int counter = 0;
            while ((readString = in.readLine()) != null) {
                System.out.println(readString);
                counter++;
                out.write(counter + " " + readString + "\n");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
