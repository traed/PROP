package prop.seminar1;

import java.util.Arrays;

public class TextNode implements INode {

	private String text = "Childs are null";

	private INode leftChild;
	private INode rightChild;

	@Override
	public Object evaluate(Object[] args) throws Exception {


		return this;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("   ");
		builder.append("Text" + "\n");
		tabs++;
	    if(leftChild != null)
			leftChild.buildString(builder, tabs);
	    if(rightChild != null) {
			rightChild.buildString(builder, tabs);
	    }
	    if(leftChild == null && rightChild == null)
			builder.append(text);
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

	public String getText() {
		return text;
	}
	public void bind(INode node) {
		if(leftChild == null)
			leftChild = node;
		else if(rightChild == null)
			rightChild = node;
	}
}
