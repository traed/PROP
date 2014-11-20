package prop.assignment0;

public class Statement {

	private Lexeme identifier;
	private double value;

	public Statement(Lexeme identifier, double value) {
		this.identifier = identifier;
		this.value = value;
	}

	public Lexeme getIdentifier() {
		return identifier;
	}

	public double getValue() {
		return value;
	}
}