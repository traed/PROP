package prop.assignment0;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.*;

public class Parser implements IParser {

	private Scanner scanner = new Scanner();
	private ArrayList<Object> wordArray;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner.open(fileName);
		String word = "";
		while("" + scanner.current() != " ")
			word += scanner.current();
		wordArray.add(word);
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
