package prop.assignment0;

public class TermNode implements INode {

	private INode child1, child2;
	private Lexeme lexeme;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(child2 != null) {
			Statement stmnt = new Statement(null, 0);
			String i = child1.evaluate(args).toString();
			String j = child2.evaluate(args).toString();
			double iD = 0;
			double jD = 0;

			try {
				iD = Double.parseDouble(i);
				jD = Double.parseDouble(j);

			} catch (NumberFormatException e) {
				
				for(int iterator = 0; iterator < args.length; iterator++) {

					if(args[iterator] != null && args[iterator].getClass() == stmnt.getClass()) {
						stmnt = (Statement)args[iterator];

						if(stmnt.getIdentifier().value().equals(i)) {
							iD = stmnt.getValue();
							

						} else if (stmnt.getIdentifier().value().equals(j)) {
							jD = stmnt.getValue();

						}
					}
				}
			}

			if(lexeme.token() == Token.MULT_OP)
				return iD * jD;
				
			return iD / jD;

		}
		return child1.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append("TermNode\n");
		tabs++;

		if(child1 != null)
			child1.buildString(builder, tabs);

		if(lexeme != null) {
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			builder.append(lexeme.token() + " " + lexeme.value() + "\n");
			if(child2 != null)
				child2.buildString(builder, tabs);
		}		
	}

	public void addLexeme(Lexeme lex) {
		lexeme = lex;
	}

	public void addChild(INode node) {
		if(child1 == null)
			child1 = node;
		else if(child2 == null)
			child2 = node;
	}

}
