import java.awt.Point;
import java.io.File;
import java.util.*;

import junit.framework.TestCase;


public class BoardTest extends TestCase {

	private static final boolean IllegalArgumentException = false;

	public void testBoard(){


		String inputDummy ="4 4\n1 1 1 1\n2 2 2 2";
		String goalDummy ="0 1 0 1";

		Board initBoard;

		Scanner initConfig = new Scanner(inputDummy);
		Scanner goalConfig = new Scanner(goalDummy);

		initBoard = new Board(initConfig, goalConfig);

		initBoard.printWhite();

	}


	public void testBoardConstructorTwo(){

		System.out.println("COPY");

		String inputDummy ="4 4\n1 1 1 1\n2 2 2 2";
		String goalDummy ="0 1 0 1";

		Board initBoard;

		Scanner initConfig = new Scanner(inputDummy);
		Scanner goalConfig = new Scanner(goalDummy);

		initBoard = new Board(initConfig, goalConfig);

		//Move testMove = new Move (new Point (1,1), new Point (0,1));

		//Board initBoardTwo = new Board(initBoard);

		//System.out.println("INITIAL");
		//initBoard.printWhite();

		//System.out.println("COPY");

		System.out.println(initBoard.myGoal.get(0));


	//	initBoardTwo.moveBlock(new Point(1,1), new Point (1,2));
		//initBoardTwo.printWhite();

	}



	public void testMoves(){


		String inputDummy ="2 2\n0 0 0 0";
		String goalDummy ="0 1 0 1";

		Board initBoard;

		Scanner initConfig = new Scanner(inputDummy);
		Scanner goalConfig = new Scanner(goalDummy);

		initBoard = new Board(initConfig, goalConfig);
		System.out.println("OLD VERSION");
		initBoard.printWhite();

		//Move single piece to the right.
		System.out.println("MOVE RIGHT");
		initBoard.moveBlock(new Point(0,0), new Point(0,1));
		initBoard.printWhite();

		//Move single piece down
		System.out.println("MOVE DOWN");
		initBoard.moveBlock(new Point(0,1), new Point(1,1));
		initBoard.printWhite();

		//Move single piece to the left.
		System.out.println("MOVE LEFT");
		initBoard.moveBlock(new Point(1,1), new Point(1,0));
		initBoard.printWhite();

		//Move single piece up.
		System.out.println("MOVE UP");
		initBoard.moveBlock(new Point(1,0), new Point(0,0));
		initBoard.printWhite();


	}



	public void testObstracles(){


		String inputDummy ="2 2\n0 0 0 0\n0 1 0 1";
		String goalDummy ="1 1 1 1";

		Board initBoard2;

		Scanner initConfig2 = new Scanner(inputDummy);
		Scanner goalConfig2 = new Scanner(goalDummy);

		initBoard2 = new Board(initConfig2, goalConfig2);
		System.out.println("OLD VERSION");
		initBoard2.printWhite();

		//Move single piece down
		System.out.println("MOVE DOWN");
		initBoard2.moveBlock(new Point(0,0), new Point(1,0));
		initBoard2.printWhite();

	}

	public void testBoardBoundaryRight(){


		String inputDummy ="2 2\n0 1 0 1";
		String goalDummy ="1 1 1 1";

		Board initBoard3;

		Scanner initConfig3 = new Scanner(inputDummy);
		Scanner goalConfig3 = new Scanner(goalDummy);

		initBoard3 = new Board(initConfig3, goalConfig3);
		System.out.println("OLD VERSION");
		initBoard3.printWhite();

		//Move single piece down
		System.out.println("MOVE RIGHT");

		try {
			initBoard3.moveBlock(new Point(0,1), new Point(0,2));

		} catch (ArrayIndexOutOfBoundsException a) {
		}
	}

	public void testBoardBoundaryLeft(){


		String inputDummy ="2 2\n0 0 0 0";
		String goalDummy ="1 1 1 1";

		Board initBoard3;

		Scanner initConfig3 = new Scanner(inputDummy);
		Scanner goalConfig3 = new Scanner(goalDummy);

		initBoard3 = new Board(initConfig3, goalConfig3);
		System.out.println("OLD VERSION");
		initBoard3.printWhite();

		//Move single piece down
		System.out.println("MOVE LEFT");

		try {
			initBoard3.moveBlock(new Point(0,1), new Point(0,-1));

		} catch (NullPointerException a) {
		}
	}

	public void testBoardBoundaryBottom(){


		String inputDummy ="2 2\n0 1 0 1";
		String goalDummy ="1 1 1 1";

		Board initBoard3;

		Scanner initConfig3 = new Scanner(inputDummy);
		Scanner goalConfig3 = new Scanner(goalDummy);

		initBoard3 = new Board(initConfig3, goalConfig3);
		System.out.println("OLD VERSION");
		initBoard3.printWhite();

		//Move single piece down
		System.out.println("MOVE DOWN");

		try {
			initBoard3.moveBlock(new Point(0,1), new Point(2,0));

		} catch (ArrayIndexOutOfBoundsException a) {
		}
	}

