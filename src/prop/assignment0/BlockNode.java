package prop.assignment0;

public class BlockNode implements INode {

	private Lexeme lexeme1, lexeme2;
	private INode child;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		Statement[] statementArray = new Statement[1024];
		if(child != null)
			return child.evaluate(statementArray);
		

		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("BlockNode\n" + lexeme1.token() + " " + lexeme1.value() + "\n");
		tabs++;
		if(child != null)
			child.buildString(builder, tabs);
		builder.append(lexeme2.token() + " " + lexeme2.value() + "\n");
	}

	public void addLexeme(Lexeme lex) {
		if(lexeme1 == null)
			lexeme1 = lex;
		else if(lexeme2 == null)
			lexeme2 = lex;
	}

	public void addChild(INode node) {
		child = node;
	}

}
