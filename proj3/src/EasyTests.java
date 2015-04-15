import java.awt.Point;
import java.util.Scanner;
import java.io.*;

import junit.framework.TestCase;


public class EasyTests extends TestCase {


	public void test1(){

		String inputDummyOne ="1 1\n0 0 0 0";
		String goalDummyOne ="0 0 0 0";
		
		String testFileName = "c:/p3test/1x1";
		String testFileName2 = "c:/p3test/1x1.goal";		

		Solver.main(new String[] {testFileName, testFileName2});
		
	}
		
	public void test2(){

		String inputDummyOne ="1 1\n0 0 0 0";
		String goalDummyOne ="0 0 0 0";
		
		String testFileName = "c:/p3test/1x2.one.block";
		String testFileName2 = "c:/p3test/1x2.one.block.goal";		

		Solver.main(new String[] {testFileName, testFileName2});
		
	}
	
	public void test(){
		
		String inputDummyOne ="1 1\n0 0 0 0";
		String goalDummyOne ="0 0 0 0";
		
		String testFileName = "c:/p3test/1x2.one.block";
		String testFileName2 = "c:/p3test/1x2.one.block.goal";		

		Solver.main(new String[] {testFileName, testFileName2});

	}

}
