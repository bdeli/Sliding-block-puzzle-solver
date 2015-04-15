/**
 * CS61BL 2013 Project 3
 *
 * Bhavani Deliwala 
 * cs61bl-gw
 *
 * James Lozano
 * cs61bl-jj
 *
 * Daniel Hellwig
 * cs61bl-iq
 *
 * Michael Zhao
 * cs61bl-ir
 *
 */

import java.util.*;

import java.awt.Point;
import java.io.*;

/**
 * Solver that produces sequence of moves to solve a sliding-block puzzle.
 */
public class Solver {

	/** The instance and class variables for the Solver class. */
	public static Board initBoard;
	public static HashSet <Board> configSeen;
	public static LinkedList<Point> toBePrinted;
	public static Stack <Board> fringe;
	public static boolean debug = false;
	public static boolean checkisOK = false;
	public static boolean view = false;
	public static boolean fringesize = false;
	public static boolean stacksizecheck = false;
	public static boolean movemade = false;
	public static boolean blocks = false;



	/**
	 * Solver constructor.  Initializes class variables.
	 * @param initConfig Scanner object specifying initial tray configuration.
	 * @param goalConfig String name of the desired final configuration file
	 */
	public Solver(Scanner initConfig, Scanner goalConfig){
		initBoard = new Board(initConfig, goalConfig);

		configSeen = new HashSet<Board>();

		configSeen.add(initBoard);

		fringe = new Stack <Board>();

		if (debug){	
			System.out.println("Initial:");

			initBoard.printWhite();

			System.out.println("Initial stack :" + fringe.size());
			System.out.println("");
		}
		addMoves(initBoard, initBoard.possibleMoves());
		if (debug){
			System.out.println("Initial stack after first call to add moves:" + fringe.size());
			System.out.println("");
		}
	}

	/** The main method of the Solver class.  Runs the search algorithm to find a solution to a given puzzle.
	 * @param args The user input. */
	public static void main(String[] args) {
		String initialConfiguration;
		String goalConfiguration;
		String debugSpecification;
		ArrayList<Move> aMoves = new ArrayList<Move>();

		if (args.length == 3 && args[0].startsWith("-o")) {
			debugSpecification = args[0];
			initialConfiguration = args[1];
			goalConfiguration = args[2];

			if (debug){
				System.out.println("OPTIONS RUN");

				System.out.println(debugSpecification);
			}
			try {
				solverInit(debugSpecification, initialConfiguration, goalConfiguration);
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
				System.exit(1);
			}

		} else if (args.length == 2) {
			initialConfiguration = args[0];
			goalConfiguration = args[1];
			if (debug){
				System.out.println("REGULAR RUN");
			}
			try {
				solverInit(initialConfiguration, goalConfiguration);
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
				System.exit(1);
			}
		} 

		else 

		{
			throw new IllegalArgumentException("Invalid amount of arguments.");
		}

		if (initBoard.isSolved()){
			if (debug){	
				System.out.println("Init check: board already solved.");

				initBoard.printWhite();

				System.out.println(initBoard.myGoal.toString());
				System.out.println(initBoard.isSolved());
				System.out.println(initBoard.myBlocks.size());
			}
			System.exit(0);
		}

		int k = 0;//TODO: get rid of this?

		while(!fringe.isEmpty()){
			
			if (stacksizecheck){
					
				System.out.println("Configurations seen: ");
				System.out.println(configSeen.size());
			}
			
			if (fringesize){
			
				System.out.println("Current size of fringe: ");
				System.out.println(fringe.size());		
			}

			for (int i = 0; i < fringe.size(); i++){

				if (debug){
					System.out.println("Fringe element" + i);

					fringe.elementAt(i).printWhite();
					System.out.println("Hashcode" + fringe.elementAt(i).hashCode());
				}

				Board current = new Board (fringe.pop());	

				if (configSeen.contains(current)){
					if (debug){
						System.out.println("Already seen.");
						System.out.println("Hashcode" + current.hashCode());
					}
					continue;

				} else {

					if (debug){
						System.out.println("Current board to be added:");
						System.out.println("");

					}
					
					if (view){
						System.out.println("Current Board: ");
						current.printWhite();
						System.out.println("");
					}
					if (movemade){
						System.out.println("Latest move that was executed: ");
						System.out.println(current.mySolution.getLast().toString());
					}
					
					if(blocks){
						System.out.println("List of blocks currently contained in the board: ");
						Iterator <Block> iterBlocksDebug = current.myBlocks.values().iterator();
						while (iterBlocksDebug.hasNext()){
							System.out.println(iterBlocksDebug.next().toString());
							
						}
						
					}
					configSeen.add(current);

					addMoves(current, current.possibleMoves());
					
					
				}
				
				if(checkisOK){
					
					System.out.println("Is the current board configuration valid?");
					System.out.println(current.isOk());
					
				}
				
				if (current.isSolved()){

					if (debug){
						System.out.println("SOLUTION:");
						current.printWhite();
					}

					Iterator <Move> movePath = current.mySolution.iterator();

					while (movePath.hasNext()){
						System.out.println(movePath.next().toString());
					}


					System.exit(0);//program ends normally
				}

				k++;//TODO: get rid of this?

			}

		}

		System.out.println("Theres no solution for this puzzle.");
		System.exit(1);

	}

