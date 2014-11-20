package prop.assignment0;

public class TermNode implements INode {

	private INode child1, child2;
	private Lexeme lexeme;

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
		lexeme = lex;
	}

	public void addChild(INode node) {
		if(child1 == null)
			child1 = node;
		else if(child2 == null)
			child2 = node;
	}

}
