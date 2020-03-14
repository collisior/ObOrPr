package quest;

public class Potion extends Ammunition {
	private int attribute_increase;
	
	public Potion(String name, int cost, int required_level, int attribute_increase) {
		super(name, cost, required_level);
		setAttributeIncrease(attribute_increase);
		// TODO Auto-generated constructor stub
	}

	public int getAttributeIncrease() {
		return attribute_increase;
	}

	public void setAttributeIncrease(int attribute_increase) {
		this.attribute_increase = attribute_increase;
	}

}
