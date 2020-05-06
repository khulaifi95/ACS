import java.io.*;
import java.net.URL;

/** 
 * Just as with reading a file from the harddrive as seen in the class
 * ReadFromFileTest.java, it is also possible to read in a file via
 * the internet from a given URL such as that of the Canvas page of our
 * module in the example below.
 *
 * @version 2016-10-19
 * @author Manfred Kerber
 */
public class Html {
    public static void main(String[] args){
        String s = "https://birmingham.instructure.com/courses/31154";
        String readString;
        try {
            URL url = new URL(s);
            BufferedReader in =
                new BufferedReader(new
                                   InputStreamReader(url.openStream()));
            BufferedWriter out =
                new BufferedWriter(new FileWriter("test1.html"));
            int counter = 0;
            while ((readString = in.readLine()) != null) {
                System.out.println(readString);
                counter++;
                out.write(counter + " " + readString + "\n");
            }

            in.close();
            out.close();
        }
        catch (IOException e) {
            System.out.println("no access to URL: " + s);
        }
    }
}
