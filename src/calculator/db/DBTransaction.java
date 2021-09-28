package calculator.db;

public class DBTransaction {
		private long id;
		private String expression;
		private String result;

		public DBTransaction(long id, String expression, String result) {
				this.expression = expression;
				this.result     = result;
				this.id = id;
		}

		public String getExpression() {
				return expression;
		}

		public String getResult() {
				return result;
		}

		public long getId() {
				return id;
		}
}
