package transmitter.streamjit;

import java.util.Random;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

public class Rician_Channel extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{

	public Rician_Channel(){
		this.add(new Channel());
	}
	
	private static class Channel extends Filter<Complex, Complex> {
		
		public Channel() {
			super(64, 64);
		}

		@Override
		public void work() {
//			System.out.println("============ Scrambler ================");
			Complex[] x = new Complex[64];	
			for (int i = 0; i < x.length; i++) {
				x[i] = pop();
			}
			p0 = getp0(power);
//			System.out.println("p0 ="+p0);
			power[0] = p0;
				
			Complex[] y = rician_effect(x);
			Complex[] z = addAWGN(y);
			
			print(x, "x values");
			print(y, "y values");
			print(z, "z values_after awgn");
			
			for (int i = 0; i < z.length; i++) {
				push(z[i]);
			}
		}
	}
	
	static int paths = 10; // LOS + 9 reflected paths;
	static double power[] = { 1, 0.057, 0.176, 0.407, 0.303, 0.258, 0.062, 0.150, 0.051, 0.185};  	// relative powers
	static double time[]  = {0, 1.003, 5.422, 0.518, 2.751, 0.602, 1.016, 0.143, 0.153, 3.324};  		// us
	static double phase[] = {0, 4.855, 3.419, 5.864, 2.215, 3.758, 5,4303.952, 1.093, 5.775 };	// rad
	
	static double p0;
	static double sum_of_squares;
	static final int RICIAN_FACTOR = 10;
/*
	
	public static void main(String[] args) {
		
		The Rician factor K, which is the ratio of the power of the direct path (the line of sight ray) to the 
		reflected paths
				
		// 0 index corresponding to LOS
//		power = { 1, 0.057, 0.176, 0.407, 0.303, 0.258, 0.062, 0.150, 0.051, 0.185};  	// relative powers
//		time[] = {0, 1.003, 5.422, 0.518, 2.751, 0.602, 1.016, 0.143, 0.153, 3.324};  		// us
//		phase[] = {0, 4.855, 3.419, 5.864, 2.215, 3.758, 5,4303.952, 1.093, 5.775 };	// rad
		
		p0 = getp0(power);
		System.out.println("p0 ="+p0);
		power[0] = p0;
		
		int inputsize = 20;
		Complex[] x = generateComplex(inputsize);		
		Complex[] y = rician_effect(x);
		Complex[] z = addAWGN(y);
		
		print(x, "x values");
		print(y, "y values");
		print(z, "z values_after awgn");
	}
	*/
	private static double getp0(double[] p){
		double p0;
		sum_of_squares = 0;
		
		for (int i = 1; i < p.length; i++) {
			sum_of_squares += Math.pow(p[i], 2 );
		}
		p0 = Math.sqrt(sum_of_squares*RICIAN_FACTOR);
		
		return p0;
	}
	
	private static Complex[] generateComplex(int dataLength){
		Complex[] dataout = new Complex[dataLength];
		Random rand = new Random();
		for (int i = 0; i < dataout.length; i++) {
			dataout[i] = Complex.valueOf(rand.nextInt(10), rand.nextInt(10));
		}		
		
		return dataout;
	}
	
	private static Complex[] rician_effect(Complex[] x){
		double den;		
		Complex num;
		Complex[] y = new Complex[x.length];
		Complex sum_part = Complex.valueOf(0,0);
		Complex temp, ejtheta;
		for (int i = 1; i < paths; i++) {
			ejtheta = Complex.valueOf(Math.cos(phase[i]), -1*Math.sin(phase[i]));
			temp = ejtheta.times(x[i]).times(power[i]);
//			temp = ejtheta.times(x[i]);
//			temp = temp.times(power[i]);
			sum_part = sum_part.plus(temp);
		}
		
		for (int i = 0; i < y.length; i++) {
			num = (x[i].times(p0)).plus(sum_part);
			den = Math.sqrt(sum_of_squares);
			y[i] = num.divide(den);
		}
		
		return y;		
	}
	
	private static Complex[] addAWGN (Complex[] in){
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
	
	private static void print(Complex[] data, String text){
		System.out.println("-----------"+text+"-------------");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}

}
