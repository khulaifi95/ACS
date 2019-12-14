import java.util.Date;
/**
 * Java goes with a minimal set of classes and methods. However, it is
 * also a language which has considerably grown. New packages have
 * been created and can be used by importing them as with the import
 * command above.
 * NOTE, there is also a corresponding String.format command in case 
 *       you want to return a String as value rather than print it.
 * @author Manfred Kerber (after Cay Horstman, 2008, Core Java, p. 66)
 * @version 2019-10-14
 */

public class PrintfMain {
/* printf allows printing in a flexible way. It takes the following
    conversions: (taken from Horstmann, Cay, 2008, Core Java, p. 66)


    char type                                          example
  ------------------------------------------------------------
    d    Decimal Integer                               159
    x    Hexadecimal Integer                           9f
    o    Octal Integer                                 237
    f    Fixed-point floating point                    15.9
    e    exponential floating point                    1.59e+01
    g    general floating point (shorter of e and f)   
    a    Hexadecimal floating point                    0x1.fccdp3
    s    String                                        Hello World
    c    Character                                     H
    h    Hash code                                     42628b2
    tx   Date and time                                 see below
    %    Percent sign                                  %
    n    Line separator

 In addition you can specify flags

    flag   purpose                                           example
  ---------------------------------------------------------------------
    +      Prints signs for positive and negative numbers    +3333.33
    space  Adds a space before positive numbers                3333.33
    #      Adds leading zeroes                               003333.33
    -      Left-justifies field                              3333.33
    (      Encloses negative numbers in parantheses          (3333.33)
    ,      Adds group separators                              3,333.33
    #      For format f always includes a decimal point       3333.
    #      For format x or o adds 0x or 0 as prefix           0xc012a
    $      Specifies the index of the argument to be          159 9F
           formatted; e.g., %1$d %1$x prints the first
           argument in decimal and hexadecimal
    <      Formats the same value as the previous             159 9F
           specification; for example %d %<x prints
           the same number in decimal and hexadecimal    


   Date and Time Conversion Characters
    

   char    type                                               example
 -----------------------------------------------------------------------
   c       complete date and time                  Wed Oct 28 11:31:59 GMT 2009
   F       ISO 8601 date                                      2008-10-28
   D       US formatted date (month/day/year)                 10/28/2009
   T       24-hour time                                       11:31:59
   r       12-hour time                                       11:31:59 AM
   R       24-hour time no seconds                            11:31  
   Y       4-digit year with leading zeroes                   2009
   y       last 2 digits of year with leading zeroes          09
   C       first 2 digits of year with leading zeroes         20
   B       full month name                                    October
   b or h  abbreviated month name                             Oct
   m       2-digit month (with leading zeroes)                10
   d       2-digit day (with leading zeroes)                  28
   e       2-digit day (without leading zeroes)               28
   A       full weekday name                                  Wednesday
   a       abbreviated weekday name                           Wed
   j       3-digit day of year (with leading zeroes 001-366)  301
   H       2-digit hour (with leading zeroes) 00-23           11
   k       2-digit hour (without leading zeroes) 0-23         11
   I       2-digit hour (with leading zeroes) 01-12           11
   l       2-digit hour (without leading zeroes) 1-12         11
   M       2-digit minutes (with leading zeroes)              31
   S       2-digit seconds (with leading zeroes)              59
   L       3-digit milliseconds (with leading zeroes)         124
   N       9-digit nanoseconds (with leading zeroes)          124000000
// P       uppercase morning or afternoon marker              PM               // ERROR?
   p       lowercase morning or afternoon marker              pm
   z       RFC 822 numberic offset from GMT                   +0000
   Z       time zone                                          GMT
   s       seconds since 1970-01-01 00:00:00 GMT              1256714968
   Q       milliseconds since 1970-01-01 00:00:00 GMT         1256714968586

 */





