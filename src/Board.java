import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.Point;

public class Board {

	/** Instance and class variables for the Board class. */
	public HashMap <Point, Block> myBlocks;
	public static ArrayList<Block> myGoal;
	public static int rows, cols;
	public int previous; // storing the move that created this board configuration
	public boolean[][] area;
	public LinkedList<Move> mySolution;
	public static boolean debug = false;


	/** The default Board constructor.  Only called on initial Board.
	 * @param initBoard The initial configuration file.
	 * @param goalConfig The file outlining the goal configuration. */
	public Board(Scanner initBoard, Scanner goalConfig) {
		myBlocks = new HashMap<Point, Block>();
		myGoal = new ArrayList<Block>();
		previous = 0;
		mySolution = new LinkedList<Move>();

		setDimensions(initBoard);
		setBlocks(initBoard);
		setGoal(goalConfig);
	}


	/**Modified board constructor.
	 * @param old The Board object from which to copy filed values.
	 */
	public Board(Board old) {

		myBlocks = new HashMap<Point, Block>();


		area = new boolean[rows][cols];

		for (Block b : old.myBlocks.values()){

			Point key = new Point (b.getUpperLeft());

			Block value = new Block(b.getUpperLeft().x, b.getUpperLeft().y, b.getLowerRight().x, b.getLowerRight().y);

			myBlocks.put(key, value);

			fillArea(value);

			mySolution = new LinkedList<Move>();


			for (int i = 0; i < old.mySolution.size(); i++){
				mySolution.add(old.mySolution.get(i));

			}        
		}        

	}

	/** Modified constructor for a Board object.
	 * @param old The Board object from which to copy filed values.
	 * @param newMove The move to be applied to this board.*/
	public Board(Board old, Move newMove) {

		this(old);
		mySolution.add(newMove);
		//TODO (maybe): might implement actual move here later (today) 

	}

	/**
	 * Board initialization. Sets dimensions of board and instantiates 2D whitespace array.
	 * @param myScanner Scanner object containing information for the initial configuration.
	 */
	private void setDimensions(Scanner myScanner) {
		if (myScanner.hasNext()) {
			rows = myScanner.nextInt();
			cols = myScanner.nextInt();
			area = new boolean[this.rows][this.cols];
		}
	}

	/** A method for checking if the state of a Board is valid.
	 * @return boolean Whether or not a Board is valid. */
	public boolean isOk(){
		int count = 0;
		for(int k = 0; k< this.rows; k++){
			for (int j = 0; j< this.cols; j++){
				count++;
			}
		}
		String checkingDimension = this.getDimensions();
		return ((checkingDimension.equals("" + this.rows + this.cols)) && (count == this.rows*this.cols));

	}

	/** The method used to check if a solution to a puzzle has been found.
	 * @return boolean Whether or not a solution has been found. */
	public boolean isSolved() {

		//System.out.println(this.myGoal.toString());
		boolean state = true;

		for (Block b : this.myGoal){

			//System.out.println(b.toString());
			if (myBlocks.get(b.getUpperLeft()) == null || !myBlocks.get(b.getUpperLeft()).equals(b)){
				state = false;
			}

		} 
		return state;
	}

	public boolean equals(Object obj) {
		return (this.hashCode() == obj.hashCode());
	}

	/** WhiteSpace print method. Shows where there are blocks in the board. */
	public void printWhite() {

		for (int i = 0; i < rows; i++){

			for (int j = 0; j < cols; j++){

				if (area[i][j]) {
					System.out.print("T ");
				} else {
					System.out.print("F ");
				}

			}
			System.out.println("\n");
		}
	}

	/**
	 * Initialization of list of goal blocks.
	 * @param goalConfig Scanner object containing information for the initial configuration.
	 */
	private void setGoal(Scanner goalConfig) {
		if (goalConfig.hasNext()) {
			while (goalConfig.hasNext()){
				int a = goalConfig.nextInt();
				int b = goalConfig.nextInt();
				int c = goalConfig.nextInt();
				int d = goalConfig.nextInt();
				Block current = new Block (a,b,c,d);
				myGoal.add(current);
			}
		}
	}

