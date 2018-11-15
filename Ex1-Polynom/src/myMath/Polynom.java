package myMath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import javmos2.JavmosGUI;
import myMath.Monom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author michael and maoz
 *
 */
public class Polynom implements Polynom_able{

	// ***************** add your code below **********************
//	public double area_positive=0;
//	public double area_negative=0;

	//polynom_able
	/**
	 * Add a polynom to this Polynom
	 * @param p1 Polynom_able
	 */
	public void add(Polynom_able p1) {
		Polynom A= (Polynom) p1.copy();
		boolean a=false;
		Iterator <Monom> it1= A.iteretor();
		while(it1.hasNext()) {
			a=false;
			Iterator <Monom> it2=this.MoList.iterator();
			Monom m1=it1.next();
			while(it2.hasNext()) {
				Monom m2=it2.next();
				if(m2.get_power()==m1.get_power()) {
					a=true;
					m2.addM(m1);
				}
			}
			if(a==false) {
				//it shouldn't  be just a reference
				Monom M_add= new Monom(m1);
				this.MoList.add(M_add);
				//System.out.println(m1);
				this.size++;
			}
		}
		this.removeZero();
		this.sortCompare();
	}
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 * 
	 */

	@Override
	public void add(Monom m1) {
		Iterator <Monom> it=this.iteretor();
		boolean a=false;
		while(it.hasNext()) {
			Monom m2=it.next();
			if(m2.get_power()==m1.get_power()) {
				a=true;
				m2.addM(m1);
			}	
		}

		if(a==false&&m1.get_power()>=0) {
			MoList.add(m1);
			size++;
		}
		this.removeZero();
		this.sortCompare();
	};
	/**
	 * Subtract p1 from this Polynom
	 * @param p1 Polynom_able
	 */


	public void substract(Polynom_able p1){
		Polynom A= (Polynom) p1.copy();
		boolean a=false;
		Negative(A);
		Iterator <Monom> it1= A.iteretor();
		while(it1.hasNext()) {
			a=false;
			Iterator <Monom> it2=this.MoList.iterator();
			Monom m1=it1.next();
			while(it2.hasNext()) {
				Monom m2=it2.next();
				if(m2.get_power()==m1.get_power()) {
					a=true;
					m2.addM(m1);
				}
			}
			if(a==false) {
				//it shouldn't  be just a reference
				Monom M_add= new Monom(m1);
				this.MoList.add(M_add);
				//System.out.println(m1);
				this.size++;
			}
		}
		this.removeZero();
		this.sortCompare();
		//so its still be in his origin form.
	}

	/**
	 * Multiply this Polynom by p1
	 * @param p1 Polynom_abl
	 */


	public void multiply(Polynom_able p1) {
		Polynom A= (Polynom) p1.copy();
		Polynom B = new Polynom();

		Iterator <Monom> it1=this.MoList.iterator();
		while(it1.hasNext()) {

			Iterator <Monom> it2= A.iteretor();
			Monom m1=it1.next();
			while(it2.hasNext()) {
				Monom m2=it2.next();
				B.add(m1.Mult(m2));
			}
		}

		this.nullify();
		Iterator <Monom> it3=B.iteretor();
		while(it3.hasNext()) {
			boolean equals= false;
			Iterator <Monom> it4=this.MoList.iterator();
			Monom m3=it3.next();
			while(it4.hasNext()) {
				Monom m4=it4.next();
				if(m4.get_power()==m3.get_power()) {
					m4.addM(m3);
					equals=true;
					break;
				}

			}
			if(equals==false)
				this.add(m3);
		}
		this.removeZero();
		this.sortCompare();
	}
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 Polynom_able
	 * @return true iff this pulynom represents the same function ans p1
	 */


