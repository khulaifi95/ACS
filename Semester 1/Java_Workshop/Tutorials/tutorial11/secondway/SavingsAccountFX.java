import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class makes use of the FXMLLoader class to parse the "ui.fxml" file and to create
 * the JavaFX components it specifies.
 * 
 * @author Alexandros Evangelidis, Manfred Kerber
 * @version 2018-12-05
 */

public class SavingsAccountFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ui.fxml"));
		Scene scene = new Scene(root, 350, 150);
		stage.setScene(scene);
		stage.setTitle("Savings Account");
		stage.show();
	}
}
