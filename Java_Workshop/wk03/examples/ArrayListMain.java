import java.util.ArrayList;

/** ArrayList as a generalization of arrays with no fixed size.
 *  We create an empty ArrayList and different elements to it.
 *  @author   Manfred Kerber
 *  @version  20 August 2018
 */
public class ArrayListMain { 

    /** 
     *  The following method prints an ArrayList of type String.
     *  @param items The ArrayList to be printed.
     */
    public static void print(ArrayList<String> items) {
        for (int i = 0; i < items.size(); i++){
            System.out.printf("%s ", items.get(i));}
        System.out.println();
    }

    public static void main(String[] args) {
	ArrayList<String> items = new ArrayList<String>();
	items.add("this");
	items.add("is");
	items.add("an");
	items.add("even");
	items.add("longer");
	items.add("longer");
	items.add("list");
        print(items);
    }
}
