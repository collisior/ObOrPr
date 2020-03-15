package quest;

public class Potion extends Ammunition {
	private double attribute_increase;
	
	public Potion(String name, double cost, int required_level, double attribute_increase) {
		super(name, cost, required_level);
		setAttributeIncrease(attribute_increase);
		// TODO Auto-generated constructor stub
	}

	public double getAttributeIncrease() {
		return attribute_increase;
	}

	public void setAttributeIncrease(double attribute_increase) {
		this.attribute_increase = attribute_increase;
	}

	@Override
	protected double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void setDamage(double damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void applyExtraDamage(Monster monster) {
		// TODO Auto-generated method stub
		
	}

}
