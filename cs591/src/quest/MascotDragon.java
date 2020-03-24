package quest;

public class MascotDragon extends QuestMascot {

	MascotDragon(String name, double power, double luck, int canDamage) {
		super(name, power, luck, canDamage);
	}

	@Override
	protected void empower(Hero hero) {
		hero.setStrength(hero.getStrength() + hero.getStrength() * (getPower() / 100));
	}


	@Override
	protected double damage(Hero hero, double damage) {
		return damage + damage * (getPower() / 100);
	}
	public String image() {
		String image = 
				"                     ,\n" + 
				"                    (\\\\__\n" + 
				"                   C/`   `\\\n" + 
				"                  C|    <oo'--.\n" + 
				"                   C\\    ,___\"/\n" + 
				"                    C) \\___/ `\n" + 
				"                   C/    |-.\n" + 
				"                  C/    '-. \\\n" + 
				"   .-=-.         C|   \\__  \\,)\n" + 
				"     `) )       C/       \\,,)\n" + 
				"    .' /       C' _    .==|\n" + 
				"   (  (   _.-'`    \\  .==/\n" + 
				"    \\  `'`     \\   | .==/\\__\n" + 
				"     `-.,,-'``-/  /__=;`   /\n" + 
				"              (    `\\  \\_.'\n" + 
				"               `\"\"\"\"\"`s";
		return (GREEN + image + RESET);
	}
}
