/** Use meaningful variable names, or you can't read your own program.
 *
 *  A variable name in Java begins with a letter (that is, any Unicode
 *  character which stand for a letter in some language, in particular,
 *  'A'-'Z', 'a'-'z', and '_') and is a sequence of letters and digits
 *  (that is, any Unicode character that denotes a digit in some
 *  language, in particular, '0'-'9'.)
 *
 *  Certain names are reserved and can not be used, e.g. you cannot write
 *  int if = 7;
 *
 *  Note that variable names are case sensitive (that is, distinguish
 *  between lower case and upper case letters).
 *
 *  If we just use the two times 26 standard Latin (English) letters
 *  and 10 arabic digits, we write for this
 *  <pre>
 *  [letter] ::= 'A'|'B'|'C'|'D'|'E'|'F'|'G'|'H'|'I'|'J'|'K'|'L'|'M'|
 *  'N'|'O'|'P'|'Q'|'R'|'S'|'T'|'U'|'V'|'W'|'X'|'Y'|'Z'|
 *  'a'|'b'|'c'|'d'|'e'|'f'|'g'|'h'|'i'|'j'|'k'|'l'|'m'|
 *  'n'|'o'|'p'|'q'|'r'|'s'|'t'|'u'|'v'|'w'|'x'|'y'|'z'|'_'
 *
 *  [digit] ::= '0'|'1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9'
 *
 *  [letter_or_digit] ::= [letter]|[digit]
 *  [variable_name]   ::= [letter][letter_or_digit]*
 *
 *  That is, a variable name is a letter followed by any number 
 *  (also 0) of letters or digits.
 *
 *  Note that '_' is a letter, but '-', '@', and '~' are not.
 *
 *  This form of writing is the Backus-Naur Form (BNF) to express a
 *  so-called context-free grammar. The | stands for or, * for any
 *  number of repetitions, + for any positive number of repetitions.
 *  The letters 'A' to 'z' and the digits '0' to '9' are called terminals.
 *  </pre>
 *
 *  It follows that we have in principle arbitrarily many variable
 *  names at our disposition.
 *
 *  USING MEANINGFUL NAMES (FOR VARIABLES AND OTHER ENTITIES) IS AN
 *  IMPORTANT WAY TO KEP THE PROGRAM READABLE. THE READABILITY OF
 *  PROGRAMS IS OF GREAT IMPORTANCE, SINCE PORGRAMS WHICH ARE NOT
 *  READABLE CANNOT BE MAINTAINED.  We will learn other important ways
 *  of structuring programs and keeping them readable.
 * 
 *  See also:
 *  http://www.cs.bham.ac.uk/internal/courses/java/msc/resources/labnotes/CodeConventions.pdf
 *
 *  @author  Manfred Kerber
 *  @version 2014-09-27
 */

public class Var8 {
    public static void main(String[] args) {

       /* Example: Conversion imperial to metric system
	* non-meaningful variable names
        */

       double TEST = 2.54;
       double TeSt = 12;
       double TesT = TEST * TeSt;
       double TEst = 5;
       double TeST = 7;
       double TESt = TesT*TEst + TEST*TeST;

       System.out.print("A person with a height of "); 
       System.out.print(TEst);
       System.out.print(" foot and ");
       System.out.print(TeST);
       System.out.print(" inches is ");
       System.out.print(TESt);
       System.out.println(" cm tall.");
   }
}
