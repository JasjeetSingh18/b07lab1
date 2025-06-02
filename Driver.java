import java.io.File;

public class Driver {
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