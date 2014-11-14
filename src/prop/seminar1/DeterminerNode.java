package prop.seminar1;

public class DeterminerNode implements INode {
	
	private Lexeme lexeme;
	
	private INode leftChild;
	private INode rightChild;
    
        public DeterminerNode(Lexeme lexeme){
	    leftChild = null;
	    rightChild = null;
	    this.lexeme = lexeme;
        }
    
	@Override
	public Object evaluate(Object[] args) throws Exception {

		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
	    System.out.println("Determiner Node!");
	    builder.append(lexeme.value() + "\n");
	}
	
        public Lexeme getLexeme(){
	    return lexeme;
        }
        public String getValue(){
	    return (String)lexeme.value();
        }
        public Token getToken(){
	    return lexeme.token();
	}
	public INode getLeftChild() {        	
	    return leftChild;
	}
    
	public INode getRightChild() {
	    return rightChild;
	}
      
}
