package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

/**
 *
 * @author Nipuna Priyamal
 */
public class De_Normalizer extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
    
	public De_Normalizer(){
		this.add(new De_Normalizerout());
	}
	
    
    private static class De_Normalizerout extends Filter<Complex, Complex> {
		
		public De_Normalizerout() {
			super(8100, 8100);
		}

		@Override
		public void work() {
			System.out.println("De Normalizer---------------");
			Complex out;
	        for(int i=0; i<8100; i++){
	        	Complex temp = pop();
//	        	System.out.print("\n de norm in = "+temp);
	            out = temp.times(Math.sqrt(170));
	            push(out);
//	            System.out.println("\t\tde norm out = "+De_Normalizerout[i]);
	        }
		}
	}
    
    
}