	/**
	 * Setting Blocks in the board.
	 * @param myScanner Scanner object containing information for the initial configuration.
	 */
	private void setBlocks(Scanner myScanner) {
		if (myScanner.hasNext()) {
			while (myScanner.hasNext()){
				int a = myScanner.nextInt();
				int b = myScanner.nextInt();
				int c = myScanner.nextInt();
				int d = myScanner.nextInt();
				Block current = new Block (a,b,c,d);
				myBlocks.put(current.getUpperLeft(), current);
				fillArea(current);
			}
		}
	}

	/** Method for returning the dimensions of a given Board.
	 * @return String the String represention of the row and column values of a Board. */
	public String getDimensions() {
		String rtn ="";
		rtn = rtn + rows + cols;
		return rtn;        
	}

	/**
	 * Initialization of White Space Boolean Board
	 * @param toFill object containing coordinates of a block in the board
	 */
	public void fillArea(Block toFill) {

		int upperLeftX, upperLeftY, lowerRightX, lowerRightY;

		upperLeftX = toFill.getUpperLeft().x;

		upperLeftY = toFill.getUpperLeft().y;

		lowerRightX = toFill.getLowerRight().x;

		lowerRightY = toFill.getLowerRight().y;

		for (int i = upperLeftX; i<=lowerRightX; i++){

			for (int j = upperLeftY; j<=lowerRightY; j++){

				this.area[i][j] = true;
			}
		}

	}

	/**
	 * Calling method of block movement.
	 * @param before Point of the initial position of a Block to be moved.
	 * @param after Point of the final position of the upper left corner of the piece after the move was completed
	 */
	public void moveBlock(Point before, Point after) throws IllegalArgumentException {

		String direction = "";

		Block toMove = myBlocks.get(before);

		if (Math.abs((after.x - toMove.getUpperLeft().x))>1 || Math.abs((after.y - toMove.getUpperLeft().y))>1){                        
			throw new IllegalArgumentException("You can only move one unit at a time.");
		}


		/*
		 * Establishment of direction of the move given.
		 */
		if (after.x > before.x){
			direction = "down";
			if (debug){
				System.out.println("moving" + direction);
			}
		} else if (after.x < before.x){
			direction = "up";
			if (debug){
				System.out.println("moving" + direction);
			}
		} else if (after.y > before.y){
			direction = "right";
			if (debug){
				System.out.println("moving" + direction);
			}
		} else if (after.y < before.y){
			direction = "left";
			if (debug){
				System.out.println("moving" + direction);
			}
		}

		/*
		 * Validation of legality of the move given, based on availablity of whitespace.
		 */
		if (direction.equals("down")){

			int newX = toMove.getLowerRight().x + 1;

			for (int yVal = toMove.getUpperLeft().y; yVal <= toMove.getLowerRight().y; yVal += 1){
				if (area[newX][yVal]) {
					throw new IllegalArgumentException("Bad move.");
				}
			}

			int top = toMove.getUpperLeft().x;

			this.myBlocks.remove(toMove.getUpperLeft());

			toMove.moveDown(after);

			this.myBlocks.put(toMove.getUpperLeft(), toMove);

			for (int yVal = toMove.getUpperLeft().y; yVal <= toMove.getLowerRight().y; yVal += 1){
				area[top][yVal] = false;
				area[newX][yVal] = true;
			}

		} else if (direction.equals("up")) {

			int newX = toMove.getUpperLeft().x - 1;
			for (int yVal = toMove.getUpperLeft().y; yVal <= toMove.getLowerRight().y; yVal += 1){
				if (area[newX][yVal]) {
					throw new IllegalArgumentException("Bad move.");
				}
			}

			int bottom = toMove.getLowerRight().x;
			this.myBlocks.remove(toMove.getUpperLeft());
			toMove.moveUp(after);
			this.myBlocks.put(toMove.getUpperLeft(), toMove);
			for (int yVal = toMove.getUpperLeft().y; yVal <= toMove.getLowerRight().y; yVal += 1){
				area[bottom][yVal] = false;
				area[newX][yVal] = true;
			}

		} else if (direction.equals("right")) {

			int newY = toMove.getLowerRight().y + 1;

			for (int xVal = toMove.getUpperLeft().x; xVal <= toMove.getLowerRight().x; xVal += 1){
				if (area[xVal][newY]) {
					throw new IllegalArgumentException("Bad move.");
				}
			}

			int side = toMove.getUpperLeft().y;
			

			this.myBlocks.remove(toMove.getUpperLeft());
			toMove.moveRight(after);

			this.myBlocks.put(toMove.getUpperLeft(), toMove);
			for (int xVal = toMove.getUpperLeft().x; xVal <= toMove.getLowerRight().x; xVal += 1){
				area[xVal][side] = false;
				area[xVal][newY] = true;
			}

		} else if (direction.equals("left")) {

			int newY = toMove.getUpperLeft().y - 1;

			for (int xVal = toMove.getUpperLeft().x; xVal <= toMove.getLowerRight().x; xVal += 1){
				if (area[xVal][newY]) {
					throw new IllegalArgumentException("Bad move.");
				}
			}

			int side = toMove.getLowerRight().y;
			this.myBlocks.remove(toMove.getUpperLeft());
			toMove.moveLeft(after);
			this.myBlocks.put(toMove.getUpperLeft(), toMove);
			for (int xVal = toMove.getUpperLeft().x; xVal <= toMove.getLowerRight().x; xVal += 1){
				area[xVal][side] = false;
				area[xVal][newY] = true;
			}

		}

	}


