package calculator.ui.view;

import calculator.Calculator;
import calculator.db.DBQuery;
import calculator.ui.model.Operators;
import calculator.ui.model.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

import static calculator.ui.model.PerformCalculations.calculate;

public class CalculatorController {

		@FXML
		private Text output;
		@FXML
		private VBox vBox;
		private Stage primaryStage;
		private double value1 = 0;
		private double value2 = 0;
		private Operators operators = Operators.UNKNOWN;
		private Operators preOperators = Operators.UNKNOWN;
		private boolean start = true;
		private int colOperator = 0;
		private ObservableList<Transactions> transactionsList = FXCollections.observableArrayList();
		private Transactions transactions = new Transactions();
		private BigDecimal result;
		private HistoryController historyController;
		private StackPane pane;
		private DBQuery dbQuery;

		public CalculatorController() {
		}
		@FXML
		private void initialize(){
				/*vBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
								if (event.getCode().isDigitKey()){
										output.setText(event.getText());
								}
								switch (event.getCode()){
										case DIGIT0: output.setText("0"); break;
										case NUMPAD1: System.out.println("1"); break;
								}
						}
				});*/
				try {
						FXMLLoader loader = new FXMLLoader(Calculator.class.getResource("ui/view/HistoryView.fxml"));
						pane              = loader.load();
						historyController = loader.getController();
						historyController.setTransactionsObservableList(transactionsList);
						historyController.setOutput(output);
				}catch (Exception ignored){
						ignored.printStackTrace();
				}
		}

		@FXML
		private void value(ActionEvent event){
				if (start){
						start = false;
						output.setText("");
				}
				String value = ((Button) event.getSource()).getText();
				output.setText(output.getText() + value);
		}

		@FXML
		private void calc(ActionEvent event){
				String operator = ((Button) event.getSource()).getText();
				if (operator.isEmpty()) return;
				try {
						if (Operators.getEnum(operator) == Operators.SQRT){
								double v = Double.parseDouble(getValue());
								output.setText(String.valueOf(calculate(v, 0, Operators.SQRT)));
								transactions.set(v, Operators.SQRT);
								return;
						}
						transactions.set(getValue());
						transactions.set(Operators.getEnum(operator));
						if (operator.equals("=")) {
								value2 = Double.parseDouble(getValue());
								result = calculate(value1, value2, operators);
								output.setText(result.toString());
								start = true;
								colOperator = 0;
								transactions.setResult(result.toString());
								dbQuery.setTransaction(transactions);
								if (transactionsList.size() == 10) transactionsList.clear();
								transactionsList.add(0, transactions);
								transactions = new Transactions();
						}else {
								if(colOperator == 0){
										value1 = Double.parseDouble(getValue());
										operators = Operators.getEnum(operator);
								}
								colOperator++;
								if (colOperator > 1) {
										value1 = calculate(value1, Double.parseDouble(getValue()), operators).doubleValue();
										operators = Operators.getEnum(operator);
										output.setText(String.valueOf(value1));
										value2 = value1;
								}
								start = true;
						}
				}catch (IOException e){
						output.setText(e.getMessage());
						clear();
				}
		}

		@FXML
		private void history(){
				for (Transactions t : transactionsList){
						System.out.println(t.toString());
				}
				if (vBox.getChildren().contains(pane)){
						vBox.getChildren().remove(pane);
				}else vBox.getChildren().add(0, pane);
		}

		@FXML
		private void backspace(){
				if (!output.getText().equals("")){
						String out = getValue();
						output.setText(out.substring(0, output.getText().length()-1));
				}
		}

		@FXML
		private void clear(){
				output.setText("");
				start = true;
				colOperator = 0;
				result = null;
				value1 = 0;
				value2 = 0;
				transactions = new Transactions();
		}

		private String getValue(){
				if (output.getText().equals("") || output.getText().equals(".")) output.setText("0");
				return output.getText();
		}

		public void setPrimaryStage(Stage primaryStage) {
				this.primaryStage = primaryStage;
		}

		public void setDbQuery(DBQuery dbQuery) {
				this.dbQuery = dbQuery;
				transactionsList.addAll(dbQuery.getTransactionsList(10));
		}
}
