package transmitter;

public class Super_Frame_Builder {
	
	Super_Frame[] getSuperFrames(T2_Frame[] t2frames){
		System.out.println("================ Inside Super Frame Builder ================");
		int number_of_t2_frames = t2frames.length;
		P2_Symbol p2 = new P2_Symbol();
		int t2_frames_per_superframe = p2.NUM_T2_FRAMES;  // 2
		int number_of_superframes = number_of_t2_frames/t2_frames_per_superframe;
		
		Super_Frame[] superframes = new Super_Frame[number_of_superframes];
		int index = 0;
		for (int i = 0; i < superframes.length; i++) {
			superframes[i] = new Super_Frame(t2frames[index], t2frames[index+1]);
			index+=2;
		}
		return superframes;
	}
}
