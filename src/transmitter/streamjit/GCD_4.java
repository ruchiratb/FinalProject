package transmitter.streamjit;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

public class GCD_4 extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{

	public GCD_4(){
		this.add(new pop_push());
	}
	
	private static class pop_push extends Filter<Complex, Complex> {
		
		public pop_push() {
			super(4, 4);
		}

		@Override
		public void work() {
			Complex a = pop();
			Complex b = pop();
			Complex c = pop();
			Complex d = pop();
			push(a);push(b);push(c);push(d);
		}
	}
}
