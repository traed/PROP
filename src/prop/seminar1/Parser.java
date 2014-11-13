package prop.seminar1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser {
	
	private Tokenizer tokenizer;
	
	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		
		tokenizer = new Tokenizer();
		
		tokenizer.open(fileName);
		//List<Lexeme> lexemeList = new ArrayList<Lexeme>();
		Lexeme[] lexemeArr = new Lexeme[tokenizer.getSize()];
		INode node;
		
		int counter = 0;
		
		while(tokenizer.moveNext()) {
						
			lexemeArr[counter] = tokenizer.current();
			
			
			
			if(tokenizer.current().token() == Token.EOS) {
				//SentenceNode sentence = new SentenceNode(lexemeArr);
			
			}
			
		}
		node = new TextNode(null);
		try {
			node.evaluate(lexemeArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tokenizer.close();
	}
	
	@Override
	public INode parse() throws IOException, TokenizerException,
			ParserException {
		return null;
	}

	@Override
	public void close() throws IOException {
		
	}

}
