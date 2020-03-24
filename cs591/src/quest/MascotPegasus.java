package quest;

public class MascotPegasus extends QuestMascot {

	MascotPegasus(String name, double power, double luck, int canDamage) {
		super(name, power, luck, canDamage);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void empower(Hero hero) {
		hero.setStrength(hero.getStrength() + hero.getStrength() * (getPower() / 100));
	}


	@Override
	protected double damage(Hero hero, double damage) {
		return damage + damage * (getPower() / 100);
	}
	
	@Override
	public String image() {
		String image = 
				"\n" + 
				"                        ,\n" + 
				"                       .''.         _.'.\n" + 
				"              (((,    /    \\    _.-'    \\\n" + 
				"             (\\)))/),'      `_.'  _.-'=. `\\\n" + 
				"             / (`  \\(((,    `'-.  \\)=.  =. `.\n" + 
				"             |a  a )  )))),     `\\ \\) =.   =.'-._\n" + 
				"             |    /::   ((((, .  | |)=.  =.   =. `'-._,\n" + 
				"             |   / \\:   ')))) /\\_/ |) =./`'-.,_ ='  _,/\n" + 
				"             \\\"_/   )     ((((  _.:' .-'       `\"\"\"`\n" + 
				"                    /      ))))/::: (\"\"```\"-.__\n" + 
				"                   ;  :.   ((( ::''          \\))),\n" + 
				"                   |  :'   ))  '          ':. |(((\n" + 
				"                   ;                /         |))))\n" + 
				"                    \\       /       |         ;((((\n" + 
				"                     `\\   /`---\"\"\"\"`Y        /  )))\n" + 
				"                      / /`|          \\`.   .'  (((\n" + 
				"                     / /| |           `\\\\  <    ))\n" + 
				"                     | || |             \\\\  \\\n" + 
				"                     | `\\ |              \\\\  \\\n" + 
				"                      \\/| |              // /\n" + 
				"                       \\/_(             //_/\n" + 
				"                       /__/            //__(\n" + 
				"";
		return (WHITE + image + RESET);
	}

}
