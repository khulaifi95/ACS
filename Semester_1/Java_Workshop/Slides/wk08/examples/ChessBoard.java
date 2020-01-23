import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color; 
import javafx.scene.text.Text; 
import javafx.scene.text.Font; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *  This is an example how to display a chess board with figures on it
 *  in their original positions.
 *
 *  @version 2018-08-28
 *  @author Manfred Kerber
 */
public class ChessBoard extends Application{


    /** BRIGHT_GREY used instead of white so that white figures are fully visible.
     */
    public static final Color BRIGHT_GREY =  Color.rgb(180, 180, 180);
    /** DARK_GREY used instead of black so that black figures are fully visible.
     */
    public static final Color DARK_GREY =  Color.rgb(100, 100, 100);
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
     *  DIST is used to keep a distance of the text from the board.
     */
    public static final int DIST = 20;
    /** SMALL_OFF is used to lower the images slightly.
     */
    public static final int SMALL_OFF = 5;
    /** Y_WRITE_ADJUST is a correction of the y-value on the y-axis
     */
    public static final int Y_WRITE_ADJUST = 20;

    
    /**
     *  A chessboard is represented by a 8x8 matrix.
     */
    private static String[][] chessBoard = new String[8][8];


    /**
     *  The chessboard is represented by a two-dimensional array of
     *  Strings for the different figures such as "towerB" for the
     *  black tower.  The method initializes the chessBoard array.
     */
    public void initializeChessBoard() {
        for (int i = 0; i<8; i++)
            for (int j = 2; j<6;j++)
                chessBoard[i][j] = "";
 	chessBoard[0][0] = "towerB" ;   // Black Tower
	chessBoard[1][0] = "knightB" ;  // Black Knight
	chessBoard[2][0] = "bishopB" ;  // Black Bishop
	chessBoard[3][0] = "queenB" ;   // Black Queen
	chessBoard[4][0] = "kingB" ;    // Black Knight
	chessBoard[5][0] = "bishopB" ;  // Black Bishop
	chessBoard[6][0] = "knightB" ;  // Black Knight
	chessBoard[7][0] = "towerB" ;   // Black Tower
	
	chessBoard[0][7] = "towerW" ;   // White Tower
	chessBoard[1][7] = "knightW" ;  // White Knight
	chessBoard[2][7] = "bishopW" ;  // White Bishop
	chessBoard[3][7] = "queenW" ;   // White Queen
	chessBoard[4][7] = "kingW" ;    // White Knight
	chessBoard[5][7] = "bishopW" ;  // White Bishop
	chessBoard[6][7] = "knightW" ;  // White Knight
	chessBoard[7][7] = "towerW" ;   // White Tower
	
	for (int i = 0; i<8; i++){
	    chessBoard[i][1] = "pawnB" ;   // Black Pawn
	}
	for (int i = 0; i<8; i++){
	    chessBoard[i][6] = "pawnW" ;   // White Pawn
	}   
    }

