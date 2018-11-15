package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * JuniTest, test Polynom and Monom sections Functions.
 * @author Michael and Maoz
 *
 */
class JUniTest {

	
	@Test
	void testgui() {
		Polynom test=new Polynom ("4*x^2-4");
		test.gui();
		
		test=new Polynom();
		test.gui();
	}
	
	@Test
	void testadd() {
		Monom tm=new Monom(4,2);
		tm.addM(new Monom (-7,2));
		Polynom test=new Polynom ("4*x^2-4");
		Polynom Result=new Polynom ("x^2-4");
		test.add(tm);
		assertEquals(true, test.equals(Result));
		Result=new Polynom ("2*x^6+7*x^2-12");
		test.add(Result);
		Result=new Polynom ("2*x^6+8*x^2-16");
		assertEquals(true, test.equals(Result));
	}

	@Test
	void testsub() {
		Polynom test=new Polynom ("4*x^2-4");
		test.substract(test);
		Polynom Zero=new Polynom ("0");
		assertEquals(true,test.equals(Zero));
		
		Polynom Result=new Polynom ("4*x^2-4");
		try {
		Result.substract(test);
		}
		catch(Exception e){
			fail("Can't substract null excpretion");
		}
		
		test.substract(Result);
		test.Negative(test);
		assertEquals(true,test.equals(Result));
		
		test=new Polynom("x^4-3*X^2+7*x-6");
		Result=new Polynom("x^7-x^8+x^4-3*X^2+7*x-6");
		test.substract(Result);
		Result=new Polynom("-x^7+x^8");
		assertEquals(true,test.equals(Result));
		
		test=new Polynom("11");
		Result=new Polynom("7");
		test.substract(Result);
		Result=new Polynom("4");
		assertEquals(true,test.equals(Result));
	}
	
	@Test
	void testMult(){
		Monom m1=new Monom (4,2);
		Monom m2=new Monom (7,1);
		m1=new Monom (m1.Mult(m2));
		m2=new Monom (28,3);
		assertEquals(true, m1.get_coefficient()==m2.get_coefficient()&&m1.get_power()==m2.get_power());
		
		Polynom test=new Polynom ("x^9+17*x^5-x^4+3");
        Polynom Result=new Polynom ("6*x^5-x^2+1");
 		test.multiply(Result);
 		Result=new Polynom("6*x^14-x^11+102*x^10-5*x^9-17*x^7+x^6+35*x^5-x^4-3*x^2+3");
 		assertEquals(true,test.equals(Result));
 		
 		Result.nullify();
 		test.multiply(new Polynom("0"));
 		assertEquals(true,test.equals(Result));
	}
	
	@Test
	void testf(){
		Polynom a=new Polynom ("x^9+17*x^5-x^4+3");
        Polynom b=new Polynom ("6*x^5-x^2+1");
 		a.multiply(b);
 		System.out.println("should be 83.30142:" + b.f(1.7)+"\n");
		assertEquals(-195,(int)b.f(-2));
		
		
		a=new Polynom ();
		assertEquals(0,a.f(1));
	}
	
	@Test
	void testRoot() {
		Polynom a=new Polynom ("x^9+17*x^5-x^4+3");
        Polynom b=new Polynom ("6*x^5-x^2+1");
 		a.multiply(b);
		System.out.println("should be close to -0.63...:" + b.root(-1, -0.5, 0.0001)+"\n");
		
		a=new Polynom ("x^2-4");
		assertEquals(2, ((int)a.root(0, 4, 0.0001))+1);//added one to circle up
		assertEquals(-2, (int)a.root(-4, 0, 0.0001));
		
		Polynom Test=new Polynom ("0");
		assertEquals(Double.MIN_VALUE,Test.root(-10, 10, 0.0001));	
	}
	
	@Test
	void test_area() {
		Polynom Boaz_Test=new Polynom ("0.2*x^4-1.5*x^3+3.0*x^2-x-5");
		System.out.println("Area integral should be close to 0.32:"+(float)Boaz_Test.area(-2, 6, 0.000001));
		
		Polynom Test=new Polynom ("0");
		assertEquals(0,Test.area(-10, 10, 0.0001));
		
		Test=new Polynom ("x^2-16");
		assertEquals(0,Test.area(1, 1, 0.0001));
	}
	
	@Test
	void test_area_positive() {
		Polynom Boaz_Test=new Polynom ("0.2*x^4-1.5*x^3+3.0*x^2-x-5");
		System.out.println("Positive area should be close to 25.5:"+(float)Boaz_Test.area_positive(-2, 6, 0.000001));
		
		Polynom Test=new Polynom ("0");
		assertEquals(0,Test.area_positive(-10, 10, 0.0001));
		
		Test=new Polynom ("x^2-16");
		assertEquals(0,Test.area_positive(1, 1, 0.0001));
	}
	
	@Test
	void test_area_negative() {
		Polynom Boaz_Test=new Polynom ("0.2*x^4-1.5*x^3+3.0*x^2-x-5");
		System.out.println("Negative area should be 25.18:"+Boaz_Test.area_negative(-2, 6));
		
		Polynom Test=new Polynom ("0");
		assertEquals(0,Math.abs(Test.area_negative(-10, 10)));
		
		Test=new Polynom ("x^2-16");
		assertEquals(0,Math.abs(Test.area_negative(1, 1)));
	}
	
	@Test
	void Test_Derivative(){
		Polynom Boaz_Test=new Polynom ("0.2*x^4-1.5*x^3+3.0*x^2-x-5");
		Boaz_Test=(Polynom)Boaz_Test.derivative();
		System.out.println("should be close to 0.194:"+Boaz_Test.root(0, 1, 0.00001));
		System.out.println("should be close to 1.753:"+Boaz_Test.root(1, 3, 0.00001));
		System.out.println("should be close to 3.679:"+Boaz_Test.root(3, 5, 0.00001));
		
		Polynom Test=new Polynom ("0");
		assertTrue(Test.derivative().equals(new Polynom()));
	}
	@Test
	void Polytest() {
		
		Polynom_able c = new Polynom();
		c.add(new Monom(1.7,0));
		c.add(new Monom(-0.6,0));
		c.add(new Monom(-1.1,0));
		assertFalse(c.isZero());
		
		Polynom_able d = new Polynom("x^2");
		assertFalse(d.equals(c));
		
		d.add(new Monom(7,7));
		d.add(new Monom(0,3));
		d.add(new Monom(13,7));
		d.add(new Monom(2,7));
		d.add(new Monom(11,-1));
		c=new Polynom("22*x^7+x^2");
		assertTrue(d.equals(c));
	}
}
