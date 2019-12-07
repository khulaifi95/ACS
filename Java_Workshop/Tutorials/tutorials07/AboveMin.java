import java.util.ArrayList;

/**
 *   <pre>
 *   The exercise related to this class has two purposes:
 *   - Rehearse the computation of min and derived methods.
 *   - Provide understanding of computational complexity
 *   </pre>
 *   @version 2017-11-23
 *   @author Alexandros Evangelidis, Manfred Kerber
 */
public class AboveMin {

    /**
     *  The minimum of an ArrayList (of a polymorphic type Measurable)
     *  is computed by initializing the result variable min to the 0th
     *  element in the ArrayList, and then iterating through all the
     *  other elements in the list updating min if a smaller element
     *  is found. 
     *
     *  The complexity is linear, since in the loop we go exactly once
     *  through the loop and each step has constant complexity.
     *
     *  [Note, we do not do exception handling for a empty
     *  ArrayList. We could easily by checking whether the ArrayList
     *  is emtpy and if it is to throw an IllegalArgumentException.]
     *  @param a A non-empty ArrayList of polymorphic type Measurable.
     *  @return The minimal value (wrt getMeasure) of all the values
     *  got from the elements in the ArrayList.
     */
    public static double min(ArrayList<Measurable> a) {
        double min = a.get(0).getMeasure();
        /* The loop invariant is that the variable min always contains
         * the minimal value seen so far.
         */
        for (int i = 1; i < a.size(); i++){
            if (a.get(i).getMeasure() < min) {
                min = a.get(i).getMeasure();
            }
        }
        return min;
    }

    /**
     *  The following method has linear complexity, since the
     *  computation of the minimum is linear and the loop requires to go
     *  exactly once through the ArrayList a and each single step has
     *  constant complexity.
     *  @param a A non-empty ArrayList of polymorphic type Measurable.
     *  @return An ArrayList of equal length in which the values are
     *  given, however, all reduced by the minimal value.
     */
    public static ArrayList<Double> aboveMin1(ArrayList<Measurable> a) {
        double min = min(a);
        ArrayList<Double> result = new ArrayList<Double>();
        for (Measurable el : a) {
            result.add(el.getMeasure() - min);
        }
        return result;
    }

    /**
     *  The following method has quadratic complexity, since the
     *  computation of the minimum is linear and the loop requires to go
     *  exactly once through the ArrayList a and each single step has
     *  linear complexity of recomputing the minimum.
     *  @param a A non-empty ArrayList of polymorphic type Measurable.
     *  @return An ArrayList of equal length in which the values are
     *  given, however, all reduced by the minimal value.
     */
    public static ArrayList<Double> aboveMin2(ArrayList<Measurable> a) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (Measurable el : a) {
            result.add(el.getMeasure() - min(a));
        }
        return result;
    }
}
