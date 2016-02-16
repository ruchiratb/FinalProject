package receiver.java;

import org.jscience.mathematics.number.Complex;

import transmitter.streamjit.Super_Frame;
import transmitter.streamjit.T2_Frame;

public class ReceiverAgent {

	public static void main(String[] args) {
		int data_length = 65536;
		int fft_size = 64;
		
		ComplexGenerator gen = new ComplexGenerator();
		Complex[] transmitted_data = gen.generateComplex(data_length);
		
		int fft_windows = data_length/fft_size;
		System.out.println("fft windows = "+fft_windows);
		int total_super_frames = fft_windows/2;   // 2 T2 frames per super frame
		Super_Frame[] super_frames = new Super_Frame[total_super_frames];		
		FFT fft = new FFT();
		Complex[] before_fft = new Complex[fft_size];
		Complex[] after_fft;
		T2_Frame frame1, frame2;
		
		int index = 0;
		for (int i = 0; i < super_frames.length; i++) {
			for (int j = 0; j < before_fft.length; j++) {
				before_fft[i] = transmitted_data[index];
				index++;
			}
			after_fft = fft.fft(before_fft); 
			frame1 = new T2_Frame(after_fft);
			
			for (int j = 0; j < before_fft.length; j++) {
				before_fft[i] = transmitted_data[index];
				index++;
			}
			after_fft = fft.fft(before_fft); 
			frame2 = new T2_Frame(after_fft);
			
			super_frames[i] = new Super_Frame(frame1, frame2);
		}
		
		
//		print(FFT_Data_tx, "original data ---- frequency domain");
//		
//		print(transmitted_data, "transmitted data ---- time domain");
//		
//		System.out.println();
//		Channel_Single channel = new Channel_Single();
//		Complex[] received_data = channel.SinglePathChannel(transmitted_data);
//		print(received_data, "received data ---- time domain");
//		
//		//------------- FFT ----------------------
//	
//		
//		Complex[] FFT_Data = fft.fft(received_data); 
//		print(FFT_Data, "received data  ---- frequency domain -> after apply FFT");

		//========================frequency de-interleave======================
		
		//========================= T2 frames from super frames =================
		T2_Frame[] t2frames = new T2_Frame[2*super_frames.length];
		int t2_index = 0;
		Super_Frame currentFrame;
		for (int i = 0; i < super_frames.length; i++) {
			currentFrame = super_frames[i];
			
			t2frames[t2_index] = currentFrame.get_frame1();
			t2_index++;
			t2frames[t2_index] = currentFrame.get_frame2();
			t2_index++;
		}
		
		//====================== cell de-interleaver =============================
		
		
		//===================== constellation de-rotation =========================
		
	}
	
	public static void print(Complex[] data, String description){
		System.out.println("\n=========== "+description+" =============");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]+"  ");
		}
	}

}
