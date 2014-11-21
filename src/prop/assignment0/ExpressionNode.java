package prop.assignment0;

public class ExpressionNode implements INode {

	private INode child1, child2;
	private Lexeme lexeme;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(child2 != null) {

			String i = child1.evaluate(args).toString();
			String j = child2.evaluate(args).toString();
			System.out.println("i:"+i +" j:"+ j);
			double iD = 0;
			double jD = 0;

			try {
				iD = Double.parseDouble(i);
				jD = Double.parseDouble(j);

			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException in ExprNode: " + i + " || " + j + " != double");

				for(int iterator = 0; iterator < args.length; iterator++) {
					Statement stmnt = (Statement)args[iterator];

					if(stmnt.getIdentifier().value().equals(i)) {
						System.out.println("identifier i in ExpressionNode");
						iD = stmnt.getValue();
						break;

					} else if (stmnt.getIdentifier().value().equals(j)) {
						System.out.println("identifier j in ExpressionNode");
						jD = stmnt.getValue();
						break;
					}
				}
			}
			
			if(lexeme.token() == Token.ADD_OP) {
				System.out.println("Operator +");
				return iD + jD;
			} else {
				System.out.println("operator -");
				return iD - jD;
			}
		}
		return child1.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append("ExpressionNode\n");
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
