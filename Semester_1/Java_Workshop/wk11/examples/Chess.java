import java.util.function.Function;
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
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 *  In this class we show how to display a chess board with figures on
 *  it and use mouse clicks to move figures around. Note, no checks
 *  are made whether the moves conform to the rule book.
 *
 *  The images of the figures are taken from GNOME chess game.
 *
 *  @version 2018-12-08
 *  @author Manfred Kerber
 */
public class Chess extends Application{
    /** BRIGHT_GREY is used instead of white for the white squares so
     *  that the white figures are fully visible.
     */
    public static final Color BRIGHT_GREY =  Color.rgb(180, 180, 180);

    /** DARK_GREY is used instead of black for the black squares so
     * that the black figures are fully visible.
     */
    public static final Color DARK_GREY =  Color.rgb(100, 100, 100);

    /** ALMOST_BRIGHT_GREY is to darken a bright grey field slightly
     * to indicate that a mouse click has been noted.
     */
    public static final Color ALMOST_BRIGHT_GREY =  Color.rgb(160, 160, 160);

    /** ALMOST_DARK_GREY is to brighten up a dark grey field slightly
     * to indicate that a mouse click has been noted.
     */
    public static final Color ALMOST_DARK_GREY =  Color.rgb(120, 120, 120);

    /**  WIDTH is the width and height of a small squares of the board
     *  as well as an additional offset.
     */
    public static final int WIDTH = 50; 

    /**  TOTAL_WIDTH is the width and height the chessboard plus the
     *  coordinates added.
     */
    public static final int TOTAL_WIDTH = 10 * WIDTH; 

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
     *  A squares array contains Rectangle objects that will be added
     *  to the root. It represents the black (dark grey) and white
     *  (bright grey) square of the chessbooad.
     */
    private static Rectangle[][] squares = new Rectangle[8][8];

    /**
     *  To each Rectangle we attach an image with the figure on it. In
     *  order to have this also for the squares that do not contain
     *  figures we will use for simplicity an empty figure.
     */
    private static ImageView[][] imageViews = new ImageView[8][8];

    /**
     *  These are four auxiliary variables used to move figures. On
     *  the first click with the mouse, the variable firstI and firstJ
     *  will be set (with the coordinates of the field) and likewise
     *  with the second click the other two.
     */
    private static int firstI, firstJ, secondI, secondJ;

    /**
     *  The variable keeps track of whether in a move of a figure on
     *  the chessboard the mouse has been clicked for the first time
     *  (from-position) or the second time (to-position).
     */
    private static boolean onFirstClick = true;

    /**
     *  The chessboard is represented by a two-dimensional array of
     *  Strings for the different figures such as "rookB" for the
     *  black rook.  The method initializes the chessBoard array.
     */
    public static void initializeChessBoard() {
        for (int i = 0; i<8; i++)
            for (int j = 2; j<6;j++)
                chessBoard[i][j] = "";  // empty fields
 	chessBoard[0][0] = "rookB" ;    // Black Rook
	chessBoard[1][0] = "knightB" ;  // Black Knight
	chessBoard[2][0] = "bishopB" ;  // Black Bishop
	chessBoard[3][0] = "queenB" ;   // Black Queen
	chessBoard[4][0] = "kingB" ;    // Black Knight
	chessBoard[5][0] = "bishopB" ;  // Black Bishop
	chessBoard[6][0] = "knightB" ;  // Black Knight
	chessBoard[7][0] = "rookB" ;    // Black Rook
	
	chessBoard[0][7] = "rookW" ;    // White Rook
	chessBoard[1][7] = "knightW" ;  // White Knight
	chessBoard[2][7] = "bishopW" ;  // White Bishop
	chessBoard[3][7] = "queenW" ;   // White Queen
	chessBoard[4][7] = "kingW" ;    // White Knight
	chessBoard[5][7] = "bishopW" ;  // White Bishop
	chessBoard[6][7] = "knightW" ;  // White Knight
	chessBoard[7][7] = "rookW" ;    // White Rook
	
	for (int i = 0; i<8; i++){
	    chessBoard[i][1] = "pawnB" ;   // Black Pawns
	}
	for (int i = 0; i<8; i++){
	    chessBoard[i][6] = "pawnW" ;   // White Pawns
	}
    }

