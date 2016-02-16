package transmitter.streamjit;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class Parity_Interleaver extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	public Parity_Interleaver(){
		this.add(new Bit_Interleave());
	}
	
	private static class Bit_Interleave extends Filter<FEC_Frame, FEC_Frame> {
		
		public Bit_Interleave() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ Parity Interleave ================");
			FEC_Frame frame = pop();
//			FEC_Frame frame2 = do_bit_interleave(frame);
			
			///////////////////////////////////////
			int N_ldpc = 64800;     //FECFREAME Size
			int K_ldpc = 32400;     //BBFRAME+BCH Size
			int Q_ldpc = 90;		// for 1/2 code rate
			
			boolean[] D_in = new boolean[N_ldpc];
			boolean[] D_out = new boolean[N_ldpc];
					
				FEC_Frame currentframe = frame;
				boolean[] inputbits = currentframe.FEC_frame;
				D_in = inputbits;
				
				for(int i=0;i<K_ldpc;i++){
					D_out[i]=D_in[(int) (i)];                              //Information bits are not interleaved
				}	
							
				for(long t=0;t<Q_ldpc;t++){
					for(long s=0;s<360;s++){
						D_out[(int) (K_ldpc+360*t+s)]=D_in[(int) (K_ldpc+Q_ldpc*s+t)]; //parity interleaving
						
					}
				}				
			
			FEC_Frame frame2 =  new FEC_Frame(D_out);
			///////////////////////////////////////
			push(frame2);
		}
		
		public static FEC_Frame do_bit_interleave(FEC_Frame frame) {
			int N_ldpc = 64800;     //FECFREAME Size
			int K_ldpc = 32400;     //BBFRAME+BCH Size
			int Q_ldpc = 90;		// for 1/2 code rate
			
			boolean[] D_in = new boolean[N_ldpc];
			boolean[] D_out = new boolean[N_ldpc];
					
				FEC_Frame currentframe = frame;
				boolean[] inputbits = currentframe.FEC_frame;
				D_in = inputbits;
				
				for(int i=0;i<K_ldpc;i++){
					D_out[i]=D_in[(int) (i)];                              //Information bits are not interleaved
				}	
							
				for(long t=0;t<Q_ldpc;t++){
					for(long s=0;s<360;s++){
						D_out[(int) (K_ldpc+360*t+s)]=D_in[(int) (K_ldpc+Q_ldpc*s+t)]; //parity interleaving
						
					}
				}				
			
			return new FEC_Frame(D_out);
		}
	}

	
	
	
}