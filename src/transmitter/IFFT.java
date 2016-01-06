package transmitter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jscience.mathematics.number.Complex;

public class IFFT {
	
	public static void doIFFT(Super_Frame[] frames) {
		 int N = 16;
		 int num_of_frames = frames.length;
		 StringBuilder builder = new StringBuilder();
		 for (int j = 0; j < frames.length; j++) {
			 Super_Frame sframe = frames[j];
			 T2_Frame t1 = sframe.frame1;
			 T2_Frame t2 = sframe.frame2;
			 Complex[] x1 = t1.data;
			 Complex[] x2 = t2.data;
		     
		     show(x1, "x1");		        
		     // take inverse FFT
		     Complex[] y1 = ifft(x1);
	         show(y1, "y1 = ifft(x1)");
	         builder.setLength(0);
	         for (int i = 0; i < y1.length; i++) {
				builder.append(y1[i]+"\t");
	         }
	         
	         
	         show(x2, "x2");		        
		     // take inverse FFT
		     Complex[] y2 = ifft(x2);
	         show(y2, "y2 = ifft(x2)");
	         for (int i = 0; i < y2.length; i++) {
				builder.append(y2[i]+"\t");
		     }
	         appendToFile(builder.toString());
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
//            y[i] = y[i].scale(1.0 / N);
        	y[i] = y[i].divide(N);
        }

        return y;

    }
    
    public static Complex[] ifft() {
    	
    	int N = 16;
	    Complex[] x = new Complex[N];

	     // original data
	     for (int i = 0; i < N; i++) {
               x[i] = Complex.valueOf(i, 0);
	           x[i] = Complex.valueOf(-2*Math.random() + 1, 0);
        }
	     show(x, "x");
	      
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