    /**
     *  The method looks up the different files in which the chess
     *  figures are stored.
     *  @param figureName The name of the figure such as "rookB" for
     *  the black rook.
     *  @return The file where the figure is stored,
     *  e.g. "rookBlack.gif";
     */        
    public static String lookupFile(String figureName) {
        switch(figureName) {
        case "rookB"   : return "rookBlack.gif";
        case "rookW"   : return "rookWhite.gif";
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
        default        : return "empty.gif";
        }
    }
    
    /**
     *  The method adds the coordinates to the chessboard (to the left
     *  and right as well as to the top and bottom of the board).
     *  @param root The group to which the coordinates are to be added.
     */
    public static void addCoordinates(Group root){
        // Write in black in a particular font the coordinates a-h and 1-8.
        Text text;
        for (int i = 0; i < 8; i++){
            //Two vertical lines with numbers 1-8
            //left line
            text = new Text(DIST,
                            (i+2)*WIDTH - Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) '8' - i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);

            //right line
            text = new Text(9*WIDTH +DIST,
                            (i+2)*WIDTH - Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) '8' - i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);

            //Two horizontal lines with character a-h
            //bottom line
            text = new Text((i+1)*WIDTH + DIST,
                            10*WIDTH - Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) 'a' + i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);

            //top line
            text = new Text((i+1)*WIDTH + DIST,
                            DIST + Y_WRITE_ADJUST,
                            String.format("%c",(char) ((int) 'a' + i)));
            text.setFont(Font.font ("Dialog", 24));
            root.getChildren().add(text);
        }
    }

    /**
     *   A single square is added to the chessboard, that is, to the
     *   root, at a particular position given by iPos and jPos. The
     *   square is provided with a background colour. At the same
     *   position, the image related to the square as stored in the
     *   chessBoard array, is added to the root.
     *
     *   @param root The group to which the square and the image is
     *   added.
     *   @param iPos The row of the square on the chessboard (counted
     *   from top).
     *   @param jPos The column of the square on the chessboard
     *   (counted from right).
     */
    public static void drawOneSquare(Group root, int iPos, int jPos){
        /* The left upper corner (iPos and jPos both zero) is bright.
         * Then they are alternatingly bright and dark grey.
         */
        Color background = ((iPos+jPos)%2 == 0) ? BRIGHT_GREY : DARK_GREY;
        /* The square is created at the correct position and added to
         * the squares array. This array is used later so that it can
         * act upon mouse clicks on it. The square is provided with
         * the appropriate background colour and added to the root.
         */
        squares[iPos][jPos] = new Rectangle((iPos+1)*WIDTH,
                                            (jPos+1)*WIDTH,
                                            WIDTH, WIDTH);
        squares[iPos][jPos].setFill(background);
        root.getChildren().add(squares[iPos][jPos]);

        /* The image associated with the position is looked up and
         * created. Then a corresponding ImageView imagV is created
         * and associated with the correct position. Then imagV is
         * added to the imageViews array. This array is used later so
         * that it can act upon mouse clicks on it. The imagV is also
         * added to the root.
         */
        String lfigureName = chessBoard[iPos][jPos];
        Image image = new Image("images/" + lookupFile(lfigureName));
        ImageView imagV = new ImageView(image);
        imagV.setX(WIDTH+ iPos*WIDTH);
        imagV.setY(WIDTH+ jPos*WIDTH);
        imageViews[iPos][jPos] = imagV;
        root.getChildren().add(imageViews[iPos][jPos]);
    }
    
    /**
     *  The chessboard is added to the roots, incl. the images.
     *  @param root The root object to whose children the squares are added.
     */
    public static void drawSquares(Group root) {
        // Add a thick line as boarder of the chessboard to the root.
        Rectangle outline = new Rectangle(WIDTH,WIDTH, 8*WIDTH,8*WIDTH);
        // Without the following command the polygon would be fully black.
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        outline.setStrokeWidth(5);
        root.getChildren().add(outline);

        // Draw the 64 fields on the board alternating dark and bright squares.
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                drawOneSquare(root, i, j);        
	    }
	}
    }

    /**
     *  The method adds click handlers to the mouse event of
     *  clicking. It is distinguished whether a click is a first click
     *  in moving a figure, that is, whether it is clicking on the
     *  from-position, or whether the click is a second click in
     *  moving the figure, that is, wether it is clicking on the
     *  to-position.
     *
     *  @param root The root object is used to re-initialize the
     *  chessboard after a move.
     */
    public static void addHandlers(Group root){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                /* As a technicality of Java, the compiler does not
                 * allow non-final local variables in the body of
                 * functions, hence, we copy the non-final variables i
                 * and j to corresponding final versions fi and fj.
                 */
                final int fi = i;
                final int fj = j;

                // The event handler for a click event.
                final EventHandler<MouseEvent> clickHandle = e -> {
                    /* We set the variables firstI and firstJ when the
                     * figure to be moved is at position fi and fj
                     */
                    if (onFirstClick) {
                        /*
                         *  If it is a first click and the field is empty
                         *  we do not want anything to happen.
                         */
                        if (chessBoard[fi][fj].equals("")) {
                        } else {
                            /*  There is a figure on the square. We
                             *  change the colour of the square by
                             *  either brightening up a dark field
                             *  slightly or darkening a bright field
                             *  slightly in order to indicate that the
                             *  click by the user has been registered.
                             */
                            if ((fi+fj)%2 == 0){
                                squares[fi][fj].setFill(ALMOST_BRIGHT_GREY);
                            } else {
                                squares[fi][fj].setFill(ALMOST_DARK_GREY);
                            }
                            firstI = fi;
                            firstJ = fj;
                            /* We set the variable onFirstClick to
                             * false in order to indicate that the
                             * next click is the to-position. 
                             */
                            onFirstClick = false;
                        }
                    } else { // i.e., second click.
                        secondI = fi;
                        secondJ = fj;
                        /* We set the variable onFirstClick to
                         * true in order to indicate that the
                         * next click is the from-position. 
                         */
                        onFirstClick = true;
                        /* no action is taken when the piece is put
                         * back to its original position.
                         */
                        if (firstI != secondI || firstJ != secondJ) {
                            chessBoard[secondI][secondJ] =
                                chessBoard[firstI][firstJ];
                            chessBoard[firstI][firstJ] = "";
                            /* The chess board is redrawn and the
                             * handlers reassigned.
                             */
                            drawSquares(root);
                            addHandlers(root);
                        }
                    }};

                /* The clickHandle is registered with the square AND
                 * with the figure on the square. If it were
                 * registered only with one of them the use would have
                 * to click on either the square outside the figure or
                 * on the figure. By registering the clickHandle with
                 * both objects the user can click on either and the
                 * click will be registered and executed.
                 */
                squares[fi][fj].setOnMouseClicked(clickHandle);
                imageViews[fi][fj].setOnMouseClicked(clickHandle);
            }
        }
    }

    /**
     *  start method
     *  It initializes the chess board with coordinates, draws the
     *  squares and adds handlers to them and the figures. The root is
     *  added to a scene, the scene to a stage.
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Create a Group (scene graph) with the line as single element.
        Group root = new Group();
        initializeChessBoard();
        addCoordinates(root);
        drawSquares(root);
        addHandlers(root);
        Scene scene = new Scene(root, TOTAL_WIDTH, TOTAL_WIDTH);

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
