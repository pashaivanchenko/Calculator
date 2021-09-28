package calculator.ui.model;

public enum Operators {
		ADD("+"),
		SUB("-"),
		MULTIPLY("*"),
		DIV("/"),
		POW("Pow"),//возведение числа в степень
		SQRT("Sqrt"),//квадратный корень
		EQUAL("="),
		UNKNOWN("");

		private String operator;

		Operators(String operation){
				this.operator = operation;
		}

		public static Operators getEnum(String value){
				for (Operators o : values()){
						if (o.getOperator().equalsIgnoreCase(value)) return o;
				}
				return UNKNOWN;
		}

		public static boolean isOperator(String value){
				for (Operators o : values()){
						if (o.getOperator().equalsIgnoreCase(value)) return true;
				}
				return false;
		}

		public String getOperator() {
				return operator;
		}
}
