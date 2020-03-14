package quest;

public abstract class QuestCharacter {
	protected String name;
	protected int hp, level;
	
	QuestCharacter(String name, int hp, int level) {
		setName(name);
		setHp(hp);
		setLevel(level);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String toString() {
		return name;
	}

	protected abstract void sellFromStorage();
}
