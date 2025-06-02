import java.io.File;

public class Driver {
	
	/*
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		//System.out.println(p.evaluate(3));
		double [] c1 = {-2.0, -1.0};
		int[] e1 = {1, 0};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {2.0, 3.0, 10.0};
		int[] e2 = {2, 1, 0};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		Polynomial m = p1.multiply(p2);
		
		for (int i=0; i<m.poly_coeff.length;i++)
		{
			System.out.println(m.poly_coeff[i] + " " + m.exponents[i]);
		}
		
		p2.saveToFile("Hello.txt");
		
		//System.out.println("s(0.1) = " + s.evaluate(0.1));
		//if(s.hasRoot(1))
		//	System.out.println("1 is a root of s");
		//else
		//	System.out.println("1 is not a root of s");
	}  */
	
	public static void main(String [] args) {
		//Get the files to read from to input the polynomial
		File f1 = new File("inputPolynomial1.txt");
		File f2 = new File("inputPolynomial2.txt");
		
		//Initilize the Polynomials from the files
		Polynomial p1 = new Polynomial(f1);
		Polynomial p2 = new Polynomial(f2);
		
		//Multiply and add the 2 roots
		Polynomial a = p1.add(p2);
		Polynomial m = p1.multiply(p2);
		
		//Check if 0 is the root of the product of the polynomials
		if(m.hasRoot(0))
			System.out.println("0 is a root of s");
		else
			System.out.println("0 is not a root of s");
		
		//Check the evaluation value of the sum of the polynomials
		System.out.println("a(2) = " + a.evaluate(2));
		
		//Output the product and the sum of the polynomial to a new file
		a.saveToFile("outputPolySum.txt");
		m.saveToFile("outputPolyProduct");
	}
	
}