package myTest;

/**
 *  If we try to compile A1 the compiler tries to also compile A2 and
 *  notices that A2 extends A1, that is, that the class hierarchy is
 *  cyclic and stops with a corresponding error.
 *
 *  @version 2015-11-10
 *  @author  Manfred Kerber
 */

public class A1 extends A2 {
}
