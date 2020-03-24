package quest;

public class Fists extends Ammunition {

	public Fists() {
		super("Fists", 0, 0);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canSell() {
		return false;
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

	@Override
	protected String image() {
		String image = 
				"                        _    ,-,    _\n" + 
				"                 ,--, /: :\\/': :`\\/: :\\\n" + 
				"                |`;  ' `,'   `.;    `: |\n" + 
				"                |    |     |  '  |     |.\n" + 
				"                | :  |     | pb  |     ||\n" + 
				"                | :. |  :  |  :  |  :  | \\\n" + 
				"                 \\__/: :.. : :.. | :.. |  )\n" + 
				"                      `---',\\___/,\\___/ /'\n" + 
				"                           `==._ .. . /'\n" + 
				"                                `-::-'\n\n";
		return image;
	}

}
