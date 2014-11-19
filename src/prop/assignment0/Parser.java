package prop.seminar1;

import java.io.IOException;
import java.util.*;

public class Parser implements IParser {
	
	private Tokenizer tokenizer;
	private LinkedList<Lexeme> lexemeList;
	private Lexeme lookahead;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {

		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
		lexemeList = new LinkedList<Lexeme>();
		INode node;
		int counter = 0;

		while(tokenizer.moveNext()) {
			lexemeList.add(tokenizer.current());
		}
		tokenizer.close();
		System.out.println(lexemeList);
	}

	@Override
	public INode parse() throws IOException, TokenizerException,
	ParserException {
		lookahead = lexemeList.getFirst();
		INode textNode = text();

		if(lookahead != null)
			throw new ParserException("Input error. Reached EOF but not EOF token was found.");

		return textNode;
	}

	private void nextLex(){
		lexemeList.pop();
		if(lexemeList.isEmpty())
			lookahead = null;
		else
			lookahead = lexemeList.getFirst();
	}

	private INode block(){
		//block = '{' + stmts + '}' ;
		return null;
	}

	private INode stmts(){
		//stmts = [ assign, stmts] ;
		return null;
	}

	private INode assign(){
		//assign = ID, '=', expr, ';' ;
		return null;
	}

	private INode expr(){
		//expr = term, [ ('+' | '-' ) , expr] ;
		return null;
	}

	private INode term(){
		//term = factor, [ ( '*' | '/' ) , term] ;
		return null;
	}

	private INode factor(){
		//factor = INT | ID | '(', expr, ')' ;
	}





	/*private INode text(){
		//text = sentence, [text];
		TextNode text = new TextNode();
		try{
			text.bind(sentence());
		} catch(ParserException pe) {
			System.err.println(pe.getMessage());
		}
		
		nextLex();
		if(lookahead != null)
			text.bind(text());

		return text;
	}
	private INode sentence() throws ParserException {
		//sentence = nounphrase, verbphrase, '.';
		SentenceNode sentence = new SentenceNode();
		sentence.bind(nounphrase());
		sentence.bind(verbphrase());
		
		if(lookahead.token() != Token.EOS){
			throw new ParserException("Reached EOS but no EOS symbol found.");
		}
		return sentence;
	}
	private INode nounphrase(){
		//nounphrase = delimiter, noun;
		NounPhraseNode NPNode = new NounPhraseNode();
		INode node;
		if(lookahead.token() == Token.DETERMINER){
			node = new DeterminerNode(lookahead);
			nextLex();
			NPNode.bind(node);
		}
		if(lookahead.token() == Token.NOUN){
			node = new NounNode(lookahead);
			nextLex();
			NPNode.bind(node);
		}
		return NPNode;
	}
	private INode verbphrase(){
		//verbphrase = verb, nounphrase;
		VerbPhraseNode VPNode = new VerbPhraseNode();
		if(lookahead.token() == Token.VERB){
			INode node = new VerbNode(lookahead);
			nextLex();
			VPNode.bind(node);
		}
		VPNode.bind(nounphrase());
		return VPNode;
	}*/

	@Override
	public void close() throws IOException {
		
	}
}
