package quest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFilesHandler implements FilesInfoInterface {

	public static List<List<String>> heroes = new ArrayList<List<String>>(), monsters = new ArrayList<List<String>>(),
			marketItems = new ArrayList<List<String>>();
	public static Map<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) {
		/*
		 * Heroes: Warriors, Paladins, Sorcerers Monsters: Exoskeletons, Dragons,Spirits
		 * MArket: Armory, Weaponry, Potions, IceSpells, FireSpells, LightningSpells
		 */

		setData();
		displayMultipleFiles(heroesFilenames);
		System.out.println();
		System.out.println();
	}

	public static void setData() {
		putInMap(heroesFilenames);
		putInMap(monstersFilenames);
		putInMap(marketFilenames);
	}

	/*
	 * Put filename [Key] and its number [value] of file's items in map
	 */
	private static void putInMap(String[] filenames) {
		int counter = 0;
		String line = "", cvsSplitBy = ",";
		for (String filename : filenames) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				while ((line = br.readLine()) != null) {
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

	/*
	 * Display all data of given individual file
	 */
	private static void displayDataFromFile(String filename) {
		int counter = 0;
		String line = "", cvsSplitBy = ",";
		String f = filename.substring(4, filename.length() - 4);
		System.out.println("" + f);
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);

				if (data[0].equals("Name")) {
					System.out.print(String.format("%-4s", "#"));

				} else {
					System.out.print(String.format("%-4s", counter));
				}
				String format = "%-20s";
				int minicounter = 0;
				for (String d : data) {
					System.out.print(String.format(format, d));
					if (minicounter == 0) {
						format = "%-18s";
					}
				}
				counter++;
				System.out.println();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Display all data of given list of files
	 */
	public static void displayMultipleFiles(String[] filenames) {
		for (String f : filenames) {
			displayDataFromFile(f);
		}
	}

	/*
	 * Retrieve data from file of a given row index
	 */
	public static String[] retrieveDataFromFileByIndex(String filename, int index) {
		int counter = 0;
		String line = "", cvsSplitBy = ",";
		String[] data = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			while ((line = br.readLine()) != null) {
				if (index == counter) {
					data = line.split(cvsSplitBy);
					break;
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/*
	 * Convert String list into list of integers (if convertible) 
	 */
	public static int[] convertListStringToInteger(String[] data) {
		int counter = 0;
		int[] dataInt = new int[data.length - 1];
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

}
