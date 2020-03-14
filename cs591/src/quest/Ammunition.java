package quest;

public abstract class Ammunition {
	String name = null;
	int cost = 0; 
	int required_level = 0;
	
	public Ammunition(String name, int cost, int required_level) {
		this.name = name;
		this.cost = cost; 
		this.required_level = required_level;
	}

	public String toString() {
		return name + " " + cost;
	}
}
