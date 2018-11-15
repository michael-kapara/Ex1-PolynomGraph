package myMath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		test();	
	}
	
	/**
	 * test functions:
	 * 1.constructor string and other constructors
	 * 2.add
	 * 3.substruct
	 * 4.derivative
	 * 5.Multiply
	 * 6.f(x)
	 * 7.root
	 * 8.area
	 * 9.copy
	 * 10.equal
	 */
	public static void test() {
//		/**constructor string check
//		 * 
//		 */
//		Polynom a = new Polynom("x^2-4");
//		a.gui();
//		System.out.println("should be:x^2-4, "+"a="+a);
//		System.out.println("b should be not valid:");
//		Polynom b = new Polynom("x^13+2*x^-2");
//		b=(Polynom) a.copy();
//		System.out.println(b.equals(a));
//		System.out.println("b="+b+"\n");
//		Monom tm=new Monom(4,2);
//		tm.addM(new Monom (-7,2));
//		Polynom test=new Polynom ("4*x^2-4");
//		System.out.println(test.size);
//		Polynom Result=new Polynom ("x^2-4");
//		test.add(tm);
//		System.out.println(test.size);
//		Result=new Polynom ("2*x^6+7*x^2-12");
//		test.add(Result);
//		System.out.println(test.size);
//		Result=new Polynom ("2*x^6+8*x^2-16");
//		Result.equals(test);
//		System.out.println(test.size);
//		System.out.println(Result.size);
//		System.out.println(Result.equals(test));
//		
//		/**substract:
//		 * 
//		 */
//		b.substract(a);
//		System.out.println("should be false: "+b.isZero());
//		b=new Polynom ("x+16+-6*x^7");
//		System.out.println();
//		a.substract(b);
//		System.out.println("we should get:10*x^7+x^3-7, a= "+a+"\n");
//		System.out.println();
//		
//		/**add
//		 * 
//		 */
//		((Polynom)b).nullify(); 
//		a.add(b);
//		System.out.println("we should get:10*x^7+x^3-7, a= "+a+"\n");
//		System.out.println();
//		
//		b=new Polynom ("x^16+x+4");
//		a.add(b);
//		System.out.println("we should get:x^16+10*x^7+x^3+x-3, a= "+a+"\n");
//		
//		
//		/**derivative
//		 * 
//		 */
//				System.out.println("should be: 16*x^15+70*x^6+3*x^2+1, a="+a.derivative()+"\n");
//				Monom y=new Monom(4,2);
//				y.derivative();
//				System.out.println("should be: 8*x , y="+y);
//		
//		/**multiply
//		 * 
//		 */
//				a=new Polynom ("x^2-4");
//				b=new Polynom ("x^2+4");
//		a.multiply(b);
//		System.out.println("should be:x^4-16, a="+a+"\n");
//		a=new Polynom ("x^9+17*x^5-x^4+3");
//		b=new Polynom ("6*x^5-x^2+1");
//		a.multiply(b);
//		System.out.println("should be: 6*x^14-x^11+102*x^10-5*x^9-17*x^7+x^6+35*x^5-x^4-3*x^2+3");
//		System.out.println("a= "+a+"\n");
//		
//		/**f(x)
//		 * 
//		 */
//		System.out.println("should be -195:" + b.f(-2));
//	
//		System.out.println("should be 83.30142:" + b.f(1.7)+"\n");
//		
//		
//		
//		/**epsilon
//		 * 
//		 */
//		double eps =0.001;
//		
//		/**root
//		 *  
//		 */
//		System.out.println("should be close to -0.63...:" + b.root(-1, -0.5, eps)+"\n");
//		
//		/**area
//		 * 
//		 */
//		a=new Polynom ("-x^2+3*x+1");
//		System.out.println("should be close to 2.3:" + a.area(-0.2, 1, eps));
//		System.out.println("should be close to 2.16:" + a.area(2, 3, eps));
//		System.out.println("should be close MIN_VALUE:" + a.area(-4, -1, eps)+"\n");
//		
//		/**zero test
//		 * 
//		 */
//		Polynom_able c = new Polynom();
//		c.add(new Monom(1.7,0));
//		c.add(new Monom(-0.6,0));
//		c.add(new Monom(-1.1,0));
//		
//		System.out.println("should be empty:, c="+c.toString()+"\n");
//		
//		System.out.println("should be invalid");
//		Polynom_able d = new Polynom(c.toString());
//		
//		d=new Polynom("x^2");
//		System.out.println("should be false:"+d.equals(c)+"\n");
//		
//		/**polynom test
//		 * 
//		 */
//		Monom m1=new Monom(7,7);
//		Monom m2=new Monom(0,3);
//		Monom m3=new Monom(13,7);
//		Monom m4=new Monom(2,7);
//		Monom m5=new Monom(11,-1);
//		d.add(m1);
//		d.add(m2);
//		d.add(m3);
//		d.add(m4);
//		d.add(m5);
//		System.out.println("should be: 22*x^7+x^2, d= "+d+"\n");
//		
//		/**copy
//		 * 
//		 */
//		c = d.copy();
//		System.out.println("should be 22*x^7+x^2: "+c+"\n");
//		
//		/**equal
//		 * 
//		 */
//		System.out.println("should be true:"+c.equals(d)+"\n");
//		c.add(new Monom (2,6));
//		System.out.println("should be false:"+c.equals(d));
//		
//		
//		
		Polynom Boaz_Test=new Polynom ("0.2*x^4-1.5*x^3+3.0*x^2-x-5");
		Boaz_Test=(Polynom)Boaz_Test.derivative();
		Boaz_Test.gui();
	}
}
