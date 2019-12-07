import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 *  Realization of an EventHandler in a separate class.
 *  This class contains just the EventHandler for the mouse event.
 *  Essentially, when the button is clicked the program is terminated
 *  by the System.exit(1) command.
 *
 *  The example is adapted from the 4th edition of "Absolute Java" by
 *  Walter Savitch, p.971.
 *
 *  @version 2019-11-29
 *  @author Manfred Kerber
 *
 */
public class EndingListener implements EventHandler<MouseEvent> {

    /**
     *  The eventHandlerEnd is defined by the method handle which
     *  takes a MouseEvent as parameter. In case of a suitable mouse
     *  event, the two commands to firstly print something and
     *  secondly to terminate the program are executed.
     */
    public void handle(MouseEvent e) { 
        System.out.println("This is the end."); 
        System.exit(1);
    } 
}
