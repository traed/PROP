package prop.assignment0;

public class StatementsNode implements INode {

	private INode child1, child2;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(child1 != null) {
			Statement statement = (Statement)child1.evaluate(args);
			StringBuilder sb = new StringBuilder();
			sb.append("" + statement.getIdentifier().value() + " = " + statement.getValue() + "\n");
			
			for(int i = 0; i < args.length; i++) {
				
				if(args[i] == null) {
					args[i] = statement;

					break;
				}
			}
			
			if(child2 != null) {
				Object o = child2.evaluate(args);
				
				if(o != null)
					return sb.append(o);
			}

			return sb.toString();

		}

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
