package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

	/*
	 * Handles 1 integer inputs
	 */
	public static int getInteger() {
		boolean inputIsValid = false;
		int num = 0;
		while (inputIsValid == false) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter here (integer): ");
			try {
				String s = scanner.nextLine().strip();
				num = Integer.parseInt(s);
				inputIsValid = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again. ");
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input. Try again. ");
			}
		}
		return num;
	}

	/*
	 * Handles 1 integer inputs within range [min, max]
	 */
	public static int getInteger(int min, int max) {
		boolean inputIsValid = false;
		int num = 0;
		while (inputIsValid == false) {
			try {
				num = getInteger();
				if ((num >= min) && (num <= max)) {
					inputIsValid = true;

				} else {
					System.out.printf("Input is out of range [%d, %d]. Try again.\n", min, max);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again. ");
			}
		}
		return num;
	}

	/*
	 * Retrieve player's yes/no answer. Returns true if 'Yes'
	 */
	public static boolean YesOrNo() {
		boolean playerInputIsValid = false;
		boolean answer = false;
		System.out.println("\nYes or No (y/n)?  ");
		while (playerInputIsValid == false) {
			try {
				char input = getCharacter();
				if (input == 'y' || input == 'Y') {
					answer = playerInputIsValid = true;
				} else if (input == 'n' || input == 'N') {
					playerInputIsValid = true;
					answer = false;
				} else {
					System.out.print("\nInput must be (y/n). ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again. ");
			}
		}
		return answer;
	}

	/*
	 * Returns 1 character input
	 */
	public static char getCharacter() {
		boolean inputIsValid = false;
		char c = 0;
		while (inputIsValid == false) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter here (character): ");
			try {
				String s = scanner.nextLine().replaceAll("\\s+","");
				if (s.length() == 1) {
					c = s.charAt(0);
					inputIsValid = true;
				} else {
					System.out.println("Too many characters! Type only 1 character!");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again. ");
			}
		}
		return c;
	}

	/*
	 * Returns Character input if exists in given character list
	 */
	public static char getCharacter(char[] list) {
		boolean inputIsValid = false;
		char c = 0;
		while (inputIsValid == false) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter here (character): ");
			try {
				c = scanner.next().charAt(0);
				for (char i : list) {
					if (c == i) {
						inputIsValid = true;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again. ");
			}
		}
		return c;
	}

	/*
	 * Retrieve 2 integers, and handle invalid inputs until valid input is typed.
	 */
	@SuppressWarnings("resource")
	public static int[] getTwoIntegers() {

		int[] nn = new int[] { -1, -1 };
		boolean playerInputIsValid = false;

		while (playerInputIsValid == false) {
			Scanner scanner = new Scanner(System.in).useDelimiter("[,\\s+]");
			System.out.print("Enter two integers (separated by comma or whitespace): ");
			try {

				nn[0] = scanner.nextInt();
				nn[1] = scanner.nextInt();
				playerInputIsValid = true;

			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again. ");
			}
		}
		return nn;
	}

	/*
	 * Retrieve and return string input from user
	 */
	public static String getString() {
		String input = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter here: ");
		input = scanner.next();
		return input;
	}
}
