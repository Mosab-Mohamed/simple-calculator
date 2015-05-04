package Calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Lab1Junit {
	Calculator C ; // need to be initialized by the
					// implemented

	// class

//	@Test
//	public void Messages() {
//		C = new Calc();
//		assertEquals(Calculator.END_LIST_UP, C.prev());
//		assertEquals(Calculator.END_LIST_DOWN, C.next());
//		C.Input("");
//		assertEquals(Calculator.BAD_FORMAT, C.getResults());
//		C.Input(" ");
//		assertEquals(Calculator.BAD_FORMAT, C.getResults());
//		C.Input("2-3+");
//		assertEquals(Calculator.BAD_FORMAT, C.getResults());
//		C.Input("2-+5");
//		assertEquals("-3.0", C.getResults());
//		C.Input("5.6/6*0");
//		assertEquals("0.0", C.getResults());
//		C.Input("5-0*6/0");
//		assertEquals(Calculator.DIVISION_BY_ZERO, C.getResults());
//		C.Input("6/0");
//		assertEquals(Calculator.DIVISION_BY_ZERO, C.getResults());
//	}
//
//	@Test
//	public void History() {
//		C = new Calc();
//		C.Input("3.5-1");
//		assertEquals("2.5", C.getResults());
//		C.Input("4/-4");
//		assertEquals("-1.0", C.getResults());
//		C.Input("2*-4/4");
//		assertEquals("-2.0", C.getResults());
//		C.Input("3.5-2*4/4");
//		assertEquals("1.5", C.getResults());
//		C.Input("2+4");
//		assertEquals("6.0", C.getResults());
//		assertEquals("3.5-2*4/4", C.prev());
//		assertEquals("2*-4/4", C.prev());
//		assertEquals("4/-4", C.prev());
//		assertEquals("4/-4", C.current_formula());
//		assertEquals("2*-4/4", C.next());
//		assertEquals("3.5-2*4/4", C.next());
//		assertEquals("1.5", C.getResults());
//		assertEquals("3.5-2*4/4", C.current_formula());
//		C.prev();
//		C.prev();
//		C.prev();
//		C.prev();
//		C.prev();
//		C.prev();
//		C.prev();
//		assertEquals("3.5-1", C.current_formula());
//		assertEquals("2.5", C.getResults());
//		C.next();
//		C.next();
//		C.next();
//		C.next();
//		C.next();
//		C.next();
//		C.next();
//		C.next();
//		assertEquals("2+4", C.current_formula());
//		assertEquals("6.0", C.getResults());
//	}
//
//	@Test
//	public void Save_Load() throws Exception {
//		C = new Calc();
//		C.Input("9*3/5-5");
//		C.Input("3.5-1");
//		C.Input("4/-4");
//		C.save();
//		C.Input("3.5-1");
//		C.Input("0.0");
//		C.Input("4");
//		C.Input("3+4");
//		C.Input("2-4");
//		C.load();
////		assertEquals("4/-4", C.current_formula());
//		
//		assertEquals("9*3/5-5", C.current_formula());
//		assertEquals("3.5-1", C.prev());
//		assertEquals("4/-4", C.prev());
//		assertEquals(Calculator.END_LIST_UP, C.prev());
//		
//		C.Input("3.5-1");
//		C.Input("0.0");
//		C.Input("4");
//		assertEquals("4.0", C.getResults());
//		C.Input("3+4");
//		C.Input("2-4");
//		C.save();
//	}

	 @Test
	 public void After_Closing() throws Exception {
		 C = new Calc();
	 C.load();
	 assertEquals("3.5-1", C.current_formula());
	 assertEquals("2.5", C.getResults());
	 assertEquals("0.0", C.prev());
	 assertEquals("0.0", C.getResults());
	 assertEquals("4", C.prev());
	 assertEquals("4.0", C.getResults());
	 assertEquals("3+4", C.prev());
	 assertEquals("7.0", C.getResults());
	 assertEquals("2-4", C.prev());
	 assertEquals("-2.0", C.getResults());
	 assertEquals(Calculator.END_LIST_UP, C.prev());
	 }

}
