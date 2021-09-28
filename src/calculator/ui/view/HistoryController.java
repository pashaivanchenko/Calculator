package calculator.ui.view;

import calculator.ui.model.Transactions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;

public class HistoryController {

		@FXML
		private ListView<Transactions> listView;
		private Text output;
		public HistoryController() {
		}

		@FXML
		private void initialize(){
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
						if (newValue != null){
								output.setText(newValue.getResult());
						}
				});
		}


		public void setTransactionsObservableList(
						ObservableList<Transactions> transactionsObservableList) {
				listView.setItems(transactionsObservableList);
		}

		public void setOutput(Text output) {
				this.output = output;
		}
}
