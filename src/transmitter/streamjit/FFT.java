package transmitter.streamjit;

import org.jscience.mathematics.number.Complex;

/*
 * source: http://algs4.cs.princeton.edu/99scientific/FFT.java
 */
public class FFT {
	
	public static void main(String[] args) {
		 int N = 64;
	        Complex[] x = new Complex[N];

	        // original data
//	        for (int i = 0; i < N; i++) {
//	            x[i] = Complex.valueOf(i, 0);
//	            x[i] = Complex.valueOf(-2*Math.random() + 1, 0);
//	        }
//	        show(x, "x");
	        
	     // FFT of original data
//	        Complex[] y = fft(x);
//	        show(y, "y = fft(x)");
	        
	        IFFT iff = new IFFT();
	        Complex[] z = fft(iff.ifft());
	        
	        show(z, "z");
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
