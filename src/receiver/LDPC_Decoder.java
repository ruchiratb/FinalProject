package receiver;

import java.util.Arrays;


import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class LDPC_Decoder extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	public LDPC_Decoder(){
		this.add(new decode());
	}
	
	private static class decode extends Filter<FEC_Frame, FEC_Frame> {
		
		public decode() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("LDPC decoding--------------------");
			FEC_Frame frame_with_ldpc = pop();
			boolean[] ldpc_array = frame_with_ldpc.getFEC_Data();
			boolean[] data_array = Arrays.copyOfRange(ldpc_array, 0, 32208);  // from 0 to 32207  Kbch length = 32208
			FEC_Frame data_frame = new FEC_Frame(data_array);
			push(data_frame);
		}
	}
}