	/**
	 *A method for initializing the state of the Solver
	 * @param options Optional debugging arguments
	 * @param initName String name of the initial configuration file
	 * @param goalName String name of the desired final configuration file
	 */
	private static void solverInit(String options, String initName, String goalName) throws FileNotFoundException {
		Scanner initFile;
		Scanner goalFile;

		initFile = new Scanner(new File(initName));
		goalFile = new Scanner(new File(goalName));


		//if (!options.substring(0, 2).equals("-o")) {//TODO: fix the -o options
		//	throw new IllegalArgumentException("Optional argument must be specified with '-o'.");
		//} else {
		//	options = options.substring(2);
		//}


		if (options.equals("-ooptions")) {


			System.out.println("Please choose from the following list of debug options:");
			System.out.println("(For example, calling Solver with first argument -o[-t -c -v] would activate options t, c, and v.");
			System.out.println("");

			System.out.println("-n: Track the current number of configurations in the fringe.");

			System.out.println("-c: Call the isOK mathod for each construction of the board and the block classes.");

			System.out.println("-v: Print a boolean representation of the board that is considered at every step.");

			System.out.println("-s: Print the size of the stack at every step.");

			System.out.println("-m: Print the last move that created the current board configuration for every board.");

			System.out.println("-b: Print the blocks contained in each board at every step.");
			System.exit(0);
		}


		else {
			for (int i = 0; i < options.length(); i++){


				if (options.charAt(i) == 'n'){
					fringesize = true;	
				}

				if (options.charAt(i) == 'c'){
					checkisOK = true;	
				}

				if (options.charAt(i) == 'v'){
					view = true;	
				}

				if (options.charAt(i) == 's'){
					stacksizecheck = true;	
				}

				if (options.charAt(i) == 'm'){
					movemade = true;	
				}

				if (options.charAt(i) == 'b'){
					blocks = true;	
				}
			}
		}




		

		try {
			initFile = new Scanner(new File(initName));
			goalFile = new Scanner(new File(goalName));	
			Solver mySolver = new Solver(initFile, goalFile);

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found.");
		}
	}

	/** A method for initializing the Solver.\
	 * @param initName The name of the initial configuration file to read from.
	 * @param goalName The name of the goal configuration file to read from. */
	private static void solverInit(String initName, String goalName) throws FileNotFoundException {
		Scanner initFile;
		Scanner goalFile;

		initFile = new Scanner(new File(initName));
		goalFile = new Scanner(new File(goalName));

		try {
			initFile = new Scanner(new File(initName));
			goalFile = new Scanner(new File(goalName));	

			Solver mySolver = new Solver(initFile, goalFile);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found.");
		}
	}

	/** A method to add new possible board configurations to the fringe. 
	 * @param origBoard The Board from which we are branching off.
	 * @param moveSet The possible moves that can be made at a given Board configuration. */
	public static void addMoves(Board origBoard, HashMap<Point, Integer> moveSet){

		Iterator<Point> myMoves = moveSet.keySet().iterator();

		while (myMoves.hasNext()){

			Point current = myMoves.next();

			int toMove = moveSet.get(current);
			if (debug){
				System.out.println("piece to move: " + current.toString());
				System.out.println("moving code: " + toMove);
			}
			Point currentStatic = new Point (current.x, current.y);

			Board currentBoard = new Board(origBoard);
			if (debug){
				System.out.println("Current Board");
			}
			if (debug){	
				currentBoard.printWhite();
			}

			if (toMove % 10 == 1){//ones place, move left

				Move moveCurrent = new Move (currentStatic,  new Point(current.x, current.y - 1));

				Board board1 = new Board(currentBoard, moveCurrent);

				board1.moveBlock(moveCurrent.getBefore(), moveCurrent.getAfter());

				if (!fringe.contains(board1)) {
					//System.out.println("LEFT MOVE TO BE ADDED TO FRINGE");

					//System.out.println("THIS IS THE HASHCODE " + board1.hashCode());
					if (debug){	
						board1.printWhite();
					}
					fringe.push(board1);
					//System.out.println("MOVE TO BE ADDED TO FRINGE" + board1.toString());
				}
			} 

			if ((toMove / 10) % 10 == 1){ //tens place, move down

				Move moveCurrent = new Move (currentStatic, new Point(current.x + 1, current.y));

				Board board2 = new Board(currentBoard, moveCurrent);

				board2.moveBlock(moveCurrent.getBefore(), moveCurrent.getAfter());

				if (fringe.contains(board2)) {
					if (debug){
						System.out.println("SOMETHING WITH HASHING OR CONTAINS IS WRONG.");
					}
				}

				if (!fringe.contains(board2)) {
					fringe.push(board2);
					if (debug){
						System.out.println("DOWN MOVE TO BE ADDED TO FRINGE" + board2.toString());
					}
				}
			} 

			if ((toMove / 100) % 10 == 1){ //hundreds place, move right

				Move moveCurrent = new Move (currentStatic, new Point(current.x, current.y+1));

				Board board3 = new Board(currentBoard, moveCurrent);

				board3.moveBlock(moveCurrent.getBefore(), moveCurrent.getAfter());

				//System.out.println("THIS IS THE HASHCODE " + board3.hashCode());

				if (fringe.contains(board3)) {
					if (debug){
						System.out.println("SOMETHING WITH HASHING OR CONTAINS IS WRONG.");
					}
				}

				if (!fringe.contains(board3)) {

					fringe.push(board3);
					//System.out.println("RIGHT MOVE TO BE ADDED TO FRINGE" + board3.toString());
					//System.out.println("");
				}
			} 

			if ((toMove / 1000) % 10 == 1){ //thousands place, move up

				Move moveCurrent = new Move (currentStatic, new Point(current.x - 1, current.y));

				Board board4 = new Board(currentBoard, moveCurrent);

				board4.moveBlock(moveCurrent.getBefore(), moveCurrent.getAfter());

				if (!fringe.contains(board4)) {

					fringe.push(board4);
					if (debug){
						System.out.println("UP MOVE TO BE ADDED TO FRINGE" + board4.toString());
					}
				}
			}
		}
	}
}
