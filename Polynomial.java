import java.io.File;    //To accept the file
import java.util.Scanner;  //To read from the file
import java.io.FileNotFoundException; //Catch if an exception if the file can't be read from
import java.io.IOException; //Catch an excpetion if cannot write to file
import java.lang.String;  //String class
import java.lang.Integer; //Integer class
import java.lang.Double; //Double class;
import java.io.FileWriter; //Write to file



public class Polynomial {

	double[] poly_coeff;
	int[] exponents;
	
	public Polynomial() {
		poly_coeff = new double[1];	
		exponents = new int[1];
		exponents[0] = 0;
		poly_coeff[0] = 0;
	}
	
	public Polynomial(double[] temp_coeff, int[] temp_exponents) {
		poly_coeff = temp_coeff;
		exponents = temp_exponents;
	}	
	
	
	public Polynomial (File f)
	{
		if (f.exists() == false)
		{
			poly_coeff = new double[1];	
			exponents = new int[1];
			exponents[0] = 0;
			poly_coeff[0] = 0;
			return;
		}
		
		try 
		{
			Scanner input = new Scanner(f);
			int length = 1; 
			
			String line = input.nextLine();  //Read the file for the polynomial and store it
			
			//Add a plus to the beignning of the string if it doesn't already have one
			if (!(line.startsWith("+")) || !(line.startsWith("-"))) 
			{
				line = "+" + line;
			}
			
			//Count how many terms are in the string
			for (int i = 1; i < line.length(); i++) 
			{
				char c = line.charAt(i);
				if (c == '+' || c == '-') 
				{
					length++;
				}
			}
			
			//Make the arrays for the coeff and the exponents
			poly_coeff = new double[length];
			exponents  = new int[length];
			
			int index = 0;
			int start = 0; 

			for (int i = 1; i < line.length(); i++) {
				char c = line.charAt(i);
				if (c == '+' || c == '-') {
					String term = line.substring(start, i);
					getTerm(term, index);
					index++;
					start = i;
				}
			}
			
			String lastTerm = line.substring(start);
			getTerm(lastTerm, index);
			index++;
			
			input.close();
		}
		catch (FileNotFoundException e) 
		{
			poly_coeff = new double[1];	
			exponents = new int[1];
			exponents[0] = 0;
			poly_coeff[0] = 0;
		}
	}
	
	private void getTerm(String term, int idx) 
	{
        int xIndex = term.indexOf('x');
        double coeff;
        int exp;

        if (xIndex < 0) {
            coeff = Double.parseDouble(term);
            exp   = 0;
        } else {
            String coeffTerm = term.substring(0, xIndex); 
            coeff = Double.parseDouble(coeffTerm);

            String expTerm = term.substring(xIndex + 1);
            exp = Integer.parseInt(expTerm);
        }

        poly_coeff[idx] = coeff;
        exponents[idx]  = exp;
    }
	
	
	public Polynomial copyOfPoly (int length)
	{
		//Make a new array with the appropriate size
		double[] new_coeff = new double[length];
		int[] new_exponents= new int[length];
		
		for (int i=0; i<length;i++)
		{
			new_coeff[i] = this.poly_coeff[i];
			new_exponents[i] = this.exponents[i];
		}
		
		//set the the new arrays to the object
		this.poly_coeff=new_coeff;
		this.exponents=new_exponents;
		
		return this;  //return the resulting polynomial
	}

