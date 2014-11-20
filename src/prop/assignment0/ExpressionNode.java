package prop.assignment0;

public class ExpressionNode implements INode {

	private INode child1, child2;
	private Lexeme lexeme;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(child2 != null) {
			double i = (double)child1.evaluate(args).value();
			double j = (double)child2.evaluate(args).value();
			if(lexeme.token() == Token.ADD_OP)
				return i + j;
			return i - j;
		}
		return (double)child1.evaluate(args).value();
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
