package somePackage;
/**  
 *   Example to demonstrate the difference between public, protected,
 *   default (package access), and private by comparing access:
 *   A a class,
 *   B a different class B in the same package, 
 *   C a subclass of A in the same package, 
 *   D a subclass of A in a different package, and
 *   E a different class in a different package.
 *
 *   Adapted from [Absolute Java, 4th Edition by Walter Savitch, 2010,
 *   p.481]
 *
 *   @version 2015-11-10
 *   @author Manfred Kerber
 */

public class C extends A {
    public void CPrint() {
        System.out.println(v1); //access
        System.out.println(v2); //access
        System.out.println(v3); //access
        System.out.println(v4); //no access (private), compiler error
    }
}
