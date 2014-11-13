package prop.seminar1;

import java.util.Arrays;

public class TextNode implements INode {
	
	INode parent;
	INode child1;
	INode child2;
	
	public TextNode(INode parent) {
		this.parent = parent;
		this.child1 = child1;
		this.child2 = child2;
		
	}

	public void setChild1(INode child) {
		this.child1 = child;
		
	}
	
	public void setChild2(INode child) {
		this.child2 = child;
	}
	
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		int copyTaken = 0;
		int copyCopyTaken = 0;
		for(int i = 0; i < args.length; i++) {
			Lexeme l = (Lexeme)args[i];
			if(l.token() == Token.EOS) {
				SentenceNode sNode = new SentenceNode(this);
				copyCopyTaken = copyTaken;
				copyTaken = i;
				setChild1(sNode);
				return sNode.evaluate(Arrays.copyOfRange(args, copyCopyTaken, i));
			}
				
		}
		
		
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		
	}

}
