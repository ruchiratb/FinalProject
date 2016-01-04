package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class BitsFromFEC extends edu.mit.streamjit.api.Pipeline<FEC_Frame, Byte>{

	public BitsFromFEC(){
		
	}
	
	private static class BitExtracter extends Filter<FEC_Frame, Byte> {
		
		public BitExtracter() {
			super(1, 32128);
		}

		@Override
		public void work() {
			FEC_Frame frame = pop();
			boolean[] data = frame.getFEC_Data();
			for (int i = 0; i < data.length; i++) {
				boolean temp = data[i];
				if (temp) {
					push((byte) 1);
				} else {
					push((byte) 0);
				}
			}			
		}
	}
}
