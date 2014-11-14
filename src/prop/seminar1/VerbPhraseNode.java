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
	
	public INode getLeftChild() {
		
		return leftChild;
	}
	
	public INode getRightChild() {
		
		return rightChild;
	}
	
	public void setText(String text) {
		
		this.text = text;
	}
        public void bind(INode node){
	    if(rightChild != null)
		rightChild = node;
	    else if(leftChild != null)
		leftChild = node;
	    else
		throw new ParserException("Node already has two children");
        }
}