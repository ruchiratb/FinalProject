package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.streamjit.Super_Frame;
import transmitter.streamjit.T2_Frame;


public class Pop_Push extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{

	public Pop_Push(){
		this.add(new pop_push());
	}
	
	private static class pop_push extends Filter<Complex, Complex> {
		
		public pop_push() {
			super(64, 64);
		}

		@Override
		public void work() {
			Complex a;
			for (int i = 0; i < 64; i++) {
				a = pop();
				push(a);
			}
		}
	}
}
