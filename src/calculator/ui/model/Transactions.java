package calculator.ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transactions {
		private StringProperty expression = new SimpleStringProperty("");
		private StringProperty result;
		private boolean flag = false;

		public Transactions(){}

		public Transactions(String expression, String result) {
				this.expression = new SimpleStringProperty(expression);
				this.result     = new SimpleStringProperty(result);
		}

		public void set(String value){
				if (!flag) {
						expression = new SimpleStringProperty(expression.get() + value);
				}else flag = false;
		}

		public void set(Operators value){
				if (value != Operators.EQUAL && value != Operators.SQRT){
						String oper = value == Operators.POW ? "^" : value.getOperator();
						expression = new SimpleStringProperty(expression.get() +" "+ oper + " ");
				}
		}

		public void set(double value, Operators operators){
				switch (operators){
						case SQRT:
								expression = new SimpleStringProperty(expression.get() + operators.getOperator() + "(" + value + ")");
								flag = true;
								break;
				}
		}

		public void setResult(String result) {
				this.result = new SimpleStringProperty(result);
		}

		@Override
		public String toString() {
				return expression.get();
		}

		public String getResult() {
				return result.get();
		}

		public String getExpression() {
				return expression.get();
		}

		public StringProperty expressionProperty() {
				return expression;
		}

		public StringProperty resultProperty() {
				return result;
		}
}
