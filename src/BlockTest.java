import junit.framework.TestCase;
import java.awt.*;

public class BlockTest extends TestCase {

	public void testBlockConstructor(){

		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;
		Block current = new Block (a,b,c,d);
		assertEquals(current.getUpperLeft(), new Point(a,b));
	}

	public void testBlockEquals(){

		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;
		Block current = new Block (a,b,c,d);
		Block current2 = new Block (a,b,c,d);
		Block current3 = new Block (b,a,d,c);
		assertTrue(current.equals(current2));
		assertFalse(current.equals(current3));
	}


	public void testBlockUp(){

		int a = 1;
		int b = 1;
		int c = 1;
		int d = 1;

		int e = 0;
		int f = 1;
		int g = 0;
		int h = 1;

		Block before = new Block (a,b,c,d);
		Block after = new Block (e,f,g,h);

		Point finalPosition = new Point(0, 1);

		before.moveUp(finalPosition);

		System.out.println(before.toString());

		assertTrue(after.equals(before));

	}

	public void testBlockDown(){

		int a = 1;
		int b = 1;
		int c = 1;
		int d = 1;

		int e = 2;
		int f = 1;
		int g = 2;
		int h = 1;

		Block before = new Block (a,b,c,d);
		Block after = new Block (e,f,g,h);

		Point finalPosition = new Point(2, 1);


		before.moveDown(finalPosition);


		assertTrue(after.equals(before));

	}

	public void testBlockRight(){

		int a = 1;
		int b = 1;
		int c = 1;
		int d = 1;

		int e = 1;
		int f = 2;
		int g = 1;
		int h = 2;

		Block before = new Block (a,b,c,d);
		Block after = new Block (e,f,g,h);

		Point finalPosition = new Point(1, 2);

		before.moveRight(finalPosition);

		assertTrue(after.equals(before));

	}

	public void testBlockLeft(){

		int a = 1;
		int b = 1;
		int c = 1;
		int d = 1;

		int e = 1;
		int f = 0;
		int g = 1;
		int h = 0;

		Block before = new Block (a,b,c,d);
		Block after = new Block (e,f,g,h);

		Point finalPosition = new Point(1, 0);

		before.moveLeft(finalPosition);

		assertTrue(after.equals(before));

	}



}