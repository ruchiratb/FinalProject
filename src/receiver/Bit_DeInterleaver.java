package receiver;

import transmitter.FEC_Frame;

public class Bit_DeInterleaver extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	public Bit_DeInterleaver(){
		this.add(new Bit_Deinterleave());
	}
	
private static class Bit_Deinterleave extends edu.mit.streamjit.api.Filter<FEC_Frame, FEC_Frame> {
		
		public Bit_Deinterleave() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("bit deinterleave--------------------");
			FEC_Frame frame = pop();
			boolean[] fec_data = frame.getFEC_Data();
			boolean[] interleaved_data = bitInterleave(fec_data);
			FEC_Frame interleaved_frame = new FEC_Frame(interleaved_data);
			push(interleaved_frame);
		}
		
		private static boolean[] bitInterleave(boolean[] fec_data) {
			int N_ldpc = 64800;     //FECFREAME Size
			int K_ldpc = 32400;     //BBFRAME+BCH Size
			int Q_ldpc = 90;		//			

			boolean[] D_out = fec_data;
			boolean[] Deint = new boolean[N_ldpc];
			
			for(int i=0;i<K_ldpc;i++){
				Deint[i]=D_out[i];
			}
			
			for(int t=0;t<Q_ldpc;t++){
				for(int s=0;s<360;s++){
					Deint[K_ldpc+Q_ldpc*s+t]=D_out[K_ldpc+360*t+s]; //parity de interleaving
					
				}
			}			
			
			return Deint;
		}
	}
}
