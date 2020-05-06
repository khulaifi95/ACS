import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * This class displays a stylized magnifying glass and
 * the 42 grid lines (21 horizontal and 21 vertical
 * lines)
 *
 * @author Alexandros Evangelidis, Manfred Kerber
 * @version 2019-12-03
 */

public class Magnifying extends Application {
  public static final int offset = 20;
  public static final int SCENE_WIDTH = 280;
  public static final int SCENE_HEIGHT = 280;

  @Override
  public void start(Stage stage) throws Exception {
    Group root = new Group();

    Line line = new Line(offset + 105, offset + 105, offset + 180, offset + 180);
    root.getChildren().add(line);

    Circle glass = new Circle(offset + 70, offset + 70, 50);
    glass.setFill(null);
    glass.setStroke(Color.BLACK);
    root.getChildren().add(glass);
    for (int i = 0; i <= 20; i++) {
      Line horizontalLine = new Line(offset + 0, offset + 10 * i, offset + 200, offset + 10 * i);
      Line verticalLine = new Line(offset + 10 * i, offset + 0, offset + 10 * i, offset + 200);
      root.getChildren().addAll(horizontalLine, verticalLine);
    }

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
