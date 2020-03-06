package quest;

/*
 * The Game class models the piece for the game that can be placed on board,
 *  or distributed between players/teams.
 */
public  abstract class Piece implements Comparable<Piece>, Color {

	private char figure = ' ';
	private int x = -1, y = -1;
	private int prev_x = -1, prev_y = -1;
	private String color = WHITE;
	

	public Piece(char figure) {
		setPieceFigure(figure);
	}

	public Piece(int x, int y) {
		setXY(x, y);
	}
	/*
	 *  Set this Piece's figure ('image')
	 */
	public void setPieceFigure(char mark) {
		this.figure = mark;
	}
	/*
	 *  Return this Piece's figure ('image')
	 */
	public char getPieceFigure() {
		return this.figure;
	}
	
	/*
	 *  Return current x coordinate of this piece
	 */
	public int getX() {
		return this.x;
	}
	/*
	 *  Return current y coordinate of this piece
	 */
	public int getY() {
		return this.y;
	}
	/*
	 *  Set current c coordinate of this piece
	 */
	public void setX(int x) {
		this.x = x;
	}
	/*
	 *  Set current y coordinate of this piece
	 */
	public void setY(int y) {
		this.y = y;
	}
	/*
	 *  Set current x,y coordinates of this piece
	 */
	public void setXY(int x, int y) {
		setX(x);
		setY(y);
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return this.color;
	}
	/*
	 *  Update coordinates and save previous cell position.
	 */
	public void updateCellPosition(int x, int y) {
		this.prev_x = this.x;
		this.prev_y = this.y;
		setX(x);
		setY(y);
	}
	/*
	 *  Return previous x coordinate of this piece.
	 */
	public int getPrevX() {
		return this.prev_x;
	}
	/*
	 *  Return previous y coordinate of this piece.
	 */
	public int getPrevY() {
		return this.prev_y;
	}

	public String toString() {
		return this.color+ this.figure + ANSI_RESET;
	}
    public boolean equals(Piece otherPiece) {       
        if(this.getPieceFigure() == otherPiece.getPieceFigure()){
            return true;
        }
        return false;
    }
 }
