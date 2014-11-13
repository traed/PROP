package prop.seminar1;

public class RootNode implements INode {

	@Override
	public Object evaluate(Object[] args) throws Exception {
		INode node = new TextNode();
		
		return node.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

		
	}

}
