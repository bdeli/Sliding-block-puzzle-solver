import java.awt.Point;
import java.awt.print.Printable;
import java.util.HashSet;
import java.util.Scanner;

import junit.framework.TestCase;


public class SolverTest extends TestCase {



	/*
	 * 
	 * public void testAddMoves(){

		String inputDummyOne ="3 3\n0 0 0 0\n0 2 0 2";
		String goalDummyOne ="1 1 1 1";

		Board initBoardOne;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Solver mySolver = new Solver(initConfigOne, goalConfigOne);

		//initBoardOne = new Board(initConfigOne, goalConfigOne);
		initBoardOne = new Board(initConfigOne, goalConfigOne);

		// HashSet to keep track of previously encountered board configurations.
		mySolver.configSeen = new HashSet<Board>();

		assertEquals(0, mySolver.configSeen.size());

		// Adding the initial board configuration to the HashSet.
		mySolver.configSeen.add(initBoardOne);

		assertEquals(1, mySolver.configSeen.size());

		while (!mySolver.fringe.isEmpty()){
			System.out.println(mySolver.fringe.pop());	
		}


	}


	public void testAddMovesDublicate(){

		String inputDummyOne ="2 2\n0 0 0 0";
		String goalDummyOne ="1 1 1 1";

		Board initBoardOne;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Solver mySolver = new Solver(initConfigOne, goalConfigOne);

		//initBoardOne = new Board(initConfigOne, goalConfigOne);
		initBoardOne = new Board(initConfigOne, goalConfigOne);

		// HashSet to keep track of previously encountered board configurations.
		mySolver.configSeen = new HashSet<Board>();

		assertEquals(0, mySolver.configSeen.size());

		// Adding the initial board configuration to the HashSet.
		mySolver.configSeen.add(initBoardOne);

		assertEquals(1, mySolver.configSeen.size());


		System.out.println(mySolver.fringe.toString());

		mySolver.addMoves(mySolver.initBoard.possibleMoves());

		System.out.println(mySolver.fringe.toString());

	}


	public void testIsSolved(){

		String inputDummyOne ="3 3\n0 0 0 0\n0 2 0 2";
		String goalDummyOne ="2 0 2 0\n2 2 2 2";

		//Board initBoardOne;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Solver mySolver = new Solver(initConfigOne, goalConfigOne);

		assertFalse(mySolver.initBoard.isSolved());

		mySolver.initBoard.moveBlock(new Point(0,2), new Point(1,2));
		mySolver.initBoard.moveBlock(new Point(1,2), new Point(2,2));

		mySolver.initBoard.moveBlock(new Point(0,0), new Point(1,0));
		mySolver.initBoard.moveBlock(new Point(1,0), new Point(2,0));

		assertTrue(mySolver.initBoard.isSolved());

	}



	public void testStack(){

		String inputDummyOne ="3 3\n0 1 0 1\n1 1 1 1";
		String goalDummyOne ="0 1  1\n1 1 1 1";

		Board initBoardOne;

		Scanner initConfigOne = new Scanner(inputDummyOne);
		Scanner goalConfigOne = new Scanner(goalDummyOne);

		Solver mySolver = new Solver(initConfigOne, goalConfigOne);

		//initBoardOne = new Board(initConfigOne, goalConfigOne);
		//initBoardOne = new Board(initConfigOne, goalConfigOne);

		// HashSet to keep track of previously encountered board configurations.
		mySolver.configSeen = new HashSet<Board>();

		assertEquals(0, mySolver.configSeen.size());

		// Adding the initial board configuration to the HashSet.
		mySolver.configSeen.add(mySolver.initBoard);

		assertEquals(1, mySolver.configSeen.size());

		int k = 0;

		System.out.println("BLOCK LIST" + mySolver.initBoard.myBlocks.values());

		System.out.println("SOLVED TEST" + mySolver.initBoard.isSolved());

		if (mySolver.initBoard.isSolved()){
			mySolver.initBoard.printWhite();
			System.exit(0);
		}



		while(!mySolver.fringe.isEmpty() && k < 500){

			System.out.println("BLOCK LIST" + mySolver.initBoard.myBlocks.values());


			Move move = mySolver.fringe.pop();		
			System.out.println("UPCOMOING MOVE" + move);

			try{
				mySolver.initBoard.moveBlock(move.getBefore(), move.getAfter());

			}
			catch (IllegalArgumentException e){
				continue;
			}

			if (mySolver.initBoard.isSolved()){
				mySolver.initBoard.printWhite();
				System.exit(0);
			}


			if (mySolver.configSeen.contains(mySolver.initBoard)){
				//reverse move

				mySolver.initBoard.moveBlock(move.getAfter(), move.getBefore());

			}


			if (!mySolver.configSeen.contains(mySolver.initBoard)) {

				System.out.println("Board is being added.");

				mySolver.configSeen.add(mySolver.initBoard);
			}

			System.out.println("POSSIBLE MOVES:" + mySolver.initBoard.possibleMoves());

			mySolver.addMoves(mySolver.initBoard.possibleMoves());

			System.out.println(mySolver.fringe.size());
			System.out.println(mySolver.fringe.toString());


			k++;

		}




	}

}



			Move move = fringe.pop();

			try{
				initBoard.moveBlock(move.getBefore(), move.getAfter());

			}
			catch (IllegalArgumentException e){
				continue;
			}




			if (initBoard.isSolved()){
				System.exit(0);
			}


			if (configSeen.contains(initBoard)){
				//reverse move

				initBoard.moveBlock(move.getAfter(), move.getBefore());

			} if (!configSeen.contains(initBoard)) {
				configSeen.add(initBoard);
				//adjust move list				

				addMoves(initBoard.possibleMoves());

			}
			System.out.println("no solution");
			System.exit(1);

		}

		System.out.println("The board was solved");




		//reach goal here
		//end game;
		//TODO: print possible moves
		//want linkedList to be printed of all the moves made;

		System.exit(0);



	 */

}
