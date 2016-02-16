package receiver.java;

import org.jscience.mathematics.number.Complex;

/**
 * 
 * @author Ruchira
 *
 */
/*
 * reference: TIME DOMAIN SYNCHRONIZATION AND DECODING OF P1 SYMBOL IN DVB-T2, Mingchao Yu, Parastoo Sadeghi
 */
public class P1_Symbol_New {

	static double[] Xp = new double[1024];   // sub-carriers
	static Complex[] P = new Complex[1024];  // main part of P1
	static Complex[] X = new Complex[2048];  // time domain P1 symbol
	
	public static Complex[] get_P1_Symbol(){
		fill_Xp();
		fill_P();
		fill_X();
		return X;
	}
	
	private static void fill_Xp(){
		for (int i = 0; i < 1024; i++) {
			Xp[i] = Math.sin(2*Math.PI*i);		// sub carrier frequencies??
		}
	}
	private static void fill_P(){
		Complex second_part, temp, value;
		double factor = Math.sqrt(384);
		double angle;
		for (int i = 0; i < 1024; i++) {
			value = Complex.valueOf(0, 0);
			for (int n = 0; n < P.length; n++) {
				angle = (2*Math.PI*n*i)/1024;
				temp = Complex.valueOf(Math.cos(angle), Math.sin(angle));
				temp = temp.times(Xp[n]);
				value = value.plus(temp);
			}			
			P[i] = value.divide(factor);
		}
	}
	private static void fill_X(){
		double angle;
		double T = 0.25/1000;		// used an arbitary value.
		double fsh = 1024/T;
		Complex second_part;
		for (int i = 0; i < 2048; i++) {
			if (i<542) {
				angle = (2*Math.PI*i*fsh)/1024;
				second_part = Complex.valueOf(Math.cos(angle), Math.sin(angle));
				X[i] = P[i].times(second_part);
			} else if (i < 1566) {
				X[i] = P[i - 542];
			} else {
				angle = (2*Math.PI*i*fsh)/1024;
				second_part = Complex.valueOf(Math.cos(angle), Math.sin(angle));
				X[i] = P[i - 1024].times(second_part);
			}
		}
	}
}
