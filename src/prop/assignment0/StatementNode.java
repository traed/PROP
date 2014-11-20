package prop.assignment0;

public class StatementNode implements INode {

	private INode child1, child2;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		// TODO Auto-generated method stub
		
	}

	public void addChild(INode child) {
		if(child1 == null)
			child1 = child;
		else if(child2 == null)
			child2 = child;
	}
}