    /**
     *  The empty chessboard is drawn on the panel.
     *  @param root The root object to whose children the squares are added.
     */
    public static void drawSquares(Group root) {
        Rectangle outline = new Rectangle(X_OFFSET,Y_OFFSET,8*SMALL_SIZE,8*SMALL_SIZE);
        // Without the following command the polygon would be fully black.
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        outline.setStrokeWidth(5);
        root.getChildren().add(outline);

        Rectangle[][] squares = new Rectangle[8][8];
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
		squares[2*i][2*j] =
                    new Rectangle(X_OFFSET+ 2*i*SMALL_SIZE,
                                  Y_OFFSET+2*j*SMALL_SIZE, SMALL_SIZE, SMALL_SIZE);
                squares[2*i][2*j].setFill(BRIGHT_GREY);
                
                squares[2*i+1][2*j+1] =
                    new Rectangle(X_OFFSET+ SMALL_SIZE*(2*i+1),
                                  Y_OFFSET+SMALL_SIZE*(2*j+1), SMALL_SIZE, SMALL_SIZE);
                squares[2*i+1][2*j+1].setFill(BRIGHT_GREY);
		squares[2*i+1][2*j] =
                    new Rectangle(X_OFFSET+ (2*i+1)*SMALL_SIZE,
                                  Y_OFFSET+2*j*SMALL_SIZE, SMALL_SIZE,SMALL_SIZE);
                squares[2*i+1][2*j].setFill(DARK_GREY);
		squares[2*i][2*j+1] =
                    new Rectangle(X_OFFSET+ SMALL_SIZE*2*i,
                                  Y_OFFSET+SMALL_SIZE*(2*j+1), SMALL_SIZE,SMALL_SIZE);
                squares[2*i][2*j+1].setFill(DARK_GREY);
                root.getChildren().add(squares[2*i][2*j]);
                root.getChildren().add(squares[2*i+1][2*j]);
                root.getChildren().add(squares[2*i][2*j+1]);
                root.getChildren().add(squares[2*i+1][2*j+1]);
	    }
	}
        

        // Write in black in a particular font the coordinates a-h and 1-8.
        Text text;
        for (int i = 0; i < 8; i++){
            text = new Text(DIST,
                            (i+1)*SMALL_SIZE + Y_OFFSET - Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) '8' - i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);

            text = new Text(i*SMALL_SIZE + X_OFFSET + DIST,
                            9*SMALL_SIZE + Y_OFFSET - Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) 'a' + i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);
            
            text = new Text(i*SMALL_SIZE + X_OFFSET + DIST,
                            DIST + Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) 'a' + i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);

            text = new Text(8*SMALL_SIZE +X_OFFSET +DIST,
                            (i+1)*SMALL_SIZE+Y_OFFSET - Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) '8' - i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);
        }
    } // end drawEmptyChessBoard();

    /**
     *  The chessboard figures are put on the chessboard.
     *  @param root The root object to whose children the squares are added.
     */
    public static void drawFigures(Group root){
	for (int i=0; i<8; i++){
	    for (int j=0; j<8; j++){
		drawFigure(root, chessBoard[i][j], i, j);
            }
	}
    }

    /**
     *  The method looks up the different files in which the chess
     *  figures are stored.
     *  @param figureName The name of the figure such as "towerB" for
     *  the black tower.
     *  @return The file where the figure is stored,
     *  e.g. "towerBlack.gif";
     */        
    public static String lookupFile(String figureName) {
        switch(figureName) {
        case "towerB"  : return "towerBlack.gif";
        case "towerW"  : return "towerWhite.gif";
        case "knightB" : return "knightBlack.gif";
	case "knightW" : return "knightWhite.gif";
	case "bishopB" : return "bishopBlack.gif";
	case "bishopW" : return "bishopWhite.gif";
        case "queenB"  : return "queenBlack.gif";
        case "queenW"  : return "queenWhite.gif";
        case "kingB"   : return "kingBlack.gif";
        case "kingW"   : return "kingWhite.gif";
        case "pawnB"   : return "pawnBlack.gif";
        case "pawnW"   : return "pawnWhite.gif";
        default        : return "";
        }
    }

    /**
     *  Auxiliary function to draw a single figure onto the chess board.
     *  @param root The group object to whose children the figure is added.
     *  @param figureName The name of the figure such as "towerB" for
     *  the black tower.
     *  @param xPos The horizontal position on the chessboard from 0-7
     *  with 0 for the leftmost column.
     *  @param yPos The vertical position on the chessboard from 0-7
     *  with 0 for the upmost row.
     */
    public static void drawFigure(Group root, String figureName, int xPos, int yPos){
        if (!lookupFile(figureName).equals("")) {
            Image image = new Image("images/" + lookupFile(figureName));
            //Setting the image view 
            ImageView imageView = new ImageView(image); 
            imageView.setX(X_OFFSET+xPos*SMALL_SIZE);
            imageView.setY(Y_OFFSET+yPos*SMALL_SIZE + SMALL_OFF);
        
            //setting the fit height and width of the image view 
            imageView.setFitHeight(SMALL_SIZE); 
            imageView.setFitWidth(SMALL_SIZE);

            //Setting the preserve ratio of the image view 
            imageView.setPreserveRatio(true);  
            root.getChildren().add(imageView);
        }
    }

    /**
     *  We create a frame with a panel on which the chessboard is displayed.
     *  @param stage The window to be displayed.
     */
        
    @Override
    public void start(Stage stage) throws Exception {
        // Create a Group (scene graph) with the line as single element.
        Group root = new Group();
        initializeChessBoard();
        drawSquares(root);
        drawFigures(root);
        // The scene consists of just one group.
        Scene scene = new Scene(root, 600, 600);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Chess");
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
