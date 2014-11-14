package prop.seminar1;

public class VerbPhraseNode implements INode {
	
	private String text;
	
	private INode leftChild;
	private INode rightChild;
	
	@Override
	public Object evaluate(Object[] args) throws Exception {

		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		
	}
	
	public void setLeftChild(INode child) {
		this.leftChild = child;
		
	}
	
	public void setRightChild(INode child) {
		this.rightChild = child;
	}
	
	public INode leftChild() {
		
		return leftChild;
	}
	
	public INode rightChild() {
		
		return rightChild;
	}
	
	public void setText(String text) {
		
		this.text = text;
	}

}
