package quest;
/*
 * Source: https://www.geeksforgeeks.org/check-possible-path-2d-matrix/
 */
public class GFG {
	/*
	 * to find the path from top left to bottom right
	 */
	static boolean isPath(int arr[][]) {
		arr[0][0] = 1; //start

		// Mark reachable (from top left) nodes
		// in first row and first column.
		for (int i = 1; i < arr[0].length; i++)
			if (arr[0][i] != -1)
				arr[0][i] = arr[0][i - 1];
		for (int j = 1; j < arr.length; j++)
			if (arr[j][0] != -1)
				arr[j][0] = arr[j - 1][0];

		// Mark reachable nodes in
		// remaining matrix.
		for (int i = 1; i < arr.length; i++)
			for (int j = 1; j < arr[0].length; j++)
				if (arr[i][j] != -1)
					arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);

		// return yes if right
		// bottom index is 1
		return (arr[arr.length - 1][arr[0].length - 1] == 1);
	}

	// Testing
	public static void main(String[] args) {
		QuestBoard board = new QuestBoard(8,9);
		board.displayBoard();
//		DisplayBoard.show(board);
		if (board.validPathExist() )
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}