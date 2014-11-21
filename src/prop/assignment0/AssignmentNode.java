package prop.assignment0;

public class AssignmentNode implements INode {

	private Lexeme lexeme1, lexeme2, lexeme3;
	private INode child;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		
		Object o = child.evaluate(args);

		return new Statement(lexeme1, (double)o);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append("AssignmentNode\n");
		tabs++;
		
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append(lexeme1.token() + " " + lexeme1.value() + "\n");
		
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append(lexeme2.token() + " " + lexeme2.value() + "\n");

		if(child != null)
			child.buildString(builder, tabs);

		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append(lexeme3.token() + " " + lexeme3.value() + "\n");
	}

	public void addLexeme(Lexeme lex) {
		if(lexeme1 == null)
			lexeme1 = lex;
		else if(lexeme2 == null)
			lexeme2 = lex;
		else if(lexeme3 == null)
			lexeme3 = lex;
	}

	public void addChild(INode node) {
		child = node;
	}

	public Lexeme getIdent() {
		return lexeme1;
	}
}
