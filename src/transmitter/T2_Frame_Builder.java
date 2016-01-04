package transmitter;

import org.jscience.mathematics.number.Complex;

public class T2_Frame_Builder {
	
	public T2_Frame[] getT2Frames (Complex[] symbols) {
		System.out.println("Inside T2 frame Builder: ");
		int symbol_length = symbols.length;
		System.out.println("   symbol length= "+symbol_length);
		P2_Symbol p2 = new P2_Symbol();
		int datasymbols_per_T2Frame  = p2.NUM_DATA_SYMBOLS;
		System.out.println("   datasymbols per t2 frame= "+datasymbols_per_T2Frame);
		int number_of_t2_frames = symbol_length/datasymbols_per_T2Frame;
		System.out.println("   number of t2 frames= "+number_of_t2_frames);
		T2_Frame[] t2frames = new T2_Frame[number_of_t2_frames];
		int index = 0;
		Complex[] frame_symbols = new Complex[datasymbols_per_T2Frame];
		
		for (int i = 0; i < t2frames.length; i++) {
			for (int j = 0; j < frame_symbols.length; j++) {
				frame_symbols[j] = symbols[index];
				index++;
			}
			t2frames[i] = new T2_Frame(frame_symbols);
		}
		
		return t2frames;
	}
}