	public HashMap<Point, Integer> possibleMoves() {

		HashMap<Point, Integer> myBlockMoves = new HashMap<Point, Integer>();

		Iterator<Block> iter = myBlocks.values().iterator();

		while (iter.hasNext()){
			int moveSum = 0;

			Block current = iter.next();

			if (canMoveUp(current)){
				moveSum += 1000;
			}

			if (canMoveRight(current)){
				moveSum += 100;
			}

			if (canMoveDown(current)){
				moveSum += 10;
			}

			if (canMoveLeft(current)){
				moveSum += 1;
			}

			if (moveSum > 0){
				myBlockMoves.put(current.getUpperLeft(), moveSum);
			}

		}

		return myBlockMoves;

	}

	public boolean canMoveDown(Block start) {

		int newX = start.getLowerRight().x+1;
		int yVal = start.getUpperLeft().y;

		if (newX >= rows || yVal >= cols-1){
			return false;
		}

		for (yVal = start.getUpperLeft().y; yVal <= start.getLowerRight().y; yVal += 1){

			if (area[newX][yVal]) {
				return false;
			}

		} return true;
	}


	public boolean canMoveUp(Block start) {

		int newX = start.getUpperLeft().x-1;
		int yVal = start.getUpperLeft().y;

		if (yVal > this.cols-1 || newX < 0){
			return false;
		}

		for (yVal = start.getUpperLeft().y; yVal <= start.getLowerRight().y; yVal += 1){

			if (area[newX][yVal]) {

				return false;
			}

		} return true;
	}

	public boolean canMoveLeft(Block start) {

		int newY = start.getUpperLeft().y-1;

		int xVal = start.getUpperLeft().x;

		if (xVal > this.rows-1 || newY < 0){
			return false;
		}

		for (xVal = start.getUpperLeft().x; xVal <= start.getLowerRight().x; xVal += 1){

			if (area[xVal][newY]) {
				return false;
			}

		} return true;
	}


	public boolean canMoveRight(Block start) {

		int newY = start.getLowerRight().y+1;

		int xVal = start.getUpperLeft().x;

		if (xVal > this.rows-1 || newY > this.cols-1){
			return false;
		}

		for (xVal = start.getUpperLeft().x; xVal <= start.getLowerRight().x; xVal += 1){

			if (area[xVal][newY]) {
				return false;
			}

		} return true;
	}

	public boolean isEqual (Board compare){

		Iterator compareBlocks = myBlocks.values().iterator();

		if (myBlocks.size() != compare.myBlocks.size()){
			return false;
		}

		while (compareBlocks.hasNext())
			if (!compare.myBlocks.containsValue(compareBlocks.next())){
				return false;
			}
		return true;

	}

	public int hashCode(){

		Iterator<Block> iter = myBlocks.values().iterator();

		int sum = 0;
		String a = "";

		while (iter.hasNext()){
			a = a + iter.next().toString();

		}
		sum = a.hashCode();

		return sum;

	}

}