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
	//List<Lexeme> lexemeList = new ArrayList<Lexeme>();
	lexemeList = new LinkedList<Lexeme>();
	INode node;
	
	int counter = 0;
	
	while(tokenizer.moveNext()) {
					
	    lexemeList.add(tokenizer.current());
		
	    if(tokenizer.current().token() == Token.EOS) {
		//SentenceNode sentence = new SentenceNode(lexemeArr);
		
	    }
		
	}
	/*node = new TextNode(null);
	try {
		node.evaluate(lexemeArr);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	tokenizer.close();
    }
	
    @Override
    public INode parse() throws IOException, TokenizerException,
			ParserException {
	lookahead = lexemeList.getFirst();
	text();

	if(lookahead.token() != Token.EOS)
	    throw new ParserException("Input error. Reached EOF but not EOF token was found.");
	    
	return null;
    }

    private void nextLex(){
	lexemeList.pop();
	if(lexemeList.isEmpty())
	    lookahead = null;
	else
	    lookahead = lexemeList.getFirst();
    }
    private void text(){
	//text = sentence, [text];
	sentence();
	text();
    }
    private void sentence(){
	//sentence = nounphrase, verbphrase, '.';
	nounphrase();
	verbphrase();
	
	if(lookahead.token() == Token.EOS){
	    INode node = new EOSNode(lookahead.value());
	    nextLex();
	    return node;
	}
    }
    private void nounphrase(){
	//nounphrase = delimiter, noun;
	INode node;
	if(lookahead.token() == Token.DELIMITER){
	    node = new DelimiterNode(lookahead.value());
	    nextLex();
	    return node;
	}
	if(lookahead.token() == Token.NOUN){
	    node = new NounNode(lookahead.value());
	    nextLex();
	    return node;
	}
    }
    private INode verbphrase(){
	//verbphrase = verb, nounphrase;
	if(lookahead.token() == Token.VERB){
	    INode node = new VerbNode(lookahead.value());
	    nextLex();
	    return node;
	}
	nounphrase();
    }
    
    @Override
    public void close() throws IOException {
	
    }
}
