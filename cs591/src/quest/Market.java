package quest;

public class Market implements FilesInfoInterface {

	/*
	 * Show items Buy? y/n (exit if no) Create Shopping bag Choose items Add items
	 * to bag, check if enough money Finalize shopping? Checkout
	 */
	public static void showcase() {
		CSVFilesHandler.displayMultipleFiles(marketFilenames);
	}

	public static Ammunition chooseItem() {

		System.out.println("Choose Ammunition type (A - Armor, W - Weapon, P - Potion, I - Ice Spell, etc.) "
				+ "and index # of specified ammunition. \ne.g. to buy Platinum_Shield type 'A1'.");
		boolean inputIsValid = false;
		int num = 0;
		String[] ammunitionData = null;
		char ammunitionType = 0;
		while (inputIsValid == false) {
			String input = InputHandler.getString();
			input = input.replace(" ", "");
			char[] clist = input.toCharArray();
			ammunitionType = clist[0];
			if (input.length() == 2) {
				if (Character.isLetter(ammunitionType) && (!getMarketFilename(ammunitionType).isEmpty())) {
					String filename = getMarketFilename(clist[0]);
					if (Character.isDigit(input.charAt(1))) {
						int max = CSVFilesHandler.map.get(filename);
						num = Integer.parseInt("" + clist[1]);
						if ((num >= 1) && (num <= max)) {
							ammunitionData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, num);
							inputIsValid = true;
						} else {
							System.out.println("Invalid input. Number is out of range.");
						}
					}

				} else {
					System.out.println("Invalid input, must be one letter followed by one integer. Try again. ");
				}
			}
		}
		return retrieveAmmunition(ammunitionData, ammunitionType);
	}

	private static Ammunition retrieveAmmunition(String[] ammunitionData, char ammunitionType) {
		Ammunition amm = null;
		int[] ammunitionDataInt = CSVFilesHandler.convertListStringToInteger(ammunitionData);
		if (ammunitionType == 'A') {
			amm = new Armor(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2]);
		}
		if (ammunitionType == 'W') {
			amm = new Weapon(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2],
					ammunitionDataInt[3]);
		}
		if (ammunitionType == 'P') {
			amm = new Potion(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2]);
		}
		if (ammunitionType == 'I') {
			amm = new IceSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2],
					ammunitionDataInt[3]);
		}
		if (ammunitionType == 'F') {
			amm = new FireSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2],
					ammunitionDataInt[3]);
		}
		if (ammunitionType == 'L') {
			amm = new LightningSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1],
					ammunitionDataInt[2], ammunitionDataInt[3]);
		}
		return amm;
	}

	private static String getMarketFilename(char c) {
		String filename = "";
		if (c == 'A') {
			filename = "csv/Armory.csv";
		}
		if (c == 'W') {
			filename = "csv/Weaponry.csv";
		}
		if (c == 'P') {
			filename = "csv/Potions.csv";
		}
		if (c == 'I') {
			filename = "csv/IceSpells.csv";
		}
		if (c == 'L') {
			filename = "csv/LightningSpells.csv";
		}
		if (c == 'F') {
			filename = "csv/FireSpells.csv";
		}
		return filename;
	}

	public static void purchaseFromMarket(QuestCharacter questCharacter) {
		showcase();
		Hero hero = (Hero) questCharacter;
		double budget = hero.getMoney();
		int max_level = hero.getLevel();
		Ammunition item = chooseItem();
		if ((item.cost <= budget) && (item.required_level <= max_level)) {
			hero.setMoney(budget - item.cost);
			hero.getStorage().add(item);
		} else if ((item.cost > budget) && (item.required_level <= max_level)) {
			System.out.println("You can't afford this Ammuntion: " + item.name);
		} else if ((item.required_level > max_level)) {
			System.out.println("You can't purchase this Ammunition, higher level required!");
		}
		System.out.println("Do you want to continue shopping?");
		if (InputHandler.YesOrNo()) {
			purchaseFromMarket(questCharacter);
		}
	}

	public static void sellToMarket(QuestCharacter questCharacter) {
		Hero hero = (Hero) questCharacter;
		if (hero.getStorage().size() <= 0) {
			System.out.println("Your storage is empty! No items to sell.");
		} else {
			hero.getStorage().showStorage();
			System.out.println("\nWhich item do you want to sell? Type index # of item to sell.");
			int index = InputHandler.getInteger(0, hero.getStorage().size() - 1);
			Ammunition item = hero.getStorage().get(index);
			hero.setMoney(hero.getMoney() + item.cost / 2);
			hero.getStorage().remove(index);
			System.out.println(" >>> after Storage size = " + hero.getStorage().size());
			System.out.println("You sold " + item + " for $" + item.cost / 2);

		}
		System.out.println("\n Do you want to continue selling?");
		if (InputHandler.YesOrNo()) {
			sellToMarket(questCharacter);
		}
		
	}
}
