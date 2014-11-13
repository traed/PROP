package prop.seminar1;

public class SentenceNode implements INode {

	private Lexeme[] lexemeArr;
	
	INode parent;
	INode child1;
	INode child2;
	
	public SentenceNode(INode parent) {
		this.parent = parent;
	}
	
	public void setChild1(INode child) {
		
		this.child1 = child;
	}
	
	public void setChild2(INode child) {
		
		this.child2 = child;
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
	
		
		
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

		for(int i = 0; i < lexemeArr.length; i++) {
			
			builder.append(lexemeArr[i].value());
		}
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder();
		buildString(sb, 0);
		
		return sb.toString();
	}

}
