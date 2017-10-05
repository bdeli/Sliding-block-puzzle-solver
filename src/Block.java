import java.util.*;
import java.awt.Point;

public class Block {

	/** The coordinates of a block in the board. */
	private Point upperLeft;
	private Point lowerRight;

	/** The default constructor for a Block.
	 * @param a, b, c, d The integer points of the 
	 * 			upper left and lower right corners of a block. */
	public Block (int a, int b, int c, int d){

		upperLeft = new Point(a,b);
		lowerRight = new Point(c,d);	
	}

	public String toString() {
		String rtn ="";
		rtn = rtn + upperLeft.x + " " + upperLeft.y + " " + lowerRight.x + " " + lowerRight.y;
		return rtn;		
	}

	/** Getter method for the upper left corner of a block. 
	 * @return Point the point representation of the corner of a block. */
	public Point getUpperLeft() {
		return this.upperLeft;	
	}

	/** Getter method for the lower right corner of a block. 
	 * @return Point the point representation of the corner of a block. */
	public Point getLowerRight() {
		return this.lowerRight;	
	}

	/** A method to modify the location of a block.
	 * @param a The point reference for the new position of the block. */
	public void moveUp(Point a){
		
		this.upperLeft.x = a.x;
		this.lowerRight.x = this.lowerRight.x-1;
		
	}

	/** A method to modify the location of a block.
	 * @param a The point reference for the new position of the block. */
	public void moveDown(Point a){
		
		this.upperLeft.x = a.x;
		this.lowerRight.x = this.lowerRight.x+1;
		
	}

	/** A method to modify the location of a block.
	 * @param a The point reference for the new position of the block. */
	public void moveRight(Point a){
		
		this.upperLeft.y = a.y;
		this.lowerRight.y = this.lowerRight.y+1;
		
	}

	/** A method to modify the location of a block.
	 * @param a The point reference for the new position of the block. */
	public void moveLeft(Point a){
		this.upperLeft.y = a.y;
		this.lowerRight.y = this.lowerRight.y-1;
	}

	public boolean equals (Object compare) {

		Block toCompare = (Block) compare;

		if(this.getUpperLeft().equals(toCompare.getUpperLeft()) && this.getLowerRight().equals(toCompare.getLowerRight())){
			return true;
		} else return false;
	}
	
	/** A method for checking whether a given Block has a valid state.
	 * @return boolean Whether or not the given Block is valid. */
	public boolean isOK() {
		int upperLeftRow, upperLeftCol, lowerRightRow, lowerRightCol;
		upperLeftRow = this.getUpperLeft().x;
		upperLeftCol = this.getUpperLeft().y;
		lowerRightRow = this.getLowerRight().x;
		lowerRightCol = this.getLowerRight().y;
		
		if (upperLeftRow < 0 || lowerRightRow >= Board.rows) {
			return false;
		} else if (upperLeftCol < 0 || lowerRightCol >= Board.cols) {
			return false;
		} else {
			return true;
		}
	}

}