package quest;

public class Sorcerer extends Hero {
//	Sorcerers are favored on the dexterity and the agility.
	Sorcerer(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
		super(name, mana, strength, agility, dexterity, money, experience);
	}

	public void upgradeSkills() {
		setStrength(getStrength() + getStrength() * 0.05);
		setAgility(getAgility() + getAgility() * (0.05 + 0.05));
		setDexterity(getDexterity() + getDexterity() * (0.05 + 0.05));
	}
	public String image() {
		String image = "                    ____\n" + 
				"                  .'* *.'\n" + 
				"               __/_*_*(_\n" + 
				"              / _______ \\\n" + 
				"             _\\_)/___\\(_/_\n" + 
				"            / _((\\- -/))_ \\\n" + 
				"            \\ \\())(-)(()/ /\n" + 
				"             ' \\(((()))/ '\n" + 
				"            / ' \\)).))/ ' \\\n" + 
				"           / _ \\ - | - /_  \\\n" + 
				"          (   ( .;''';. .'  )\n" + 
				"          _\\\"__ /    )\\ __\"/_\n" + 
				"            \\/  \\   ' /  \\/\n" + 
				"             .'  '...' ' )\n" + 
				"              / /  |  \\ \\\n" + 
				"             / .   .   . \\\n" + 
				"            /   .     .   \\\n" + 
				"           /   /   |   \\   \\\n" + 
				"         .'   /    b    '.  '.\n" + 
				"     _.-'    /     Bb     '-. '-._\n" + 
				" _.-'       |      BBb       '-.  '-.\n" + 
				"(________mrf\\____.dBBBb.________)____)\n";
		return (color + image + RESET);
	}
}