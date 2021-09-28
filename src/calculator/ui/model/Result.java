package calculator.ui.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Result extends BigDecimal {

		public Result(double val) {
				super(val, MathContext.DECIMAL64);
		}
}
