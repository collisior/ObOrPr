package quest;

public abstract class Ammunition {
	String name = null;
	double cost = 0; 
	int required_level = 0;
	
	public Ammunition(String name, double cost, int required_level) {
		this.name = name;
		this.cost = cost; 
		this.required_level = required_level;
	}

	public String toString() {
		if (name == null) {
			return "Fists";
		}
		return name;
	}
	protected abstract double getDamage();
	
	protected abstract void setDamage(double damage);
		
	protected abstract void applyExtraDamage(Monster monster);
}
