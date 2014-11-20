package prop.assignment0;

import java.io.IOException;
import java.util.LinkedList;

public class Tokenizer implements ITokenizer {
	private Scanner scanner;
	private Lexeme currentLexeme;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		currentLexeme = null;

		scanner = new Scanner();

		scanner.open(fileName);
	}

	@Override
	public Lexeme current() {
		return currentLexeme;
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {

		if(scanner != null) { 
			char currentChar;
			scanner.moveNext();
			currentChar = scanner.current();
			
			if(currentChar == scanner.EOF) {
				currentLexeme = new Lexeme(currentChar+"", Token.EOF);

			} else if(currentChar == '\n') {
				moveNext();
			} else if(currentChar == '\t') {
				moveNext();
			} else if(currentChar == ' ') {
				moveNext();	
			} else {
				lookup(currentChar);
				
			}
		}
	}

	@Override
	public void close() throws IOException {
		scanner.close();
	}
	
	private void lookup(char currentChar) throws TokenizerException {

		String matchString = currentChar+"";

		switch(matchString) {
		case "+": currentLexeme = new Lexeme(matchString, Token.ADD_OP); break;
		case "=": currentLexeme = new Lexeme(matchString, Token.ASSIGN_OP); break;
		case "-": currentLexeme = new Lexeme(matchString, Token.SUB_OP); break;
		case "*": currentLexeme = new Lexeme(matchString, Token.MULT_OP); break;
		case "/": currentLexeme = new Lexeme(matchString, Token.DIV_OP); break;
		case "(": currentLexeme = new Lexeme(matchString, Token.LEFT_PAREN); break;
		case ")": currentLexeme = new Lexeme(matchString, Token.RIGHT_PAREN); break;
		case ";": currentLexeme = new Lexeme(matchString, Token.SEMICOLON); break;
		case "{": currentLexeme = new Lexeme(matchString, Token.LEFT_CURLY); break;
		case "}": currentLexeme = new Lexeme(matchString, Token.RIGHT_CURLY); break;

		default: 
			if(matchString.matches("[a-z]")) 
				currentLexeme = new Lexeme(matchString, Token.IDENT);

			else if(matchString.matches("\\d"))
				currentLexeme = new Lexeme(matchString, Token.INT_LIT);

			else {
				throw new TokenizerException("TokenizerException: Unsupported character " + matchString);
			}
		}
	}
}
