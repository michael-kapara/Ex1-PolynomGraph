package myMath;

import java.util.Iterator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function{

	/**
	 * 	constructor
	 * @param a double 
	 * @param b integer  
	 */

	public Monom(double a, int b){//constructor

		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * constructor  by another monom
	 * @param ot Monom
	 */
	public Monom(Monom ot) {//copy constructor
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * return the value of y by parameter
	 * @param x double
	 * @return double 
	 */
	public double f(double x) {
		if (this._power==0) {
			return this._coefficient;
		}
		int i=1;
		double sum=x;
		while(i<_power) {
			sum=sum*x;
			i++;
		}
		sum=this._coefficient*sum;
		return sum;	
	};

	//****************** Private Methods and Data *****************

	private double _coefficient; //a 
	private int _power;//b
	/**
	 * return the power of the monom
	 * @return int
	 */
	public int get_power() {
		return _power;
	}

	/**
	 * empty constructor
	 */
	public Monom(){//constructor

	}
	/**
	 * change the monom into his derivative form
	 * 
	 */
	public void derivative() {
		if(this.get_power()!=0) {
			this.set_coefficient(this.get_coefficient()*this.get_power());
			this.set_power(this.get_power()-1);
		}
		else if(this.get_power()==0) {
			this.set_coefficient(0);
		}
	}
	/**
	 * Multiply a monom with a monom
	 * @param m Monom
	 * @return monom
	 */
	public Monom Mult(Monom m) {
		if(m.get_coefficient()==0||m.get_power()<0) {
			return this;
		}
		Monom m1 = new Monom (this.get_coefficient()*m.get_coefficient(),this.get_power()+m.get_power());
		return m1;
	}
	/**
	 * get the value of the coefficient 
	 * @return coefficient double
	 */
	public double get_coefficient() {
		return _coefficient;
	}

	/**
	 * set the coefficient 
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * setting the power
	 */
	private void set_power(int p) {
		this._power = p;
	}
	/**
	 * add a monom to another iff they have the same power
	 * @param m1 Monom
	 * @return m Monom
	 */
	public Monom addM (Monom m1) {
		
		if(this._power==m1._power) 
			this.set_coefficient(this.get_coefficient()+m1.get_coefficient());

		return this;

	}
	/**
	 * changes the monom to his negative form
	 * @param m Monom
	 */
	public void Negative (Monom m) {
		m.set_coefficient(m.get_coefficient()*(-1));
	}
	/**
	 * print the monom
	 */
	public String toString() {
		if(this.get_coefficient()==0||this.get_power()<0) {
			return "empty";
		}
		if(this.get_power()==0) {
			return _coefficient+"";
		}

		if(this.get_coefficient()==1) {
			return "x^"+_power+"";
		}

		if(this.get_power()==1) {
			return _coefficient+"x";
		}

		if(this.get_power()==1&&this.get_coefficient()==1) {
			return "x";
		}
		return _coefficient+"x^"+_power+"";

	}
}
