package prop.assignment0;

import java.io.IOException;
import java.util.*;

public class Parser implements IParser {
	
	private Tokenizer tokenizer;
	private Lexeme previous;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {

		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
		
	}

	@Override
	public INode parse() throws IOException, TokenizerException,
	ParserException {
		tokenizer.moveNext();
		INode node = block();
		
		if(tokenizer.current().token() != Token.EOF)
			throw new ParserException("Input error. Reached EOF but no EOF token was found.");

		return node;
	}

	private INode block() throws IOException, TokenizerException, ParserException {
		//block = '{' + stmts + '}' ;
	    BlockNode block = new BlockNode();
	    
	    if(tokenizer.current().token() == Token.LEFT_CURLY) {
	    	block.addLexeme(tokenizer.current());
	    	tokenizer.moveNext();
	    } else {
	    	throw new ParserException("ParserException while creating a block node: " + tokenizer.current().token() + " instead of LEFT_CURLY_BRACKETS.");
	    }
	    block.addChild(stmts());

	    if(tokenizer.current().token() == Token.RIGHT_CURLY) {
	    	block.addLexeme(tokenizer.current());
	    	tokenizer.moveNext();
	    } else {
	    	throw new ParserException("ParserException while creating a block node: " + tokenizer.current().token() + " instead of RIGHT_CURLY_BRACKETS.");
	    }

	    return block;
	}

	private INode stmts() throws IOException, TokenizerException, ParserException {
		//stmts = [ assign, stmts ] ;
		StatementsNode stmt = new StatementsNode();
		
		if(tokenizer.current().token() == Token.IDENT){
			stmt.addChild(assign());
			stmt.addChild(stmts());
		} else if (tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_CURLY) {
			throw new ParserException("ParserException while creating stmts node: " + tokenizer.current().token() + " when only IDENTIFIER and RIGHT_CURLY allowed.");
		}
		return stmt;
	}

	private INode assign() throws IOException, TokenizerException, ParserException {
		//assign = ID, '=', expr, ';' ;
		AssignmentNode assign = new AssignmentNode();
		assign.addLexeme(tokenizer.current());
		tokenizer.moveNext();

		if(tokenizer.current().token() == Token.ASSIGN_OP) {
			assign.addLexeme(tokenizer.current());
			tokenizer.moveNext();
		} else {
			throw new ParserException("ParserException while creating ass node: " + tokenizer.current().token() + " instead of ASSIGN_OPERATOR.");
		}
	
		assign.addChild(expr());

		if(tokenizer.current().token() == Token.SEMICOLON) {
			assign.addLexeme(tokenizer.current());
			tokenizer.moveNext();
		}
		else {
			throw new ParserException("ParserException while creating ass node: " + tokenizer.current().token() + " instead of SEMICOLON.");
		}
		return assign;
	}

	private INode expr() throws IOException, TokenizerException, ParserException {
		//expr = term, [ ('+' | '-' ) , expr] ;
		ExpressionNode expr = new ExpressionNode();
		expr.addChild(term());
		if(tokenizer.current().token() == Token.SUB_OP || tokenizer.current().token() == Token.ADD_OP) {
			
			expr.addLexeme(tokenizer.current());
			tokenizer.moveNext();
			expr.addChild(expr());
		
		} else if (tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
			throw new ParserException("ParserException while creating expression node: " + tokenizer.current().token() + " when only SUB_OP, ADD_OP, INT, IDENTIFIER or LEFT_PARENTHESIS allowed.");
		}
		return expr;
	}

	private INode term() throws IOException, TokenizerException, ParserException {
		//term = factor, [ ( '*' | '/' ) , term] ;
		TermNode term = new TermNode();
		term.addChild(factor());
		
		if(tokenizer.current().token() == Token.MULT_OP || tokenizer.current().token() == Token.DIV_OP) {
			term.addLexeme(tokenizer.current());
			tokenizer.moveNext();
			term.addChild(term());
		} else if (tokenizer.current().token() != Token.SUB_OP && tokenizer.current().token() != Token.ADD_OP && tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
			throw new ParserException("ParserException while creating Term Node: " + tokenizer.current().token() + " when not expected");
		}
		return term;
	}

	private INode factor() throws IOException, TokenizerException, ParserException {
		//factor = INT | ID | '(', expr, ')' ;
		FactorNode factor = new FactorNode();
		if(tokenizer.current().token() == Token.INT_LIT || tokenizer.current().token() == Token.IDENT) {
			factor.addLexeme(tokenizer.current());
			tokenizer.moveNext();
		} else if(tokenizer.current().token() == Token.LEFT_PAREN) {

			factor.addLexeme(tokenizer.current());
			tokenizer.moveNext();
			factor.addChild(expr());
			if(tokenizer.current().token() == Token.RIGHT_PAREN) {
				factor.addLexeme(tokenizer.current());
				tokenizer.moveNext();
			} else {
				throw new ParserException("ParserException while creating factor node: " + tokenizer.current().token() + " instead of RIGHT_PARENTHESIS");
			}
		} else if (tokenizer.current().token() != Token.MULT_OP && tokenizer.current().token() != Token.DIV_OP && tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.LEFT_PAREN) {
			throw new ParserException("ParserException while creating factor node: " + tokenizer.current().token() + " instead of MULT_OP, DIV_OP, INT, IDENTIFIER or LEFT_PARANTHESIS");
		}
		return factor;
	}

	@Override
	public void close() throws IOException {
	    tokenizer.close();
	    
	}
}
