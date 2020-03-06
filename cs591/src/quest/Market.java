package quest;

import java.util.List;

public class Market {
	
	/*
	 * Show items
	 * Buy? y/n (exit if no)
	 * Create Shopping bag
	 * Choose items
	 * Add items to bag, check if enough money
	 * Finalize shopping?
	 * Checkout
	 */
	public void showcase() {
		int counter = 0;
		for (List<String> list : CSVReader.items) {
			System.out.print(String.format("%-4s", counter));
			System.out.println(String.format("%-20s%-12s%-12s%-12s%-12s", list.get(0), list.get(1), list.get(2),
					list.get(3), list.get(4), list.get(5)));
			counter++;
		}
		
	}
}
