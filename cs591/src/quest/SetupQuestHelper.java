package quest;

import java.util.ArrayList;

public class SetupQuestHelper implements FilesInfoInterface, Color {

	public static void setupTeam(Team team) {
		for (Player player : team.getTeam()) {

			Hero hero = (Hero) chooseQuestHero(player);
			player.setHero(hero);
			player.setTeamId(team.getId());
			System.out.println(team.color + player + " your Hero: " + player.getHero() + RESET);
		}
	}

	static QuestCharacter chooseQuestHero(Player player) {
		char[] clist = null;
		if (player.getHeroChosen() != null) {
			System.out.println("\n" + player + ": do you want to choose new Hero? "
					+ "\n (if no, your Hero from previous quest will reset.");
			if (InputHandler.YesOrNo()) {
				clist = null;
			} else {
				clist = player.getHeroChosen();
			}
		}
		if (clist == null) {
			CSVFilesHandler.displayMultipleFiles(heroesFilenames);
			System.out.println("\n" + GREEN + player + RESET
					+ ", choose Quest character by entering Hero type (W - warrior, P - paladin, S - sorcerer) \n"
					+ "and index # of specified hero. For example, to choose Gaerdal_Ironhand (Warrior, index = 1) type 'W1'.");
			clist = chooseNewHero(player);
		}

		String filename = getHeroFilename(clist[0]);
		int num = Integer.parseInt("" + clist[1]);
		String[] heroData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, num);
		player.setHeroChosen(clist);
		return produceHero(heroData, clist[0]);
	}

	static char[] chooseNewHero(Player player) {
		char[] clist = null;
		int num = 0;
		boolean inputIsValid = false;
		char heroType = 0;
		while (inputIsValid == false) {
			String input = InputHandler.getString();
			input = input.replace(" ", "");
			clist = input.toCharArray();
			heroType = clist[0];
			if (input.length() == 2) {
				if (Character.isLetter(heroType) && (!getHeroFilename(heroType).isEmpty())) {
					String filename = getHeroFilename(clist[0]);
					if (Character.isDigit(input.charAt(1))) {

						int max = CSVFilesHandler.map.get(filename);
						num = Integer.parseInt("" + clist[1]);
						if ((num >= 1) && (num <= max)) {
							player.setHeroChosen(clist);
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
		return clist;
	}

	static QuestMascot chooseMascot(Hero hero) {
		System.out.println("\nCongratulations! You receive mascot that will be protect you and fight by your side.");
		String[] mascotData = null;
		String filename = "csv/Mascots.csv";
		CSVFilesHandler.displayMultipleFiles(mascotsFilenames);
		int max = CSVFilesHandler.map.get(filename);
		int index = InputHandler.getInteger(1, max);
		mascotData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, index);
		return produceMascot(mascotData);
	}

	public static QuestCharacter produceHero(String[] heroData, char heroType) {
		Hero hero = null;
		int[] heroDataInt = CSVFilesHandler.convertListStringToInteger(heroData);
		if (heroType == 'W') {
			hero = new Warrior(heroData[0], heroDataInt[0], heroDataInt[1], heroDataInt[2], heroDataInt[3],
					heroDataInt[4], heroDataInt[5]);
		}
		if (heroType == 'P') {
			hero = new Paladin(heroData[0], heroDataInt[0], heroDataInt[1], heroDataInt[2], heroDataInt[3],
					heroDataInt[4], heroDataInt[5]);
		}
		if (heroType == 'S') {
			hero = new Sorcerer(heroData[0], heroDataInt[0], heroDataInt[1], heroDataInt[2], heroDataInt[3],
					heroDataInt[4], heroDataInt[5]);
		}
		return hero;
	}

	public static QuestMascot produceMascot(String[] mascotData) {
		if (mascotData == null) {
			return null;
		}
		int[] mascotDataInt = CSVFilesHandler.convertListStringToInteger(mascotData);
		QuestMascot mascot = null;
		if (mascotData[0].equals("Unicorn")) {
			mascot = new MascotUnicorn(mascotData[0], mascotDataInt[0], mascotDataInt[1], mascotDataInt[2]);
		}
		if (mascotData[0].equals("Dragon")) {
			mascot = new MascotDragon(mascotData[0], mascotDataInt[0], mascotDataInt[1], mascotDataInt[2]);
		}
		if (mascotData[0].equals("Gryphon")) {
			mascot = new MascotGryphon(mascotData[0], mascotDataInt[0], mascotDataInt[1], mascotDataInt[2]);
		}
		if (mascotData[0].equals("Pegasus")) {
			mascot = new MascotPegasus(mascotData[0], mascotDataInt[0], mascotDataInt[1], mascotDataInt[2]);
		}
		return mascot;
	}

	private static String getHeroFilename(char c) {
		String filename = "";
		if (c == 'W') {
			filename = "csv/Warriors.csv";
		}
		if (c == 'P') {
			filename = "csv/Paladins.csv";
		}
		if (c == 'S') {
			filename = "csv/Sorcerers.csv";
		}
		return filename;
	}

	public static ArrayList<Monster> generateMonsters(Team team) {
		ArrayList<Monster> listMonsters = new ArrayList<Monster>();
		int highestHeroLevel = 0;
		for (Player player : team.getTeam()) {
			if (highestHeroLevel < player.getHero().getLevel()) {
				highestHeroLevel = player.getHero().getLevel();
			}
		}
		for (Player player : team.getTeam()) {
			Monster monster = null;
			while (monster == null) {
				monster = generateMonster((int) Math.round(highestHeroLevel));
			}
			listMonsters.add(monster);
		}
		return listMonsters;
	}

	private static Monster generateMonster(int highestHeroLevel) {
		String filename = getRandomMonsterFilename();
		String[] monsterData = getRandomMonsterFrom(filename, highestHeroLevel);
		if (monsterData == null) {
			return null;
		}
		int[] monsterDataInt = CSVFilesHandler.convertListStringToInteger(monsterData);
		Monster monster = null;
		if (filename.equals("csv/Dragons.csv")) {
			monster = new Dragon(monsterData[0], monsterDataInt[0], monsterDataInt[1], monsterDataInt[2],
					monsterDataInt[3]);
		}
		if (filename.equals("csv/Exoskele.csv")) {
			monster = new Exoskeleton(monsterData[0], monsterDataInt[0], monsterDataInt[1], monsterDataInt[2],
					monsterDataInt[3]);
		}
		if (filename.equals("csv/Spirits.csv")) {
			monster = new Spirit(monsterData[0], monsterDataInt[0], monsterDataInt[1], monsterDataInt[2],
					monsterDataInt[3]);
		}
		return monster;
	}

	private static String[] getRandomMonsterFrom(String filename, int highestHeroLevel) {
		ArrayList<Integer> listIndexes = getValidLevelMonstersFrom(filename, highestHeroLevel);
		if (listIndexes.isEmpty()) {
			return null;
		}
		int r = listIndexes.size() - 1;
		int randomIndex = listIndexes.get(r);
		String[] monsterData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, randomIndex);

		return monsterData;
	}

	private static ArrayList<Integer> getValidLevelMonstersFrom(String filename, int highestHeroLevel) {
		ArrayList<Integer> listIndexes = new ArrayList<Integer>();

		for (int i = 1; i < CSVFilesHandler.map.get(filename); i++) {
			String[] s = CSVFilesHandler.retrieveDataFromFileByIndex(filename, i);
			int level = Integer.parseInt(s[1]);
			if (level == highestHeroLevel) {
				listIndexes.add(i);
			}
		}
		return listIndexes;
	}

	private static String getRandomMonsterFilename() {
		int randomNum = Quest.random.nextInt(3);
		String filename = null;
		if (randomNum == 0) {
			filename = "csv/Dragons.csv";
		}
		if (randomNum == 1) {
			filename = "csv/Exoskeletons.csv";
		}
		if (randomNum == 2) {
			filename = "csv/Spirits.csv";
		}
		return filename;
	}

}