	public void testBoardBoundaryTop(){


		String inputDummy ="2 2\n0 0 0 0";
		String goalDummy ="1 1 1 1";

		Board initBoard3;

		Scanner initConfig3 = new Scanner(inputDummy);
		Scanner goalConfig3 = new Scanner(goalDummy);

		initBoard3 = new Board(initConfig3, goalConfig3);
		System.out.println("OLD VERSION");
		initBoard3.printWhite();

		//Move single piece down
		System.out.println("MOVE LEFT");

		try {
			initBoard3.moveBlock(new Point(0,1), new Point(-1,0));

		} catch (NullPointerException a) {
		}
	} 


	public void testPossibleMoves(){


		String inputDummy ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		String goalDummy ="1 1 1 1";

		Board initBoard3;

		Scanner initConfig3 = new Scanner(inputDummy);
		Scanner goalConfig3 = new Scanner(goalDummy);

		initBoard3 = new Board(initConfig3, goalConfig3);
		System.out.println("OLD VERSION");
		initBoard3.printWhite();

		HashMap<Point, Integer> tempHash = initBoard3.possibleMoves();

		Iterator<Integer> iter = tempHash.values().iterator();

		while (iter.hasNext()){
			System.out.println(iter.next());
		}

	}


	public void testEquals(){


		String inputDummyOne ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		String goalDummyOne ="1 1 1 1";

		String inputDummyTwo ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		String goalDummyTwo ="1 1 1 1";

		Board initBoardOne;
		Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Scanner initConfigTwo = new Scanner(inputDummyTwo);
		Scanner goalConfigTwo = new Scanner(goalDummyTwo);

		initBoardOne = new Board(initConfigOne, goalConfigOne);
		initBoardTwo = new Board(initConfigTwo, goalConfigTwo);

		assertTrue(initBoardOne.isEqual(initBoardTwo));

		initBoardTwo.moveBlock(new Point(1,1), new Point(1,0));

		assertFalse(initBoardOne.isEqual(initBoardTwo));


	}



	public void testHash(){


		String inputDummyOne ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		String goalDummyOne ="1 1 1 1";

		String inputDummyTwo ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		String goalDummyTwo ="1 1 1 1";

		Board initBoardOne;
		Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Scanner initConfigTwo = new Scanner(inputDummyTwo);
		Scanner goalConfigTwo = new Scanner(goalDummyTwo);

		initBoardOne = new Board(initConfigOne, goalConfigOne);
		initBoardTwo = new Board(initConfigTwo, goalConfigTwo);

		int codeOne = initBoardOne.hashCode();
		int codeTwo = initBoardTwo.hashCode();		

		assertEquals(codeOne, codeTwo);

		System.out.println(codeOne);
		System.out.println(codeTwo);		

		initBoardTwo.moveBlock(new Point(1,1), new Point(1,0));
		//int codeTwo = initBoardOne.hasCode();

		codeTwo = initBoardTwo.hashCode();

		System.out.println(codeOne);
		System.out.println(codeTwo);	


	}
	
	public void testHash2(){


		String inputDummyOne ="4 4\n0 1 0 1\n1 0 1 0";
		String goalDummyOne ="1 1 1 1";

		String inputDummyTwo ="4 4\n0 0 0 0\n1 1 1 1";
		String goalDummyTwo ="1 2 1 2";

		Board initBoardOne;
		Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Scanner initConfigTwo = new Scanner(inputDummyTwo);
		Scanner goalConfigTwo = new Scanner(goalDummyTwo);

		initBoardOne = new Board(initConfigOne, goalConfigOne);
		initBoardTwo = new Board(initConfigTwo, goalConfigTwo);

		int codeOne = initBoardOne.hashCode();
		int codeTwo = initBoardTwo.hashCode();		

		
		System.out.println("HASHCODE 1 " + codeOne);
		System.out.println("HASHCODE 2 "+ codeTwo);	


	}
	 
	 

