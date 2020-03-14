package quest;

import java.util.List;

public class Market implements FilesInfoInterface {

	/*
	 * Show items Buy? y/n (exit if no) Create Shopping bag Choose items Add items
	 * to bag, check if enough money Finalize shopping? Checkout
	 */
	public static void showcase() {
		CSVFilesHandler.displayMultipleFiles(marketFilenames);
	}

	public static Ammunition chooseItem() {

		System.out.println("Choose Ammunition type (A - armor, W - weapon, S - spell, P - potion) "
				+ "and index # of specified ammunition. For example, to choose Gaerdal_Ironhand type 'W1'.");
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
			amm = new Weapon(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2], ammunitionDataInt[3]);
		}
		if (ammunitionType == 'P') {
			amm = new Potion(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2]);
		}
		if (ammunitionType == 'I') {
			amm = new IceSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2], ammunitionDataInt[3]);
		}
		if (ammunitionType == 'F') {
			amm = new FireSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2], ammunitionDataInt[3]);
		}
		if (ammunitionType == 'L') {
			amm = new LightningSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2], ammunitionDataInt[3]);
		}
		return amm;
	}

	private static  String getMarketFilename(char c) {
		String filename = "";
		if (c == 'A') {
			filename = "csv/Armory.csv";
		}
		if (c == 'W') {
			filename = "csv/Weaponry.csv";
		}
		if (c == 'S') {
			System.out.println("Which spells do you want to purchase? (I - Ice, L - Lightning, F - Fire) case sensitive!");
			char spell = InputHandler.getCharacter(new char[] {'I','L','F'});
			if(spell == 'I') {
				filename = "csv/IceSpells.csv";
			}
			if(spell == 'L') {
				filename = "csv/LightningSpells.csv";
			}
			if(spell == 'F') {
				filename = "csv/FireSpells.csv";
			}
		}
		if (c == 'P') {
			filename = "csv/Potions.csv";
		}
		return filename;
	}
}
