import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.paint.Color; 


/**
 *  This is an example how to create and display an empty chessboard.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class EmptyChessBoard extends Application{
    /**  SMALL_SIZE is the width and height of a small squares of the board.
     */
    public static final int SMALL_SIZE = 50; 
    /** X_OFFSET moves the board on the x-axis out of the left upper corner.
     */
    public static final int X_OFFSET = 50;
    /** Y_OFFSET moves the board on the y-axis out of the left upper corner.
     */
    public static final int Y_OFFSET = 50; 

    /**
     *  @return A two-dimensional array of squares forming the
     *  chessboard given as the x-position, y-position, x-size,
     *  y-size. They are alternatingly coloured as white or black.
     */
    public static Rectangle[][] makeSquares() {
        Rectangle[][] squares = new Rectangle[8][8];
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
		squares[2*i][2*j] =
                    new Rectangle(X_OFFSET+ 2*i*SMALL_SIZE, Y_OFFSET+2*j*SMALL_SIZE,
                                  SMALL_SIZE, SMALL_SIZE);
                squares[2*i][2*j].setFill(Color.WHITE);

		squares[2*i+1][2*j+1] =
                    new Rectangle(X_OFFSET+ SMALL_SIZE*(2*i+1), Y_OFFSET+SMALL_SIZE*(2*j+1),
                                  SMALL_SIZE, SMALL_SIZE);
                squares[2*i+1][2*j+1].setFill(Color.WHITE);

		squares[2*i+1][2*j] = new Rectangle(X_OFFSET+ (2*i+1)*SMALL_SIZE, Y_OFFSET+2*j*SMALL_SIZE,
                                                    SMALL_SIZE,SMALL_SIZE);
                squares[2*i+1][2*j].setFill(Color.BLACK);

		squares[2*i][2*j+1] = new Rectangle(X_OFFSET+ SMALL_SIZE*2*i, Y_OFFSET+SMALL_SIZE*(2*j+1),
                                                    SMALL_SIZE,SMALL_SIZE);
                squares[2*i][2*j+1].setFill(Color.BLACK);
	    }
	}
        return squares;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle[][] chessboard = makeSquares();

        // Create a Group (scene graph) with the 64 elements added.
        Group root = new Group();
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                root.getChildren().add(chessboard[i][j]);
            }
        }
        root.getChildren().add(new Line(X_OFFSET,Y_OFFSET, 9*X_OFFSET,Y_OFFSET));
        root.getChildren().add(new Line(X_OFFSET,Y_OFFSET, X_OFFSET,9*Y_OFFSET));
        root.getChildren().add(new Line(X_OFFSET,9*Y_OFFSET, 9*X_OFFSET,9*Y_OFFSET));
        root.getChildren().add(new Line(9*X_OFFSET,Y_OFFSET, 9*X_OFFSET,9*Y_OFFSET));

        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 600);

        
        // Give the stage (window) a title and add the scene.
        stage.setTitle("Empty Chessboard");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  main method to launch the application.
     */
    public static void main(String[] args) { 
        launch(args);
    }
}

