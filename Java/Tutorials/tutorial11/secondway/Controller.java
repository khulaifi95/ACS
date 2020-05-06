import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * A "controller" class which is associated with the "ui.fxml" file.
 * This is an incomplete class and has to be completed according to the .fxml file.
 * @author Alexandros Evangelids, Manfred Kerber
 * @version 2018-12-05
 *
 */
public class Controller {	
	//TO BE COMPLETED
	
	@FXML
	private TextField balanceTextField;
	
	@FXML
	private Label accountNumberLabel;
	
	@FXML
	private Button balanceButton;
	
	private Customer john;
	private SavingsAccount account;

	/**
	 * TO BE COMPLETED
	 * This method performs the necessary initialisations.
	 */
	@FXML
	private void initialize() {
		john = new Customer("John Smith", "56 ", "123132");
		account = new SavingsAccount(john, "056486241", "password", 0.05);
		account.deposit(22200, 25);
		accountNumberLabel.setText(account.getNumber());
		//TO BE COMPLETED: the code for the customer label		
	}

	/**
	 * TO BE COMPLETED
	 * This method displays the balance in the text field when
	 * the balance button is clicked. 
	 * @param event, an event which represents a mouse click. 
	 */
	@FXML
	protected void handleBalanceButtonAction(ActionEvent event) {
		
	}

	/**
	 * TO BE COMPLETED
	 * This method displays the interest in the text field when
	 * the interest button is clicked. 
	 * @param event, an event which represents a mouse click. 
	 */
	@FXML
	protected void handleInterestButtonAction(ActionEvent event) {
		
	}	

}
