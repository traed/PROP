package prop.seminar1;

import java.io.IOException;
import java.util.*;

public class Parser implements IParser {
	
	private Tokenizer tokenizer;
	private Lexeme previous;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {

		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
		
		System.out.println(lexemeList);
	}

	@Override
	public INode parse() throws IOException, TokenizerException,
	ParserException {

		INode node = block();

		if(tokenizer.peek() != null)
			throw new ParserException("Input error. Reached EOF but not EOF token was found.");

		return node;
	}

	private INode block(){
		//block = '{' + stmts + '}' ;
	    BlockNode block = new BlockNode();
	    if(tokenizer.current().token() == Token.LEFT_CURLY) {
	    	block.bind(tokenizer.current());
	    	tokenizer.moveNext();
	    }

	    block.bind(stmts());

	    if(tokenizer.current().token() == Token.RIGHT_CURLY) {
	    	block.bind(tokenizer.current());
	    	tokenizer.moveNext();
	    }

	    return block;
	}

	private INode stmts(){
		//stmts = [ assign, stmts ] ;
		StatementNode stmt = new StatementNode();
		if(tokenizer.current().token() == Token.IDENT){
			stmt.bind(assign());
			stmt.bind(stmt());
		}
		return stmt;
	}

	private INode assign(){
		//assign = ID, '=', expr, ';' ;
		AssignmentNode assign = new AssignmentNode();
		assign.bind(tokenizer.current());
		tokenizer.moveNext();

		if(tokenizer.current().token() == Token.ASSIGN_OP) {
			assign.bind(tokenizer.current());
			tokenizer.moveNext();
		}

		assign.bind(expr());

		if(tokenizer.current().token() == Token.SEMICOLON) {
			assign.bind(tokenizer.current());
			tokenizer.moveNext();
		}
		return assign;
	}

	private INode expr(){
		//expr = term, [ ('+' | '-' ) , expr] ;
		ExpressionNode expr = new ExpressionNode();
		expr.bind(term());
		if(tokenizer.current().token() == Token.SUB_OP || tokenizer.current().token() == Token.ADD_OP) {
			expr.bind(tokenizer.current());
			tokenizer.moveNext();
			expr.bind(expr());
		}
		return expr;
	}

	private INode term(){
		//term = factor, [ ( '*' | '/' ) , term] ;
		TermNode term = new TermNode();
		term.bind(factor());
		if(tokenizer.current().token() == Token.MULT_OP || tokenizer.current().token() == Token.DIV_OP) {
			term.bind(tokenizer.current());
			tokenizer.moveNext();
			term.bind(term());
		}
		return term;
	}

	private INode factor(){
		//factor = INT | ID | '(', expr, ')' ;
		FactorNode factor = new FactorNode();
		if(tokenizer.current().token() == Token.INT_LIT || tokenizer.current().token() == Token.IDENT) {
			factor.bind(tokenizer.current());
			tokenizer.moveNext();
		} else if(tokenizer.current().token() == Token.LEFT_PAREN) {
			factor.bind(tokenizer.current());
			tokenizer.moveNext();
			factor.bind(expr());
			if(tokenizer.current().token() == Token.RIGHT_PAREN) {
				factor.bind(tokenizer.current());
				tokenizer.moveNext();
			}
		}
		return factor;
	}

	@Override
	public void close() throws IOException {
	    tokenizer.close();
	    
	}
}
