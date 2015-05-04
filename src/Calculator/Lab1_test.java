package Calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class Lab1_test {
	Calculator C; // need to be initialized by the implemented
					// class

	@Test
	//test of next() & prev() functions
	public void Lab1_test0() {
		Calc C = new Calc();
		C.Input("");
		assertEquals(Calculator.BAD_FORMAT, C.getResults());
		C.Input("5+2*1.5-7/10");
		assertEquals("7.3", C.getResults());
		C.Input("5*2/10/10");
		assertEquals("0.1", C.getResults());
		assertEquals(Calculator.END_LIST_DOWN, C.next());
		assertEquals("5+2*1.5-7/10", C.prev());
		assertEquals("5+2*1.5-7/10", C.current_formula());
		assertEquals("", C.prev());
		assertEquals(Calculator.END_LIST_UP, C.prev());
		C.Input("0.2*5+9-14/8+26*48/11");
		assertEquals("121.7", C.getResults());
		assertEquals("0.2*5+9-14/8+26*48/11", C.current_formula());
		C.Input("0.25*0.5/10*100+12");
		assertEquals("13.2", C.getResults());
		assertEquals(Calculator.END_LIST_DOWN, C.next());
		assertEquals("0.2*5+9-14/8+26*48/11", C.prev());
		assertEquals("5*2/10/10", C.prev());
		assertEquals("5+2*1.5-7/10", C.prev());
		assertEquals("", C.prev());
		assertEquals(Calculator.END_LIST_UP, C.prev());
		assertEquals("", C.current_formula());
		assertEquals(Calculator.BAD_FORMAT, C.getResults());
		assertEquals("5+2*1.5-7/10", C.next());
		assertEquals("5*2/10/10", C.next());
		C.Input("8");
		assertEquals("8", C.current_formula());
		assertEquals("8.0", C.getResults());
		
	}

	@Test
	//test of save() & load() functions
	public void Lab1_test1() {
		Calc C = new Calc();
		C.Input("3.5-2*4/4");
		assertEquals("1.5", C.getResults());
		
		C.Input("5+1");
		assertEquals("6.0", C.getResults());
		
		C.Input("5+2");
		assertEquals("7.0", C.getResults());
		
		C.Input("5+3");
		assertEquals("8.0", C.getResults());
		
		C.Input("5+4");
		assertEquals("9.0", C.getResults());
		
		C.Input("5+5");
		assertEquals("10.0", C.getResults());
		
		assertEquals("5+5", C.current_formula());
		
		assertEquals("5+4", C.prev());
		assertEquals("5+3", C.prev());
		assertEquals("5+2", C.prev());
		assertEquals("5+1", C.prev());
		assertEquals("3.5-2*4/4", C.prev());
		assertEquals(Calculator.END_LIST_UP, C.prev());
		
		C.save();
		
		assertEquals("3.5-2*4/4", C.current_formula());
		assertEquals("5+1", C.next());
		assertEquals("5+2", C.next());
		assertEquals("5+3", C.next());
		assertEquals("5+4", C.next());
		assertEquals("5+5", C.next());
		assertEquals(Calculator.END_LIST_DOWN, C.next());
		
		C.load();
		
		assertEquals("5+1", C.current_formula());
		assertEquals("5+2", C.prev());
		assertEquals("5+3", C.prev());
		assertEquals("5+4", C.prev());
		assertEquals("5+5", C.prev());
		assertEquals(Calculator.END_LIST_UP, C.prev());
		
		C.Input("5+6");
		C.Input("5+7");
		C.Input("5+8");
		C.Input("5+9");
		C.Input("5+10");
		
		C.save();
		C.load();
		
		assertEquals("5+6", C.current_formula());
		assertEquals("5+7", C.prev());
		assertEquals("5+8", C.prev());
		assertEquals("5+9", C.prev());
		assertEquals("5+10", C.prev());
		assertEquals(Calculator.END_LIST_UP, C.prev());
		
	}

	@Test
	//division by zero
	public void Lab1_test2() {
		Calc C = new Calc();
		C.Input("9*3/5-5");
		assertEquals("0.4", C.getResults());
		
		C.Input("9*3/0-5");
		assertEquals(Calculator.DIVISION_BY_ZERO, C.getResults());
	}

	@Test
	//
	public void Lab1_test3() {
		Calc C = new Calc();
		C.Input("0/5-5");
		assertEquals("-5.0", C.getResults());
	}

	@Test
	public void Lab1_test4() {
		Calc C = new Calc();
		C.Input("5*-4/10");
		assertEquals("-2.0", C.getResults());
	}

	@Test
	public void Lab1_test5() {
		Calc C = new Calc();
		C.Input("17 / 2-");
		assertEquals(Calculator.BAD_FORMAT, C.getResults());
	}

	@Test
	public void Lab1_test6() {
		Calc C = new Calc();
		C.Input("5--2");
		assertEquals("7.0", C.getResults());
	}
}
