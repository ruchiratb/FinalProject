package channel;

import java.util.Random;

import org.jscience.mathematics.number.Complex;

public class AWGN {

	public static void main(String[] args) {
		Complex[] data = generateComplex(10);
		Complex[] out = SinglePathChannel(data);
		printComplexArray(data, "Input Data");
		System.out.println();
		printComplexArray(out, "Output Data");
//		double[] data = new double[20];
//		double[] output = new double[20];
//		Random rand = new Random();
//		double noise;
//		for (int i = 0; i < data.length; i++) {
//			data[i] = rand.nextDouble();
//			System.out.print(data[i]+" ");
//		}
//		System.out.println();
//		for (int i = 0; i < output.length; i++) {
//			noise = rand.nextGaussian();
//			output[i] = data[i] + noise;
//			System.out.print(noise+" ");			
//		}
//		System.out.println();
//		for (int i = 0; i < output.length; i++) {;
//			System.out.print(output[i]+" ");			
//		}

	}
	
	public static Complex[] SinglePathChannel (Complex[] in){
		Complex[] out = new Complex[in.length];
		Random rand = new Random();
		double real;
		double img;
		for (int i = 0; i < in.length; i++) {
			real = in[i].getReal();
			img = in[i].getImaginary();
			out[i] = Complex.valueOf(real+rand.nextGaussian(), img+rand.nextGaussian());
		}
		
		return out;
	}
	
	private static Complex[] generateComplex(int dataLength){
		Complex[] dataout = new Complex[dataLength];
		Random rand = new Random();
		for (int i = 0; i < dataout.length; i++) {
			dataout[i] = Complex.valueOf(rand.nextInt(20), rand.nextInt(20));
		}		
		
		return dataout;
	}
	
	private static void printComplexArray(Complex[] data, String desc){
		System.out.println(desc);
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]+"\t");
		}
	}

}
