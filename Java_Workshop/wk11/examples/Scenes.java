import java.util.function.Function;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 
import javafx.scene.text.Text; 
import javafx.scene.text.Font; 
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.control.Button;

/** 
 *  
 *  The method is to show how on a stage the scenes displayed can be
 *  changed. Initially, a scene with three buttons is presented, one
 *  to select the game of Memory, one to select the game of Chess, and
 *  one to exit the application. If one of the games is selected the
 *  scene displayed on the stage is changed to the corresponding scene
 *  from Memory.java or Chess.java, respectively.
 *
 *  @version 2018-12-08
 *  @author Manfred Kerber
 */

public class Scenes extends Application {
    private Scene sceneMemory, sceneChess;
    
        public Scene initialScene(Stage stage) {
        // A new Button is created with the label "Play Memory"
        Button memoryButton = new Button("Play Memory");
        /* Event handler for the Memory button to change the scene to
         * the memory scene.
         */
        final EventHandler<MouseEvent> eventHandlerMemory = e -> {
            stage.setScene(sceneMemory);
        };
        // The position of the Button is determined.
        memoryButton.setLayoutX(50);
        memoryButton.setLayoutY(60);
        // The event handler is associated with the button.
        memoryButton.setOnMouseClicked(eventHandlerMemory);

        // A new Button is created with the label "Play Chess"
        Button chessButton = new Button("Play Chess");
        /* Event handler for the Chess button to change the scene to
         * the chess scene.
         */
        final EventHandler<MouseEvent> eventHandlerChess = e -> {
            stage.setScene(sceneChess);
        };
        // The position of the Button is determined.
        chessButton.setLayoutX(200);
        chessButton.setLayoutY(60);
        // The event handler is associated with the button.
        chessButton.setOnMouseClicked(eventHandlerChess);

        // A new Button is created with the label "Exit"
        Button endButton = new Button("Exit");
        // Event handler for the exit button to exit the application.
        final EventHandler<MouseEvent> eventHandlerEnd = e -> {
            System.exit(1);
        };
        // The position of the Button is determined.
        endButton.setLayoutX(350);
        endButton.setLayoutY(60);
        // The event handler is associated with the button.
        endButton.setOnMouseClicked(eventHandlerEnd);
        
        //The three buttons are added to the initial scene.
        Group initialRoot = new Group(memoryButton, chessButton, endButton);
        return new Scene(initialRoot, 450, 150);
        }

    /**
     *  start method
     * 
     *  Initially, an initial scence with three buttons is displayed.
     *  Also the games of Memory and Chess are initialized and
     *  associated with corresponding scenes sceneMemory and
     *  sceneChess.
     * 
     *  If the play memory button is pressed, the scene will be
     *  changed from the initialScene to sceneMemory, likewise on
     *  pressing the play chess button to sceneChess.
     *
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // The initial scene is created and added to the stage.
        Scene initialScene = initialScene(stage);
        stage.setTitle("Games");
        stage.setScene(initialScene);
        stage.show();
        
        // sceneMemory is created and populated.
        Group rootMemory = new Group();
        sceneMemory = new Scene(rootMemory, Memory.WIDTH, Memory.HEIGHT);
        Memory.randomizeImages();
        Memory.addTiles(rootMemory);

        // sceneChess is created and populated.
        Group rootChess = new Group();
        sceneChess = new Scene(rootChess, Chess.TOTAL_WIDTH,
                               Chess.TOTAL_WIDTH);
        Chess.initializeChessBoard();
        Chess.addCoordinates(rootChess);
        Chess.drawSquares(rootChess);
        Chess.addHandlers(rootChess);
    }

    /*
     *  main method to launch the application.
     */
    public static void main(String[] args) { 
        launch(args);
    }
}
