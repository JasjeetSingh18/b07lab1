public class Polynomial {

	double[] poly_coeff;	
	
	public Polynomial() {
		poly_coeff = new double[1];	
		poly_coeff[0] = 0;
	}
	
	public Polynomial(double[] temp_coeff) {
		poly_coeff = temp_coeff;
	}	

	public Polynomial add (Polynomial p) {
		int length_max = Math.max(poly_coeff.length, p.poly_coeff.length);
		int length_min = Math.min(poly_coeff.length, p.poly_coeff.length);
		Polynomial new_p;

		if(poly_coeff.length >= p.poly_coeff.length){
			new_p = new Polynomial(poly_coeff);
		}
		else {
			new_p = p;
		}

		for (int i=0; i<length_min; i++) {
			new_p.poly_coeff[i] = poly_coeff[i] + p.poly_coeff[i];
		}
		return new_p;
	}

	public double evaluate (double x) {
		double total = 0;
		int length = poly_coeff.length;

		for(int i=0; i<length; i++){
			total += poly_coeff[i]*(Math.pow(x,(double)i));
		}
		
		return total;
	}

	public boolean hasRoot(double x) {
		double total = 0;
		int length = poly_coeff.length;

		for(int i=0; i<length; i++) {
			total += poly_coeff[i]*(Math.pow(x,i));
		}

		if (total == 0) return true;
		else return false;

	}
}