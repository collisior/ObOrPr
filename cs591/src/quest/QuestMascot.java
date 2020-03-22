package quest;

public abstract class QuestMascot implements Color {
	private String name;
	private double power, luck; // max = 100
	private int canDamage = 0; //if 0 can't damage
	
	QuestMascot(String name, double power, double luck, int canDamage) {
		this.name = name;
		this.power = power;
		this.setLuck(luck);
		this.canDamage = canDamage;
	}

	public String displayDetails() {
		System.out.println("Name: " + name);
		System.out.println("Power: " + power);
		System.out.println("Can Damage: " + canDamage);
		return null;
	}
	/*
	 * enhance Hero's skills depending on Mascot type
	 */
	protected abstract void empower(Hero hero);
	/*
	 * enhance Hero's damage on Monster
	 */
	protected abstract double damage(Hero hero, double damage);

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public String toString() {
		return name;
	}

	public boolean canDamage() {
		if(canDamage ==0 ) {
			return false;
		}
		return true;
	}

	public void setCanDamage(int canDamage) {
		this.canDamage = canDamage;
	}
	
	public abstract void image();

	public double getLuck() {
		return luck;
	}

	public void setLuck(double luck) {
		this.luck = luck;
	}
}
