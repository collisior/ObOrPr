package quest;

import java.util.ArrayList;
import java.util.Random;

class GenericMethods {
	
	/*
	 * Generic version. Return shuffled version of given array list (e.g. players/teams).
	 */
	public static <T> ArrayList<T> shuffle(ArrayList<T> array) {
		Random random = new Random();
		int n = array.size();
		for (int i = 0; i < n - 1; i++) {
			int j = i + random.nextInt(n - i);
			T obj = array.get(i);
			array.set(i, array.get(j));
			array.set(j, obj);
		}
		return array;
	}

	public boolean pathExists(Board board) {
		int[][] temp = new int[board.rows][board.cols];
		
		return false;
		
	}
}
