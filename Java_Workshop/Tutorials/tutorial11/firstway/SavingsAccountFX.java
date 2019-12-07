import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This class creates a GUI for the SavingsAccount class. The layout of the GUI
 * and the handling of events are coded inside the start method.
 * 
 * @author Alexandros Evangelidis, Manfred Kerber
 * @version 2018-12-05
 *
 */
public class SavingsAccountFX extends Application {
	private Customer john;
	private SavingsAccount account;

	@Override
	public void start(Stage stage) {
		// We create a customer, a savings account and make an initial deposit.
		john = new Customer("John Smith", "56 ", "123132");
		account = new SavingsAccount(john, "056486241", "password", 0.05);
		account.deposit(22200, 25);
		/*
		 * As a layout container, we use the GridPane class which allows us to create a
		 * flexible grid of rows and columns.
		 */
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		/*
		 * The label accountNumber is created and added to the GridPane in position 0
		 * (columns),0 (rows). Note that the index of the column comes first.
		 */
		Label accountNumber = new Label("" + account.getNumber());
		grid.add(accountNumber, 0, 0);

		/*
		 * TO BE COMPLETED The label "accountName" is created and added to the GridPane
		 * in position 1 (columns),0 (rows). Note that the index of the column comes
		 * first.
		 */
		// Label accountName = ???????

		/*
		 * We first create the button of the balance, and then use another layout
		 * container (HBox), which layouts its children (in this case balanceButton) on
		 * a single horizontal row. Then, we add this container to the grid. This
		 * demonstrates the fact that we can create a container within another
		 * container.
		 */
		Button balanceButton = new Button("Balance");
		HBox hbBalanceButton = new HBox(10);
		hbBalanceButton.setAlignment(Pos.CENTER);
		hbBalanceButton.getChildren().add(balanceButton);
		grid.add(hbBalanceButton, 0, 1);

		/*
		 * We create a text field for the balance, which is initially empty. The text
		 * field "balanceTextField" is added to the GridPane in position 1 (columns), 1
		 * (rows).
		 */
		TextField balanceTextField = new TextField();
		grid.add(balanceTextField, 1, 1);

		/*
		 * The handling of events can be done by either using an anonymous inner class or by
		 * replacing the use of the anonymous inner class with a lambda expression.
		 * 
		 * balanceButton.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent e) { balanceTextField.setText(""+
		 * account.getBalance()); } });
		 */

		balanceButton.setOnAction(event ->  balanceTextField.setText("" + account.getBalance()));


		Button interestButton = new Button("Interest");
		HBox hbInterestButton = new HBox(10);
		hbInterestButton.setAlignment(Pos.CENTER);
		hbInterestButton.getChildren().add(interestButton);
		grid.add(hbInterestButton, 0, 2);

		/*
		 * TO BE COMPLETED We create a text field for the balance, which is initially
		 * empty. The text field "interestTextField" is added to the GridPane in
		 * position 1 (columns), 2 (rows).
		 */
		// TextField interestTextField ??????
		// grid.add ????????

		// TO BE COMPLETED
		// interestButton. ??????

		Scene scene = new Scene(grid, 350, 150);
		stage.setScene(scene);
		stage.setTitle("Savings Account");
		stage.show();
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
