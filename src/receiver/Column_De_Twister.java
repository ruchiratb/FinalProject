package receiver;

import java.util.Arrays;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class Column_De_Twister extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	public Column_De_Twister(){
		this.add(new Detwister());
	}
	
	private static class Detwister extends Filter<FEC_Frame, FEC_Frame> {
		
		public Detwister() {
			super(1,1);  // 32208-80
		}

		@Override
		public void work() {
			System.out.println("De Twister-------------");
			FEC_Frame frame = pop();
//			FEC_Frame frame_out = detwist(frame);
			
			////////////////////////////////
			int N_ldpc = 64800;     //FECFREAME Size
			int R=4050;
			int C=16; 
			// TODO Auto-generated method stub
			
			boolean[] D_out = frame.FEC_frame;		     // interleaved input
			
			int T[]={0,2,2,2,2,3,7,15,16,20,22,22,27,27,28,32}; // Twisted parameter

			boolean[][] D_Buffer_1 = new boolean[R][C];                     //Parameters for de interleaving
			boolean[] D_Deint = new boolean[N_ldpc];                        //Deinterleaved output
			
			for(int i=0;i<N_ldpc;i++){
				int c=i%C;
				int r=i/C;                                          //Write Operation
				D_Buffer_1[r][c]=D_out[i];
			}
			
			for(int c=0;c<C;c++){
				for(int r=0;r<R;r++){                                //Read operation
					D_Deint[r+(c*R)]=D_Buffer_1[(r+T[c])%R][c];
				}
			}
			
			FEC_Frame frame_out =  new FEC_Frame(D_Deint);
			///////////////////////////////
			push(frame_out);
		}
		
		public static FEC_Frame detwist(FEC_Frame frame) {
			int N_ldpc = 64800;     //FECFREAME Size
			int R=4050;
			int C=16; 
			// TODO Auto-generated method stub
			
			boolean[] D_out = frame.FEC_frame;		     // interleaved input
			
			int T[]={0,2,2,2,2,3,7,15,16,20,22,22,27,27,28,32}; // Twisted parameter

			boolean[][] D_Buffer_1 = new boolean[R][C];                     //Parameters for de interleaving
			boolean[] D_Deint = new boolean[N_ldpc];                        //Deinterleaved output
			
			for(int i=0;i<N_ldpc;i++){
				int c=i%C;
				int r=i/C;                                          //Write Operation
				D_Buffer_1[r][c]=D_out[i];
			}
			
			for(int c=0;c<C;c++){
				for(int r=0;r<R;r++){                                //Read operation
					D_Deint[r+(c*R)]=D_Buffer_1[(r+T[c])%R][c];
				}
			}
			
			return new FEC_Frame(D_Deint);
		}
	}
}
