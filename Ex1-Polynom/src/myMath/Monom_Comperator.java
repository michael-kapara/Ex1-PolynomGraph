package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {
/**
 * checks which monom is bigger by power
 * @param m1 Monom
 * @param m2 Monom
 * @return -1 if m1, 1 if m2,0 if equals
 */
	@Override
	public int compare (Monom m1, Monom m2) {
		
		
			if(m1.get_power() > m2.get_power())
				return -1;
			else if(m1.get_power()== m2.get_power())
				return 0;
			
			else
				return 1;
		}

}
