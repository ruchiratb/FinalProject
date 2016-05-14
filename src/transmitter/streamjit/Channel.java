package transmitter.streamjit;

import java.util.Random;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

public class Channel extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{

	public Channel(){
		this.add(new AWGN());
	}
	
	private static class AWGN extends Filter<Complex, Complex> {
		
		public AWGN() {
			super(64, 64);
		}

		@Override
		public void work() {
//			System.out.println("============ Scrambler ================");
			Complex[] x = new Complex[64];	
			for (int i = 0; i < x.length; i++) {
				x[i] = pop();
			}
			Complex[] z = addAWGN(x);			
			
			for (int i = 0; i < z.length; i++) {
				push(z[i]);
			}
		}
	}	
	
	private static Complex[] addAWGN (Complex[] in){
		Complex[] out = new Complex[in.length];
		Random rand = new Random();
		double real;
		double img;
		for (int i = 0; i < in.length; i++) {
			
			if (i%2 == 0) {
				real = in[i].getReal();
				img = in[i].getImaginary();
				out[i] = Complex.valueOf(real+rand.nextGaussian()*0.02, img+rand.nextGaussian()*0.02);
			}else {
				out[i] = in[i];
			}
			
		}
		
		return out;
	}

}
