import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 *
 * The class contains the two methods findLocalCustomer
 * and generateWelcomeEmails as well as a main method
 * to test the two methods.
 *
 * @author Alexandros Evangelidis, Manfred Kerber
 * @version 2018-11-27
 *
 */

public class CustomerMain {

  /**
   * The method filters all those customers whose
   * telephone number starts with a given areaCode and
   * returns them in a new ArrayList.
   *
   * @param cList An ArrayList of all customers of a
   *        company.
   * @param areaCode The area code for which we want to
   *        filter the list.
   * @return A new ArrayList with all those customers
   *         from the original list whose telephone
   *         number starts with the given areaCode.
   *
   */
  public static ArrayList<Customer> findLocalCustomer(ArrayList<Customer> cList, String areaCode) {
    ArrayList<Customer> local = new ArrayList<Customer>();

    /*
     * The loop invariant is that the variable local always
     * contains all those customers seen so far whose
     * telephone number starts with the correct area code.
     */
    for (Customer c : cList) {
      if (c.getTelephoneNumber().startsWith(areaCode)) {
        local.add(c);
      }
    }
    return local;
  }

  /**
   * A more general approach would be to use a function,
   * which we do with the following method that filters
   * the given ArrayList based on a general predicate,
   * that is, a function from Customer to Boolean.
   *
   * @param cList An ArrayList of all customers of a
   *        company.
   * @param predicate The function from Customer to
   *        Boolean with which the new ArrayList is
   *        created.
   * @return A new ArrayList with all those customers
   *         from the original list for whom the
   *         predicate returns true.
   */
  public static ArrayList<Customer> filter(ArrayList<Customer> cList,
      Function<Customer, Boolean> predicate) {
    ArrayList<Customer> result = new ArrayList<Customer>();

    /*
     * The loop invariant is that the variable result
     * always contains all those customers seen so far who
     * satisfy the predicate.
     */
    for (Customer c : cList) {
      if (predicate.apply(c)) {
        result.add(c);
      }
    }
    return result;
  }

  /**
   * The method generates files (named 1, 2, 3, ...) each
   * containing a welcome email for each of the local
   * customers.
   *
   * @param localCustomers An ArrayList of type Customer
   *        containing all local customers.
   */
  public static void generateWelcomeEmails(ArrayList<Customer> localCustomers) {
    // Since we want to write to files, we need to enclose
    // this in a try-catch
    // block.
    try {
      // The initial fileName should be 1
      int fileNumber = 1;
      /*
       * The loop invariant is that for each customer seen so
       * far, a welcome email has been generated.
       */
      for (Customer c : localCustomers) {
        BufferedWriter out = new BufferedWriter(new FileWriter("" + fileNumber));
        out.write("To: " + c.getAddress() + "\n" + "From: ACME \n"
            + "Subject: Welcome to new local service\n" + "--------\n" + "Dear "
            + c.getCustomerName() + ",\n Welcome to our new local service");
        fileNumber++;
        out.close();
      }

    } catch (IOException ex1) {
      System.out.println("Emails were not generated due to file handling problems. "
          + "Check whether the directory to which the emails should be written "
          + "exists and is writable.");
    }
  }

  /*
   * main method to test the methods written above.
   */
  public static void main(String[] args) {

    Customer c = new Customer("Alex", "alex@alex.org", "012134243241");
    Customer c1 = new Customer("Mary", "mary@mary.com", "012245452243");
    Customer c2 = new Customer("Jack", "jack@jack.net", "012234242");
    Customer c3 = new Customer("Sam", "sam@sam.com", "01212321321");
    Customer c4 = new Customer("Anna", "anna@anna.com", "012156696");

    ArrayList<Customer> list = new ArrayList<Customer>();

    list.add(c1);
    list.add(c2);
    list.add(c3);
    list.add(c);
    list.add(c4);

    generateWelcomeEmails(findLocalCustomer(list, "0121"));


    // ArrayList<Customer> local1 = findLocalCustomer(list,
    // "0121");

    // ArrayList<Customer> local2 = filter(list, cust ->
    // cust.getTelephoneNumber().startsWith("0121"));


  }
}

