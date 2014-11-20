package prop.assignment0;

public class StatementsNode implements INode {

	private INode child1, child2;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		builder.append("StatementsNode\n");
		tabs++;
		if(child1 != null)
			child1.buildString(builder, tabs);
		if(child2 != null)
			child2.buildString(builder, tabs);
		
	}

	public void addChild(INode child) {
		if(child1 == null)
			child1 = child;
		else if(child2 == null)
			child2 = child;
	}
}