	public void testIsSolvedOnePiece(){


		String inputDummyOne ="3 3\n0 1 0 1";
		String goalDummyOne ="0 0 0 0";



		//String inputDummyTwo ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		//String goalDummyTwo ="1 1 1 1";

		Board initBoardOne;
		//Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		initBoardOne = new Board(initConfigOne, goalConfigOne);

		initBoardOne.printWhite();

		System.out.println("Goal: " + initBoardOne.myGoal.toString());


		System.out.println("Move piece to the left.");

		initBoardOne.moveBlock(new Point(0,1), new Point(0,0));

		initBoardOne.printWhite();

		assertTrue(initBoardOne.isSolved());

		initBoardOne.moveBlock(new Point(0,0), new Point(0,1));

		initBoardOne.moveBlock(new Point(0,1), new Point(0,2));

		initBoardOne.myGoal.remove(0);

		Block newGoal1 = new Block (0, 2, 0, 2);

		initBoardOne.myGoal.add(newGoal1);

		assertTrue(initBoardOne.isSolved());

		initBoardOne.moveBlock(new Point(0,2), new Point(1,2));

		initBoardOne.moveBlock(new Point(1,2), new Point(2,2));

		Block newGoal2 = new Block (2, 2, 2, 2);

		initBoardOne.myGoal.remove(0);

		initBoardOne.myGoal.add(newGoal2);

		assertTrue(initBoardOne.isSolved());

		initBoardOne.moveBlock(new Point(2,2), new Point(2,1));

		initBoardOne.moveBlock(new Point(2,1), new Point(2,0));

		Block newGoal3 = new Block (2, 0, 2, 0);

		initBoardOne.myGoal.remove(0);

		initBoardOne.myGoal.add(newGoal3);

		assertTrue(initBoardOne.isSolved());


	} 

	
	public void testIsSolvedTwoByTwo(){

		String inputDummyOne ="3 3\n0 0 1 1";
		String goalDummyOne ="0 1 1 2";

		//String inputDummyTwo ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		//String goalDummyTwo ="1 1 1 1";

		Board initBoardOne;
		//Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		initBoardOne = new Board(initConfigOne, goalConfigOne);

		initBoardOne.printWhite();

		System.out.println("Goal: " + initBoardOne.myGoal.toString());

		initBoardOne.moveBlock(new Point(0,0), new Point(0,1));

		initBoardOne.printWhite();

		assertTrue(initBoardOne.isSolved());

		initBoardOne.moveBlock(new Point(0,1), new Point(1,1));

		//initBoardOne.moveBlock(new Point(0,1), new Point(0,2));

		initBoardOne.myGoal.remove(0);

		Block newGoal1 = new Block (1, 1, 2, 2);

		initBoardOne.myGoal.add(newGoal1);

		assertTrue(initBoardOne.isSolved());

		initBoardOne.printWhite();

	} 

	public void testIsSolvedTwoPieces(){

		String inputDummyOne ="3 3\n0 1 0 1\n1 1 1 1";
		String goalDummyOne ="0 0 0 0\n1 0 1 0";

		//String inputDummyTwo ="3 3\n0 0 0 0\n2 2 2 2\n1 1 1 1\n0 2 0 2\n0 1 0 1";
		//String goalDummyTwo ="1 1 1 1";

		Board initBoardOne;
		//Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		initBoardOne = new Board(initConfigOne, goalConfigOne);

		initBoardOne.printWhite();

		System.out.println("Goal: " + initBoardOne.myGoal.toString());

		System.out.println("Move piece to the left.");

		initBoardOne.moveBlock(new Point(0,1), new Point(0,0));

		initBoardOne.printWhite();

		System.out.println(initBoardOne.isSolved());

		assertFalse(initBoardOne.isSolved());

		initBoardOne.moveBlock(new Point(1,1), new Point(1,0));

		initBoardOne.printWhite();

		assertTrue(initBoardOne.isSolved());

	}
	
	
	public void testBoardConstructor(){

		// Creating new baord instance using the default baord constructor based on "file" input.
		
		String inputDummyOne ="3 3\n0 1 0 1\n1 1 1 1";
		String goalDummyOne ="0 0 0 0\n1 0 1 0\n1 1 1 1";

		Board initBoardOne;
		Board initBoardTwo;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		initBoardOne = new Board(initConfigOne, goalConfigOne);
		
		// creating new board instance based on initBoardOne input, using alternative constructor;
		
		initBoardTwo = new Board (initBoardOne);
		
		assertEquals(initBoardOne, initBoardTwo);
		
		System.out.println("Goal (orig.): " + initBoardOne.myGoal.toString());
		
		System.out.println("Goal (changed.): " + initBoardTwo.myGoal.toString());
					
	
		//initBoardOne.printWhite();

		//System.out.println("Goal: " + initBoardOne.myGoal.toString());

		//System.out.println("Move piece to the left.");

		//initBoardOne.moveBlock(new Point(0,1), new Point(0,0));

		//initBoardOne.printWhite();

		//System.out.println(initBoardOne.isSolved());

		//assertFalse(initBoardOne.isSolved());

		//initBoardOne.moveBlock(new Point(1,1), new Point(1,0));

		//initBoardOne.printWhite();

		//assertTrue(initBoardOne.isSolved());

	}
	
	
	


}
