package transmitter.streamjit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

// show method calls are commented

public class IFFT extends edu.mit.streamjit.api.Pipeline<Super_Frame, Complex>{
	
	public IFFT(){
		this.add(new InverseFourier());
	}
	
	private static class InverseFourier extends Filter<Super_Frame, Complex> {
		
		public InverseFourier() {
			super(1, 128);
		}

		@Override
		public void work() {
			System.out.println("============ IFFT ================");
			Super_Frame frame = pop();
			 T2_Frame t1 = frame.frame1;
			 T2_Frame t2 = frame.frame2;
			 Complex[] x1 = t1.data;
			 Complex[] x2 = t2.data;
			 
/*			 Complex[] X = new Complex[32768];
			 for (int i = 0; i < x1.length; i++) {
				X[i] = x1[i];
			 }
			 for (int i = x2.length; i < X.length; i++) {
				X[i] = x2[i - x2.length];
			 }
			 Complex[] Y = ifft(X);
			 for (int i = 0; i < Y.length; i++) {
				push(Y[i]);
			 }*/
			 
			 Complex[] y1 = ifft(x1);
			 for (int i = 0; i < y1.length; i++) {
				push(y1[i]);
			 }
			 
			 Complex[] y2 = ifft(x2);
			 for (int i = 0; i < y2.length; i++) {
				push(y2[i]);
			}
		}
		
		 public static Complex[] ifft(Complex[] x) {
		        int N = x.length;
		        Complex[] y = new Complex[N];

		        // take conjugate
		        for (int i = 0; i < N; i++) {
		            y[i] = x[i].conjugate();
		        }

		        // compute forward FFT
		        y = fft(y);

		        // take conjugate again
		        for (int i = 0; i < N; i++) {
		            y[i] = y[i].conjugate();
		        }

		        // divide by N
		        for (int i = 0; i < N; i++) {
//		            y[i] = y[i].scale(1.0 / N);
		        	y[i] = y[i].divide(N);
		        }

		        return y;

		    }
		 

			
		    public static Complex[] fft(Complex[] x) {
		        int N = x.length;

		        // base case
		        if (N == 1) return new Complex[] { x[0] };

		        // radix 2 Cooley-Tukey FFT
		        if (N % 2 != 0) {
		            throw new IllegalArgumentException("N is not a power of 2");
		        }

		        // fft of even terms
		        Complex[] even = new Complex[N/2];
		        for (int k = 0; k < N/2; k++) {
		            even[k] = x[2*k];
		        }
		        Complex[] q = fft(even);

		        // fft of odd terms
		        Complex[] odd  = even;  // reuse the array
		        for (int k = 0; k < N/2; k++) {
		            odd[k] = x[2*k + 1];
		        }
		        Complex[] r = fft(odd);

		        // combine
		        Complex[] y = new Complex[N];
		        for (int k = 0; k < N/2; k++) {
		            double kth = -2 * k * Math.PI / N;
		            Complex wk = Complex.valueOf(Math.cos(kth), Math.sin(kth));
		            y[k]       = q[k].plus(wk.times(r[k]));
		            y[k + N/2] = q[k].minus(wk.times(r[k]));
		        }
		        return y;
		    }
		    
	}
/*	
	static Complex[] doIFFT(Complex[] x) {
		 int N = 16;
		 Complex[] ifft_data = new Complex[2];
		 StringBuilder builder = new StringBuilder();
		 
		     
//		     show(x, "x");		        
		     // take inverse FFT
		     Complex[] y = ifft(x);
//	         show(y, "y = ifft(x)");
	         
	         builder.setLength(0);
	         for (int i = 0; i < y1.length; i++) {
				builder.append(y1[i]+"\t");
	         }
	         
	         appendToFile(builder.toString());
	     
	         return y;
	}
*/	
	
/*	
    public static Complex[] ifft(Complex[] x) {
        int N = x.length;
        Complex[] y = new Complex[N];

        // take conjugate
        for (int i = 0; i < N; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < N; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by N
        for (int i = 0; i < N; i++) {
//            y[i] = y[i].scale(1.0 / N);
        	y[i] = y[i].divide(N);
        }

        return y;

    }
  */  
    
    
/*    
    public static Complex[] ifft() {
    	
    	int N = 16;
	    Complex[] x = new Complex[N];

	     // original data
	     for (int i = 0; i < N; i++) {
               x[i] = Complex.valueOf(i, 0);
	           x[i] = Complex.valueOf(-2*Math.random() + 1, 0);
        }
//	     show(x, "x");
	      
        Complex[] y = new Complex[N];

        // take conjugate
        for (int i = 0; i < N; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < N; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by N
        for (int i = 0; i < N; i++) {
//            y[i] = y[i].scale(1.0 / N);
        	y[i] = y[i].divide(N);
        }

        return y;

    }
    
	*/
	
/*	
    public static Complex[] fft(Complex[] x) {
        int N = x.length;

        // base case
        if (N == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (N % 2 != 0) {
            throw new IllegalArgumentException("N is not a power of 2");
        }

        // fft of even terms
        Complex[] even = new Complex[N/2];
        for (int k = 0; k < N/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd  = even;  // reuse the array
        for (int k = 0; k < N/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        Complex[] y = new Complex[N];
        for (int k = 0; k < N/2; k++) {
            double kth = -2 * k * Math.PI / N;
            Complex wk = Complex.valueOf(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + N/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }
 */   
    private static void appendToFile(String text){
		try(
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transmitter_complex_out.txt", true)))) {
				System.out.println("appending....");
			    out.println(text);
		}catch (IOException e) {
			    e.printStackTrace();
		}
	}
    
    // display an array of Complex numbers to standard output
    private static void show(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
        	System.out.println(x[i]);
        }
        System.out.println();
    }
}
