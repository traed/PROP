package prop.seminar1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer implements ITokenizer {
	private int index;
	
	private List<Lexeme> lexemeList;
	private List<String> stringList;
	
	private Scanner charScanner;
	public Tokenizer() {
		
		index = -1;
		lexemeList = new ArrayList<Lexeme>();
		stringList = new ArrayList<String>();
		charScanner = new Scanner();
		
	}
	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		
		charScanner.open(fileName);
		
		char currentChar;
		StringBuilder sb = new StringBuilder();
		while(charScanner.moveNext()) {
			currentChar = charScanner.current();

			if(currentChar != ' ' && currentChar != '.') {

				sb.append(currentChar);
			}
			else if(currentChar == '.') {
				
				stringList.add(sb.toString());
				stringList.add(".");
				
				sb = new StringBuilder();
			}
			
			else {
				stringList.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		
		for(String str: stringList) {
			//System.out.println(str + " length: " +str.length());
			
			switch(str.toLowerCase()) {
			
			case "a": lexemeList.add(new Lexeme(str, Token.DETERMINER)); break;
			
			case "the": lexemeList.add(new Lexeme(str, Token.DETERMINER)); break;
			
			case "cat": lexemeList.add(new Lexeme(str, Token.NOUN)); break;
			
			case "mouse": lexemeList.add(new Lexeme(str, Token.NOUN)); break;
			
			case "scares": lexemeList.add(new Lexeme(str, Token.VERB)); break;
			
			case "hates": lexemeList.add(new Lexeme(str, Token.VERB)); break;
			
			case ".": lexemeList.add(new Lexeme(str, Token.EOS)); break;
			}
		}
	}

	@Override
	public Lexeme current() {
		return lexemeList.get(index);
	}

	@Override
	public boolean moveNext() throws IOException, TokenizerException {
		
		if(index < lexemeList.size()-1) {

			index++;
			return true;
		}
		else 
			return false;
	}

	@Override
	public void close() throws IOException {
		
		charScanner.close();
	}
	
	public int getSize() {
		
		return lexemeList.size();
	}
	
	public static void main(String[]args) {
		
		new Tokenizer();
	}

}
