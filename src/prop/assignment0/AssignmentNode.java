package prop.assignment0;

public class AssignmentNode implements INode {

	private Lexeme lexeme1, lexeme2, lexeme3;
	private INode child;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		// TODO Auto-generated method stub
		
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
}
