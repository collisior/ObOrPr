package tictactoe;

/*
 * The HumanInfo class models the person with all basic information related to any Human.
 */

public class HumanInfo {
	
	private String FirstName, LastName, Username;
	private int Age;
	/*
	 * Constructor to initialize this Human. 
	 */
	public HumanInfo() {
		askFirstName();
		setLastName("");
		setUsername("");
		setAge(0);
	}
	/*
	 * Constructor to initialize this Human (with given full name, age). 
	 */
	public HumanInfo(String fname, String lname, Integer age) {
		setFirstName(fname);
		setLastName(lname);
		setUsername("");
		setAge(age);
	}
	/*
	 * Set this Human's name.
	 */
	public void setFirstName(String fname) {
		this.FirstName = fname;
	}
	/*
	 * Set this Human's last name.
	 */
	public void setLastName(String lname) {
		this.LastName = lname;
	}
	/*
	 * Set this Human's username.
	 */
	public void setUsername(String uname) {
		this.Username = uname;
	}
	/*
	 * Set this Human's age.
	 */
	public void setAge(int age) {
		this.Age = age;
	}
	/*
	 * Return this Human's name.
	 */
	public String getFirstName() {
		return this.FirstName;
	}
	/*
	 * Return this Human's last name.
	 */
	public String getLastName() {
		return this.LastName;
	}
	/*
	 * Return this Human's username.
	 */
	public String getUsername() {
		return this.Username;
	}
	/*
	 * Return this Human's age.
	 */
	public Integer getAge() {
		return this.Age;
	}
	/*
	 * Return true if this Human's of legal age.
	 */
	public boolean isLegalAge(int legal) {
		if (this.Age >= legal) {
			return true;
		} 
		return false;
	}
	/*
	 * Ask and set this Human's first name .
	 */
	public void askFirstName() {
		System.out.printf("\nWhat's your first name? ");
		setFirstName(InputHandler.getString());
	}
	/*
	 * Ask and set this Human's last name.
	 */
	public void askLastName() {
		System.out.print("\nWhat's your last name?");
		setLastName(InputHandler.getString());
	}
	/*
	 * Ask and set this Human's username.
	 */
	public void askUsername() {
		System.out.print("\nPreferred nickname? ");
		setUsername(InputHandler.getString());
	}
	/*
	 * Ask and set this Human's age
	 */
	public void askAge() {
		System.out.print("\nHow old are you? ");
		setAge(InputHandler.getInteger(0, 100));
	}
	
	public String toString() {
		return getFirstName() + " " + getLastName() + " (" + getUsername() + ")";
	}
}
