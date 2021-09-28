package calculator;

import calculator.db.DBQuery;
import calculator.ui.view.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Calculator extends Application {

		private Stage primaryStage;
		private VBox rootLayout;
		private CalculatorController controller;


		@Override
		public void start(Stage primaryStage) throws Exception {
				this.primaryStage = primaryStage;
				this.primaryStage.setTitle("Калькулятор");
				DBQuery dbQuery = new DBQuery();
				initCalculatorUI(dbQuery);
		}

		private void initCalculatorUI(DBQuery dbQuery) throws IOException {
				FXMLLoader loader =new FXMLLoader();
				loader.setLocation(Calculator.class.getResource("ui/view/CalculatorView.fxml"));
				rootLayout = loader.load();
				Scene scene = new Scene(rootLayout);
				primaryStage.setScene(scene);
				primaryStage.show();
				controller = loader.getController();
				controller.setPrimaryStage(primaryStage);
				controller.setDbQuery(dbQuery);
		}

		public static void main(String[] args){
				launch(args);
		}

}
