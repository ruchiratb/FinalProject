package receiver.java;

import java.util.Random;

import org.jscience.mathematics.number.Complex;

public class ComplexGenerator {	
	
	public static Complex[] generateComplex(int dataLength){
		Complex[] dataout = new Complex[dataLength];
		Random rand = new Random();
		for (int i = 0; i < dataout.length; i++) {
			dataout[i] = Complex.valueOf(rand.nextInt(20), rand.nextInt(20));
		}		
		
		return dataout;
	}
}