    public static void main(String[] args) {
      System.out.print("System.out.printf(\"%5d\",1)             ===>");System.out.printf("%5d\n",1);
      System.out.print("System.out.printf(\"%5d\",12)            ===>");System.out.printf("%5d\n",12);
      System.out.print("System.out.printf(\"%5d\",123)           ===>");System.out.printf("%5d\n",123);
      System.out.print("System.out.printf(\"%5d\",1234)          ===>");System.out.printf("%5d\n",1234);
      System.out.print("System.out.printf(\"%5d\",12345)         ===>");System.out.printf("%5d\n",12345);
      System.out.print("System.out.printf(\"%5d\",123456)        ===>");System.out.printf("%5d\n",123456);
      System.out.print("System.out.printf(\"%5x\",42)            ===>");System.out.printf("%x\n",42);
      System.out.print("System.out.printf(\"%5o\",42)            ===>");System.out.printf("%o\n",42);
      System.out.print("System.out.printf(\"%5+d\",42)           ===>");System.out.printf("%+d\n",42);
      System.out.print("System.out.printf(\"%5+d\",-42)          ===>");System.out.printf("%+d\n",-42);
      System.out.print("System.out.printf(\"%5 d\",42)           ===>");System.out.printf("% d\n",42);
      System.out.print("System.out.printf(\"%5 d\",-42)          ===>");System.out.printf("% d\n\n",-42);

      System.out.print("System.out.printf(\"%7.5f\",Math.PI)     ===>");System.out.printf("%7.5f\n",Math.PI);
      System.out.print("System.out.printf(\"%8.5f\",Math.PI)     ===>");System.out.printf("%8.5f\n",Math.PI);
      System.out.print("System.out.printf(\"%9.5f\",Math.PI)     ===>");System.out.printf("%9.5f\n",Math.PI);
      System.out.print("System.out.printf(\"%10.6f\",Math.PI)    ===>");System.out.printf("%10.6f\n",Math.PI);
      System.out.print("System.out.printf(\"%7.2f\",Math.PI)     ===>");System.out.printf("%7.2f\n",Math.PI);
      System.out.print("System.out.printf(\"%a\",Math.PI)        ===>");System.out.printf("%a\n\n",Math.PI);
      System.out.print("System.out.printf(\"%s\",\"Hello World\")  ===>");System.out.printf("%s\n","Hello World");
      System.out.print("System.out.printf(\"%20s\",\"Hello World\")===>");System.out.printf("%20s\n","Hello World");
      System.out.print("System.out.printf(\"%2s\",\"Hello World\") ===>");System.out.printf("%2s\n","Hello World");
      System.out.print("System.out.printf(\"%n\")                ===>");System.out.printf("%n");





      System.out.println("-------- DATE AND TIME ------------");
      Date currentDate = new Date();

      System.out.print("System.out.printf(\"%tc\",currentDate)   ===>");System.out.printf("%tc\n",currentDate);
      System.out.print("System.out.printf(\"%tF\",currentDate)   ===>");System.out.printf("%tF\n",currentDate);
      System.out.print("System.out.printf(\"%tD\",currentDate)   ===>");System.out.printf("%tD\n",currentDate);
      System.out.print("System.out.printf(\"%tT\",currentDate)   ===>");System.out.printf("%tT\n",currentDate);
      System.out.print("System.out.printf(\"%tr\",currentDate)   ===>");System.out.printf("%tr\n",currentDate);
      System.out.print("System.out.printf(\"%tR\",currentDate)   ===>");System.out.printf("%tR\n",currentDate);
      System.out.print("System.out.printf(\"%tY\",currentDate)   ===>");System.out.printf("%tY\n",currentDate);
      System.out.print("System.out.printf(\"%ty\",currentDate)   ===>");System.out.printf("%ty\n",currentDate);
      System.out.print("System.out.printf(\"%tC\",currentDate)   ===>");System.out.printf("%tC\n",currentDate);
      System.out.print("System.out.printf(\"%tB\",currentDate)   ===>");System.out.printf("%tB\n",currentDate);
      System.out.print("System.out.printf(\"%tb\",currentDate)   ===>");System.out.printf("%tb\n",currentDate);
      System.out.print("System.out.printf(\"%tm\",currentDate)   ===>");System.out.printf("%tm\n",currentDate);
      System.out.print("System.out.printf(\"%td\",currentDate)   ===>");System.out.printf("%td\n",currentDate);
      System.out.print("System.out.printf(\"%te\",currentDate)   ===>");System.out.printf("%te\n",currentDate);
      System.out.print("System.out.printf(\"%tA\",currentDate)   ===>");System.out.printf("%tA\n",currentDate);
      System.out.print("System.out.printf(\"%ta\",currentDate)   ===>");System.out.printf("%ta\n",currentDate);
      System.out.print("System.out.printf(\"%tj\",currentDate)   ===>");System.out.printf("%tj\n",currentDate);
      System.out.print("System.out.printf(\"%tH\",currentDate)   ===>");System.out.printf("%tH\n",currentDate);
      System.out.print("System.out.printf(\"%tk\",currentDate)   ===>");System.out.printf("%tk\n",currentDate);
      System.out.print("System.out.printf(\"%tI\",currentDate)   ===>");System.out.printf("%tI\n",currentDate);
      System.out.print("System.out.printf(\"%tl\",currentDate)   ===>");System.out.printf("%tl\n",currentDate);
      System.out.print("System.out.printf(\"%tI\",currentDate)   ===>");System.out.printf("%tI\n",currentDate);
      System.out.print("System.out.printf(\"%tM\",currentDate)   ===>");System.out.printf("%tM\n",currentDate);
      System.out.print("System.out.printf(\"%tS\",currentDate)   ===>");System.out.printf("%tS\n",currentDate);
      System.out.print("System.out.printf(\"%tL\",currentDate)   ===>");System.out.printf("%tL\n",currentDate);
      System.out.print("System.out.printf(\"%tN\",currentDate)   ===>");System.out.printf("%tN\n",currentDate);
      System.out.print("System.out.printf(\"%tp\",currentDate)   ===>");System.out.printf("%tp\n",currentDate);
      System.out.print("System.out.printf(\"%tz\",currentDate)   ===>");System.out.printf("%tz\n",currentDate);
      System.out.print("System.out.printf(\"%tZ\",currentDate)   ===>");System.out.printf("%tZ\n",currentDate);
      System.out.print("System.out.printf(\"%ts\",currentDate)   ===>");System.out.printf("%ts\n",currentDate);
      System.out.print("System.out.printf(\"%tQ\",currentDate)   ===>");System.out.printf("%tQ\n",currentDate);

      System.out.println();

    }
}
