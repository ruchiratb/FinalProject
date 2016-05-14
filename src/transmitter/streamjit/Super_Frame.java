package transmitter.streamjit;


public class Super_Frame {
	
	public T2_Frame frame1, frame2;
	public Super_Frame(T2_Frame f1, T2_Frame f2){
		this.frame1 = f1;
		this.frame2 = f2;
	}
	
	public T2_Frame get_frame1(){
		return frame1;
	}
	public T2_Frame get_frame2(){
		return frame2;
	}

/*	public static void main(String[] args) {
		int T2_frames_per_superframe, data_symbols_per_T2_frame;
		P2_Symbol p2 = new P2_Symbol();
		T2_Frame_2 t2f = new T2_Frame_2();
		T2_frames_per_superframe = p2.NUM_T2_FRAMES;
		data_symbols_per_T2_frame = t2f.getDataSymbolsPerT2Frame();
		System.out.println(T2_frames_per_superframe);
		int T2_frame_length = t2f.geP2SymbolLength()+(t2f.getDataSymbolsPerT2Frame()*8);
		int super_frame_length = T2_frame_length*T2_frames_per_superframe;
//		BitSet SuperFrame = new BitSet(super_frame_length);
		
		System.out.println("T2 frame length: "+T2_frame_length);
		T2_Frame_2 []SuperFrame = new T2_Frame_2[T2_frames_per_superframe];
		T2_Frame_2 currentFrame;
		BitSet currentFrameBits = new BitSet(T2_frame_length);
		int frameCount = 0;
		int temp;
		for (int i = 0; i < T2_frames_per_superframe; i++) {
			SuperFrame[i] = new T2_Frame_2();
		}	
		
		Complex[] data = new Complex[data_symbols_per_T2_frame];
		for (int i = 0; i < SuperFrame.length; i++) {
			System.out.println("-------------------------- T2 Frame Number: "+(i+1));
			currentFrame = SuperFrame[i];
			System.out.println("p2 symbol: "+currentFrame.geP2SymbolLength());
			
			System.out.println("data symbols:");
			data = t2f.getDataSymbols();
			System.out.println(data.length);
			for (int j = 0; j <data_symbols_per_T2_frame; j++) {
				System.out.print(data[j].magnitude()+"   ");
			}
			System.out.println();
		}
	}*/

}