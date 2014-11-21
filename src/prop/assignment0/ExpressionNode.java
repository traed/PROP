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
			
			try {
				double iD = Double.parseDouble(i);
				double jD = Double.parseDouble(j);
				
				if(lexeme.token() == Token.ADD_OP)
					return iD + jD;
				return iD - jD;
			
			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException in ExprNode: " + i + " || " + j + " != double");
				
				return i + j;
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
