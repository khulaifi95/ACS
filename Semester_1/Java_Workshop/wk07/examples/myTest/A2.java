package myTest;

/**
 *  If we try to compile A2 the compiler tries to also compile A1 and
 *  notices that A1 extends A2, that is, that the class hierarchy is
 *  cyclic and stops with a corresponding error.
 *
 *  @version 2015-11-10
 *  @author  Manfred Kerber
 */
public class A2 extends A1 {
}
