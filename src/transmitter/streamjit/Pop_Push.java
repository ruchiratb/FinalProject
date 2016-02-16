package transmitter.streamjit;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

public class Pop_Push extends edu.mit.streamjit.api.Pipeline<Super_Frame, Complex>{

	public Pop_Push(){
		this.add(new pop_push());
	}
	
	private static class pop_push extends Filter<Super_Frame, Complex> {
		
		public pop_push() {
			super(1, 128);
		}

		@Override
		public void work() {
//			StringBuilder builder = new StringBuilder();
			Super_Frame sframe = pop();
			T2_Frame frame1 = sframe.frame1;
			T2_Frame frame2 = sframe.frame2;
			
			Complex[] data1 = frame1.data;
			Complex[] data2 = frame2.data;
			
			for (int i = 0; i < data1.length; i++) {				
				push(data1[i]);
			}
			for (int i = 0; i < data2.length; i++) {
				push(data2[i]);
			}
		}
	}
}
