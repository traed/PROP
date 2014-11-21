package prop.assignment0;

public class FactorNode implements INode {

	private Lexeme lexeme1, lexeme2;
	private INode child;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(lexeme2 != null) {
			return child.evaluate(args);	
		}
		if(lexeme1.token() == Token.IDENT){
			for(int i = 0; i < args.length; i++){
				Statement stmt = (Statement)args[i];
				System.out.println("happens" );
				
				if(stmt.getIdentifier().value().equals(lexeme1.value())) {
					System.out.println("happens " + lexeme1.value());

					return stmt.getIdentifier().value();
				}
			}
		}
		return lexeme1.value();
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append("FactorNode\n");
		tabs++;

		if(lexeme1.token() == Token.INT_LIT || lexeme1.token() == Token.IDENT){
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			builder.append(lexeme1.token() + " " + lexeme1.value() + "\n");
		}
		else if(lexeme1.token() == Token.LEFT_PAREN){
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			builder.append(lexeme1.token() + " " + lexeme1.value() + "\n");

			if(child != null)
				child.buildString(builder, tabs);

			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			builder.append(lexeme2.token() + " " + lexeme2.value() + "\n");
		}
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
