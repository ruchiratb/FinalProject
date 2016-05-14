package transmitter.streamjit;


import edu.mit.streamjit.api.Filter;
import transmitter.streamjit.FEC_Frame;

public class Column_Twist extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{

	public Column_Twist(){
		this.add(new Twister());
	}
	
	private static class Twister extends Filter<FEC_Frame, FEC_Frame> {
		
		public Twister() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ Column Twist ================");
			FEC_Frame frame = pop();
			FEC_Frame frame2 = do_columntwist(frame);
			push(frame2);
		}
	}
	public static FEC_Frame do_columntwist(FEC_Frame frame) {
		int N_ldpc = 64800;     //FECFREAME Size
		int R=4050;
		int C=16; 
		// TODO Auto-generated method stub
		boolean[] D_in = new boolean[N_ldpc]; 			 // input
		boolean[] D_out = new boolean[N_ldpc];		     // output
		boolean[][] D_Buffer = new boolean[R][C];
		int T[]={0,2,2,2,2,3,7,15,16,20,22,22,27,27,28,32}; // Twisted parameter
		
			FEC_Frame current_frame = frame;
			boolean[] inputbits = current_frame.FEC_frame;
			D_in = inputbits;
			//	System.out.println(A[4050]);
			for(int i=0;i<N_ldpc;i++){
				int c=  i/R;
				int r= (i+T[i/R])%R;								  // Write Operation
				D_Buffer[r][c]=D_in[i];
			}
//			System.out.println(D_Buffer[0][0]);
						
			
			for(int r=0;r<R;r++){
				for(int c=0;c<C;c++){
					D_out[(r*C)+c]=D_Buffer[r][c];                   //Read Operation
					//System.out.println(D[r*c+c]);
				}
			}
//			System.out.println(D_out[12]);
//			System.out.println(D_Buffer[0][12]);	
		
			System.out.println("\ncolumn twist");
			for (int i = 0; i < 100; i++) {
				if (D_out[i] == true) {
					System.out.print("1");
				}else {
					System.out.print("0");
				}
				
			}
			System.out.println();
		return new FEC_Frame(D_out);
	}
	
	

}
