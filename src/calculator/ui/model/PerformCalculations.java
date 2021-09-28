package calculator.ui.model;

import java.io.IOException;

public class PerformCalculations {

		public static Result calculate(double value1, double value2, Operators operators) throws IOException{
				switch (operators){
						case ADD:
								return add(value1, value2);
						case DIV:
								return div(value1, value2);
						case SUB:
								return sub(value1, value2);
						case SQRT:
								return sqrt(value1);
						case POW:
								return degree(value1, value2);
						case MULTIPLY:
								return multiple(value1, value2);
						case EQUAL:
								return new Result(value1);
				}
				throw new IOException("Неизвестная операция");
		}

		private static Result add(double value1, double value2){
				System.out.println(value1 + " + " + value2);
				return new Result(value1 + value2);
		}

		private static Result sub(double value1, double value2){
				return new Result(value1 - value2);
		}

		private static Result multiple(double value1, double value2){
				return new Result(value1 * value2);
		}

		private static Result div(double value1, double value2) throws IOException{
				if (value2 == 0)  throw new IOException("Делить на ноль нельзя.");
				return new Result(value1 / value2);
		}

		private static Result degree(double value1, double value2){
				return new Result(Math.pow(value1, value2));
		}

		private static Result sqrt(double value1){
				return new Result(Math.sqrt(value1));
		}
}
