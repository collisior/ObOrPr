package quest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
	static String line = "";
	static String cvsSplitBy = ",";
	public static List<List<String>> heroes = new ArrayList<List<String>>(), monsters = new ArrayList<List<String>>(),
			marketItems = new ArrayList<List<String>>();
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static String[] heroesFilenames = {"csv/Warriors.csv", "csv/Paladins.csv","csv/Sorcerers.csv"};
	public static String[] monstersFilenames = {"csv/Exoskeletons.csv","csv/Dragons.csv","csv/Spirits.csv"};
	public static String[] marketFilenames = {"csv/Armory.csv", "csv/Potions.csv","csv/Weaponry.csv","csv/IceSpells.csv","csv/FireSpells.csv","csv/LightningSpells.csv" };
	
	public static void main(String[] args) {
		/*
		 * Heroes: Warriors, Paladins, Sorcerers Monsters: Exoskeletons, Dragons,Spirits
		 * MArket: Armory, Weaponry, Potions, IceSpells, FireSpells, LightningSpells
		 */
		
		setData();
		displayAllMonstersData();
		displayAllWeapons();
		System.out.println();
		System.out.println();
		
		System.out.println();
		System.out.println();
		System.out.println(" " + map.get("csv/Potions.csv"));
		

	}
	public static void setData() {
		putInMap(heroesFilenames);
		putInMap(monstersFilenames);
		putInMap(marketFilenames);
		
		setDataHeroes();
		setDataMonsters();
		setDataMarket();
	}
	/*
	 * Setup map with name of a file and its number of content
	 */
	private static void putInMap(String[] filenames) {
		int counter = 0;
		for(String filename: filenames) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				while ((line = br.readLine()) != null) {
					// use comma as separator
					String[] data = line.split(cvsSplitBy);
					if (data[0].equals("Name")) {
						// do nothing
					} else {
						counter++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			map.put(filename, counter);
			counter = 0;
		}
	}
	
	private static void displayDataFromFile(String filename) {
		int counter = 0;
		List<String> list;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				
				if (data[0].equals("Name")) {
				} else {
					list = (Arrays.asList(data));
					counter++;
				}
				
				System.out.print(String.format("%-4s", counter));
				System.out.println(String.format("%-20s%-12s%-12s%-12s%-12s", list.get(0), list.get(1), list.get(2),
						list.get(3), list.get(4)));
				counter++;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void setDataHeroes() {
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("csv/Warriors.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (data[0].equals("Name")) {
				} else {
					data[6] = "Warrior";
					heroes.add(Arrays.asList(data));
					counter++;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader("csv/Paladins.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (data[0].equals("Name")) {

				} else {
					data[6] = "Paladin";
					heroes.add(Arrays.asList(data));
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader("csv/Sorcerers.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (data[0].equals("Name")) {
				} else {
					data[6] = "Sorcerer";
					heroes.add(Arrays.asList(data));
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayAllHeroesData() {
		System.out.println(
				"#   Name                Mana        Strength    Agility     Dexterity   Starting money      Hero Type");
		int counter = 0;
		for (List<String> list : heroes) {
			System.out.print(String.format("%-4s", counter));
			System.out.println(String.format("%-20s%-12s%-12s%-12s%-12s%-20s%-15s", list.get(0), list.get(1),
					list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)));
			counter++;
		}
	}

	public static void displayAllMonstersData() {
		System.out.println("#   Name                level       damage      defense     dodge_chance");
		int counter = 0;
		for (List<String> list : monsters) {
			System.out.print(String.format("%-4s", counter));
			System.out.println(String.format("%-20s%-12s%-12s%-12s%-12s", list.get(0), list.get(1), list.get(2),
					list.get(3), list.get(4)));
			counter++;
		}
	}
	
	public static void displayAllWeapons() {
		int counter = 0;
		System.out.println("#   Name                Cost       required level      Damage     Required Hands");
		for (List<String> list : marketItems) {
			if(list.equals(new String("csv/Weaponry.csv"))) {
				System.out.print(String.format("%-4s", counter));
				System.out.println(String.format("%-20s%-12s%-12s%-12s", list.get(0), list.get(1), list.get(2),
						list.get(3)));
			}
			
			counter++;
		}
	}




	private static void setDataMonsters() {
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("csv/Exoskeletons.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (data[0].equals("Name")) {
				} else {
					monsters.add(Arrays.asList(data));
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader("csv/Dragons.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (data[0].equals("Name")) {

				} else {
					monsters.add(Arrays.asList(data));
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader("csv/Spirits.csv"))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				if (data[0].equals("Name")) {
				} else {

					monsters.add(Arrays.asList(data));
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setDataMarket() {
		System.out.println("     market  " );
		int counter = 0;
		for(String filename : marketFilenames) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				while ((line = br.readLine()) != null) {
					String[] data = line.split(cvsSplitBy);
					if (data[0].equals("Name")) {
					} else {
						marketItems.add(Arrays.asList(data));
						counter++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.print(" \n " + filename + " " + counter + "\n");
			counter = 0;
		}
		System.out.println("#   Name                level       level      defense");
		counter = 0;
		for (List<String> list : marketItems) {
			System.out.print(String.format("%-4s", counter));
			System.out.println(String.format("%-20s%-12s%-12s%-12s", list.get(0), list.get(1), list.get(2),
					list.get(3)));
			counter++;
		}
		
	}
	
	public static void csvMonster(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				System.out.println(
						String.format("%-20s%-12s%-12s%-12s%-12s", data[0], data[1], data[2], data[3], data[4]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void csvMarket(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				System.out.println(
						String.format("%-20s%-12s%-12s%-12s%-12s", data[0], data[1], data[2], data[3], data[4]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] retrieveData(String filename, int index) {
		int counter = 0;
		String[] data = null;

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				data = line.split(cvsSplitBy);
				if (index == counter) {
					break;
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static int[] convertListStringToInteger(List<String> data) {
		int counter = 0;
		int[] dataInt = new int[data.size() - 1];
		int num = 0;
		for (String s : data) {
			try {
				num = Integer.parseInt(s);
				dataInt[counter] = num;
				counter++;
			} catch (NumberFormatException e) {
			}
		}
		return dataInt;
	}

	public static Hero retrieveHero(int index) {
		Hero hero = null;
		List<String> data = heroes.get(index);
		int[] dataInt = convertListStringToInteger(data);
		// TODO: change this to adjustable Warriors, Paladins, Sorcerers
		if (data.get(6).equals("Warrior")) {
			hero = new Warrior(data.get(0), dataInt[0], dataInt[1], dataInt[2], dataInt[3], dataInt[4], dataInt[5]);
		}
		if (data.get(6).equals("Paladin")) {
			hero = new Paladin(data.get(0), dataInt[0], dataInt[1], dataInt[2], dataInt[3], dataInt[4], dataInt[5]);
		}
		if (data.get(6).equals("Sorcerer")) {
			hero = new Sorcerer(data.get(0), dataInt[0], dataInt[1], dataInt[2], dataInt[3], dataInt[4], dataInt[5]);
		}

		return hero;
	}

}
