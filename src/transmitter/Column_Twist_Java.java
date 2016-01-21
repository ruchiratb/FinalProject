package transmitter;

import java.util.ArrayList;

public class Column_Twist_Java {

	public ArrayList<FEC_Frame> do_columntwist(ArrayList<FEC_Frame> frames) {
		int N_ldpc = 64800;     //FECFREAME Size
		int R=4050;
		int C=16; 
		int framecount = frames.size();
		// TODO Auto-generated method stub
		boolean[] D_in = new boolean[N_ldpc]; 			 // input
		boolean[] D_out = new boolean[N_ldpc];		     // output
		boolean[][] D_Buffer = new boolean[R][C];
		int T[]={0,2,2,2,2,3,7,15,16,20,22,22,27,27,28,32}; // Twisted parameter
		ArrayList<FEC_Frame> outputframes = new ArrayList<FEC_Frame>();
		
		for (int k = 0; k < framecount; k++) {
			FEC_Frame current_frame = frames.get(k);
			boolean[] inputbits = current_frame.FEC_frame;
			D_in = inputbits;
			//	System.out.println(A[4050]);
			for(int i=0;i<N_ldpc;i++){
				int c=  i/R;
				int r= (i+T[i/R])%R;								  // Write Operation
				D_Buffer[r][c]=D_in[i];
			}
			System.out.println(D_Buffer[0][0]);
						
			
			for(int r=0;r<R;r++){
				for(int c=0;c<C;c++){
					D_out[(r*C)+c]=D_Buffer[r][c];                   //Read Operation
					//System.out.println(D[r*c+c]);
				}
			}
			System.out.println(D_out[12]);
			System.out.println(D_Buffer[0][12]);	
			outputframes.add(new FEC_Frame(D_out));
		}
		return outputframes;
	}
	
	

}
