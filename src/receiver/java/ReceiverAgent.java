package receiver.java;

import org.jscience.mathematics.number.Complex;

public class ReceiverAgent {

	public static void main(String[] args) {
		int data_length = 32;
		ComplexGenerator gen = new ComplexGenerator();
		Complex[] transmitted_data = gen.generateComplex(data_length);
		
		FFT fft = new FFT();
		Complex[] FFT_Data_tx = fft.fft(transmitted_data); 
		print(FFT_Data_tx, "original data ---- frequency domain");
		
		print(transmitted_data, "transmitted data ---- time domain");
		
		System.out.println();
		Channel_Single channel = new Channel_Single();
		Complex[] received_data = channel.SinglePathChannel(transmitted_data);
		print(received_data, "received data ---- time domain");
		
		//------------- FFT ----------------------
	
		
		Complex[] FFT_Data = fft.fft(received_data); 
		print(FFT_Data, "received data  ---- frequency domain -> after apply FFT");

	}
	
	public static void print(Complex[] data, String description){
		System.out.println("\n=========== "+description+" =============");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]+"  ");
		}
	}

}