	public Polynomial add(Polynomial p) {
		int length = this.poly_coeff.length + p.poly_coeff.length;
		Polynomial new_p = new Polynomial();
		
		//Set the size of the coefficient and exponents array
		new_p.poly_coeff = new double[length];
		new_p.exponents = new int[length];
		
		//Add a end of the array delimeter in the expnoents array
		new_p.exponents[0] = -1;
		int index = 0;
		
		//Boolean to check if the variable has been added to the new array
		boolean added = false;
		
		//Compare the two arrays (the calling object and the passed argument)
		for (int i = 0; i<this.poly_coeff.length; i++)
		{
			added=false;
			for (int j=0; j<p.exponents.length; j++)
			{
				if (this.exponents[i] == p.exponents[j])
				{
					new_p.exponents[index] = exponents[i];
					new_p.poly_coeff[index] = this.poly_coeff[i] + p.poly_coeff[j];
					index++;
					new_p.exponents[index]=-1;
					
					added=true;
					break;
				}
			}
			
			//If the exponent has not been seen in the second array, added it to the new polynomial
			if (!added)
			{
				new_p.exponents[index]=this.exponents[i];
				new_p.poly_coeff[index]=this.poly_coeff[i];
				index++;
				new_p.exponents[index]=-1;
			}
		}
		
		
		//Compare the new object and the calling object
		for (int i=0; i<p.poly_coeff.length; i++)
		{
			added=false;
			for (int j=0; j<index; j++)
			{
				if (p.exponents[i] == new_p.exponents[j])
				{
					added=true;
					break;
				}
			}
			
			if (!added)
			{
				new_p.exponents[index]=p.exponents[i];
				new_p.poly_coeff[index]=p.poly_coeff[i];
				index++;
				new_p.exponents[index]=-1;
			}
		}
		
		return new_p.copyOfPoly(index);  //return the resulting polynomial
	}

	public double evaluate (double x) {
		double total = 0;
		int length = this.poly_coeff.length;

		for(int i=0; i<length; i++){
			total += poly_coeff[i]*(Math.pow(x,exponents[i]));
		}
		
		return total;
	}

	public boolean hasRoot(double x) {
		double total = 0;
		int length = poly_coeff.length;

		for(int i=0; i<length; i++){
			total += poly_coeff[i]*(Math.pow(x,exponents[i]));
		}

		if (total == 0) return true;
		else return false;
	}
	
	public Polynomial multiply(Polynomial p) {
		int length = (this.poly_coeff.length)*(p.poly_coeff.length);
		double[] new_coeff = new double[length];
		int[] new_exponents = new int[length];
		
		//Use foil to get the expaneded polynomial when multiplying two polynomials
		for(int i=0, counter=0; i<this.poly_coeff.length; i++)
		{
			for(int j=0; j<p.poly_coeff.length; j++)
			{
				new_coeff[counter] = this.poly_coeff[i]*p.poly_coeff[j];
				new_exponents[counter] = this.exponents[i]+p.exponents[j];
				counter++;
			}
		}
		
		double count = 0;
		int index = 0;
		double[] coeff = new double[length];
		int[] exponent = new int[length];
		
		//Parse the exponents array to look for redundant exponents
		for (int i=0; i<length; i++)
		{
			//skip the loop iteration if the exponent is -1
			if (new_exponents[i] == -1) continue;
			
			count = new_coeff[i];
			for (int j=0; j<length; j++)
			{
				if (i != j && new_exponents[i] == new_exponents[j])
				{
					count += new_coeff[j];
					new_exponents[j] = -1;
				}
			}
			
			//Add the term if the coeff is not 0
			if (count != 0)
			{
				coeff[index] = count;
				exponent[index] = new_exponents[i];
				index++;
			}
		}
		
		//Make a new Polynomial with the new coeff and exponents and return
		Polynomial new_p = new Polynomial(coeff, exponent);
		
		return new_p.copyOfPoly(index);
	}
	
	public void saveToFile (String fileName)
	{
		String polynomial = "";
		
		for (int i=0, length = this.poly_coeff.length; i<length; i++)
		{
			if (exponents[i] == 0)
			{
				polynomial = polynomial + Double.toString(this.poly_coeff[i]);
			}
			else 
			{
				polynomial = polynomial + (Double.toString(this.poly_coeff[i]) + "x" + Integer.toString(this.exponents[i]));
			}
			if (i+1 < length)
			{
				if (this.poly_coeff[i+1] >= 0.0)
				{
					polynomial = polynomial + "+";
				}
			}
		}
		
		try 
		{
			FileWriter output = new FileWriter (fileName, false);
			output.write(polynomial);
			output.close();
		}
		catch (IOException e2)
		{
			System.out.println("Error. Cannot write to file.");
		}
		return;
	}
		
}