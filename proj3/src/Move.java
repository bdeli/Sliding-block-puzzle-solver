import java.util.*;
import java.awt.Point;

public class Move {

	/** The upper left-hand corner of a block before moving. */
	private Point before;
	/** The upper left-hand corner of a block after moving. */
	private Point after;

	/** The default Move constructor.
	 * @param a, b, c, d The integer coordinates of the block to be moved
	 * 			before and after moving. */
	public Move (int a, int b, int c, int d){

		before = new Point(a,b);
		after = new Point(c,d);	
	}

	/** A modified Move constructor.
	 * @param a The previous location of a block
	 * @param b The new location of a block. */
	public Move (Point a, Point b){

		before = a;
		after = b;	
	}

	/** The "toString" method for the move object. */
	public String toString() {
		String rtn ="";
		rtn = rtn + before.x + " " + before.y + " " + after.x + " " + after.y;
		return rtn;		
	}

	/** The getter method for the before field of a Move object. */
	public Point getBefore() {
		return this.before;	
	}

	/** The getter method for the after field of a Move object. */
	public Point getAfter() {
		return this.after;	
	}
	
	/** The equals method for comparing two Move objects. */
	public boolean equals (Object compare) {

		Move toCompare = (Move) compare;

		if(this.before.equals(toCompare.getBefore()) && this.after.equals(toCompare.getAfter())){
			return true;
		} else return false;
	}

}