	public boolean equals (Polynom_able p1) {
		Iterator <Monom> it1= (Iterator<Monom>)p1.iteretor();
		Iterator <Monom> it2=this.MoList.iterator();
		if(this.size!=((Polynom)p1).size) {
			return false;
		}
		if(!this.isZero()&&!p1.isZero())
			return true;
		
		while(it2.hasNext()) {
			Monom m1=it1.next();
			Monom m2=it2.next();
			if(m1.get_power()!=m2.get_power()||m1.get_coefficient()!=m2.get_coefficient()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Test if this is the Zero Polynom
	 * @return false if it is
	 */


	public boolean isZero() {
		Iterator <Monom> it=MoList.iterator();
		if(it.hasNext()) {
			return true;
		}

		return false;
	}
	/**
	 * Compute a value x' (x0 smalleq x' smalleq x1) for with |f(x')| smaller than eps
	 * assuming (f(x0) multi f(x1) small equl 0, returns f(x2) such that:
	 * 1. x0 smalleq x1 smalleq x2  2. f(x2)smaller than eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return xmid double
	 */

	public double root(double x0, double x1, double eps) {
		double xmid = Double.MIN_VALUE;
		if(f(x0)*f(x1)<0) {
			while((x1-x0)>=eps&&(x1-x0)>0.0000000001) {
				xmid=(x0+x1)/2;
				if(f(x0)*f(xmid)<=0) 
					x1=xmid;
				else
					x0=xmid;

			}
		}
		if(xmid==Double.MIN_VALUE) 
			System.out.println("Zero points or more than one.");
		
		return xmid;
	}
	/**
	 * create a deep copy of this Polynum
	 * @return p Polynom_able
	 */
	public Polynom_able copy() {
		Iterator <Monom> it=this.MoList.iterator();
		Polynom_able p=new Polynom();
		while(it.hasNext()) {
			Monom m= new Monom(it.next());
			p.add(m);
		}
		return ((Polynom_able)p);

	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return A Polynom_able
	 */
	public Polynom_able derivative() {
		Polynom_able A= this.copy();
		Iterator <Monom> it=A.iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			m.derivative();
		}
		((Polynom)A).removeZero();
		return (Polynom_able)A;	
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * area_positive the area above x section
	 * area_negative the area under x section
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */


	public double area(double x0,double x1, double eps) {
		double xmov=x0;
		double field=0;
		while(xmov<x1) {
			if(f(xmov)<0) {
				field+=eps*f(xmov);
				//the area above x section
				xmov+=eps;
			}
			else {
				field+=eps*f(xmov);
				xmov+=eps;
			}
		}
		
		return field;
	}
	/**
	 * compute the area above x section
	 * @param x0	first x
	 * @param x1	second x
	 * @param eps	epsilon
	 * @return field	double
	 */
	public double area_positive(double x0,double x1, double eps) {
		double xmov=x0;
		double field=0;
		while(xmov<x1) {
			if(f(xmov)<0) {
				xmov+=eps;
			}
			else {
				field+=eps*f(xmov);
				xmov+=eps;
			}
		}
		
		return field;
	}
	/**
	 * compute the area under x section
	 * @param x0	first x
	 * @param x1	second x
	 * @return field	double
	 */
	public double area_negative(double x0,double x1) {
		double xmov=x0;
		double field=0;
		double eps2=0.01;
		while(xmov<x1) {
			if(f(xmov)<0) {
				field+=eps2*f(xmov);
				xmov+=eps2;
			}
			else {
				xmov+=eps2;
			}
		}
		
		return field*-1;
	}
	
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */


	public Iterator<Monom> iteretor() {
		Iterator <Monom> it=MoList.iterator();
		return it;
	}

	//function
	/*public double f(double x) {
				return 0;
			}*/

	/**
	 * converts the polynom into his negative version
	 * @param p Polynom_able
	 */
	public void Negative (Polynom_able p) {
		Polynom p1=(Polynom) p;
		Iterator <Monom> it=((Polynom) p1).iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			m.Negative(m);
		}

	}
	private List <Monom> MoList= new ArrayList <Monom>();
	public List<Monom> getMoList() {
		return MoList;
	}
	/**
	 * constructor 
	 * @param m1 Monom
	 */
	public Polynom(Monom m1) {//constructor
		this.size=0;
		//this.m1=m1;
		add(m1);
	}
	/**
	 * empty constructor
	 */
	public Polynom() {//constructor
		this.size=0;
	}

	/**
	 * finds the y  by the parameter of x
	 * @param x double
	 */
	public double f(double x) {
		double answer=0;
		Iterator <Monom> it=this.iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			if(m.get_power()==0) 
				answer+=m.get_coefficient();
			else
				answer+=m.f(x);
		}

		return answer;
	}
	public String toString() {
		String s="";
		Iterator <Monom> it1=MoList.iterator();
		Monom m;
		if(it1.hasNext()) {
			m=it1.next();
			s+=m;
		}
		
		while(it1.hasNext()) {
			m=it1.next();
			
			if(m.get_coefficient()>0) {
				s+="+";
			}
			else s+="";
			s+=m;
		}
		if(!this.isZero()) {
			return "null";
		}
		return s;
	}
	
	public void gui() {
		if(!this.isZero()) {
			JavmosGUI my_gui=new JavmosGUI("0");
			return;
		}
		
		JavmosGUI my_gui=new JavmosGUI(this.toString());
	}
	
	/**
	 * constructor 
	 * @param s string
	 */
	public Polynom(String s) {
		this.size=0;
		if(s==null||s.length()==0) {
			System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
			this.nullify();
			return;
		}
		//if the monom starts with a negative number it gets true 
		boolean is_negative=false;
		//as long as the string is not empty
		while(s.length()!=0) {
			double coefficient=0;
			int power=0;
			String s_coef="";
			String s_pow="";

			//x
			//we gets a monom that starts with x
			if(s.charAt(0)==120||s.charAt(0)==88) {
				//if the string is only 'x'
				if(s.length()==1) {
					double coefficient2=1;
					if(is_negative) {//if the coefficient is negative
						coefficient2=coefficient2*-1;
						is_negative=false;
					}
					Monom m=new Monom(coefficient2,1);
					this.add(m);
					s=new String(s.substring(1));
				}
				//if the starts with just x and followed by +/-
				else if(s.length()>1&&(s.charAt(1)==43||s.charAt(1)==45)) {
					double coefficient2=1;
					if(is_negative) {
						coefficient2=coefficient2*-1;
						is_negative=false;
					}
					Monom m=new Monom(coefficient2,1);
					this.add(m);
					s=new String(s.substring(1));
				}
				//}
				//Second case x^b
				else  if((s.charAt(0)==88||s.charAt(0)==120)&&s.length()>2&&s.charAt(1)==94) {
					coefficient=1;
					if(is_negative) {
						coefficient= coefficient*(-1);
						is_negative=false;
					}
					//the second char should be '^'
					if(s.charAt(1)==94)	
						s=new String(s.substring(2));
					else {
						System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
						this.nullify();
						return;}

					for(int i=0;i<s.length();i++) {
						if(s.charAt(i)>47&&s.charAt(i)<58)
							s_pow+=""+s.charAt(i);
						if(s.charAt(i)<48||s.charAt(i)>57)
							break;

					}
					try { power= Integer.parseInt(s_pow);}
					catch(Exception e) {
						System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
						this.nullify();
						return;
					}
					Monom m=new Monom(coefficient,power);
					this.add(m);
					s=new String(s.substring(s_pow.length()));
				}}
			//case 3 a*x^b || a || a*x
			//if it starts with a number
			else if(s.charAt(0)>47&&s.charAt(0)<58) {
				for(int i=0;i<s.length();i++) {
					if(s.charAt(i)!=42&&s.charAt(i)!=43&&s.charAt(i)!=45) 
						s_coef+=""+s.charAt(i);
					if(s.charAt(i)==42) break;
					if(s.charAt(i)==43||s.charAt(i)==45) { break;}
				}
				//checks if the string can be a double
				if(is_A_double(s_coef)) {
					coefficient= Double.parseDouble(s_coef);
					if(is_negative) {
						coefficient= coefficient*(-1);
						is_negative=false;
					}
				}
				else {
					System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
					this.nullify();
					return;
				}
				s=new String(s.substring(s_coef.length()));
				//a
				if(s.length()==0||s.charAt(0)==43||s.charAt(0)==45) {
					Monom m=new Monom(coefficient,0);
					this.add(m);
				}
				else {

					if(s.length()==1||s.charAt(1)!=120&&s.charAt(1)!=88) {
						System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
						this.nullify();
						return;
					}
					//a*x
					//there is no power to x
					if(s.length()==2||s.charAt(2)!=94) {
						Monom m=new Monom(coefficient,1);
						this.add(m);
						s=new String(s.substring(2));
					}
					else if((s.length()>3&&s.charAt(2)==94)){
						s=new String(s.substring(3));
						for(int i=0;i<s.length();i++) {
							if(s.charAt(i)<48||s.charAt(i)>57)
								break;
							s_pow+=""+s.charAt(i);
							//System.out.println(s_pow);
						}
						try { power= Integer.parseInt(s_pow);}

						catch(Exception e) {
							System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
							this.nullify();
							return;
						}
						Monom m=new Monom(coefficient,power);
						this.add(m);
						s=new String(s.substring(s_pow.length()));
					}

				}
			}



			//+
			else if(s.charAt(0)==43&&s.length()>1&&s.charAt(1)!=43)
				s=new String(s.substring(1));
			//-
			else if(s.charAt(0)==45&&s.length()>1&&(s.charAt(1)!=45&&s.charAt(1)!=43)) {
				s=new String(s.substring(1));
				is_negative=true;
			}
			else {
				System.out.println("invalid input: right input a*x^b or x or a connected with +/-");
				this.nullify();
				return;
			}

		}
		this.sortCompare();
	}
	/**
	 * checks if the the string can be a double
	 * @param s
	 * @return true if it is
	 */
	boolean is_A_double(String s) {
		int count_dot=0;
		for(int i=0;i<s.length();i++) {
			if((s.charAt(i)>57||s.charAt(i)<48)&&s.charAt(i)!=46)
				return false;
			if(s.charAt(i)==46) 
				count_dot++;	
		}
		if(count_dot<2)
			return true;
		return false;
	}

	//****************** Private Methods and Data *****************
	private Monom m1;

	private Object getMoList;

	public int size=0;
	/**
	 * removing zero from the polynom
	 */
	public void removeZero() {
		Iterator <Monom> it1= this.iteretor();
		while(it1.hasNext()) {
			if(it1.next().get_coefficient()==0) {
				it1.remove()	;
				this.size--;
			}
		}	
	}
	/**
	 * reset the polynom with zero
	 */
	public void nullify() {
		this.substract(this.copy());
		this.removeZero();
	}
	/**
	 * sorts the polynoms by it's power
	 * from the bigger power to the lower one
	 */
	private Monom_Comperator bypower = new Monom_Comperator();		
	public void sortCompare()
	{
		this.MoList.sort(bypower);
	}
	

}