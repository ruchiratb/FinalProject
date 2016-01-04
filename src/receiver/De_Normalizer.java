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
			super(6, 6);
		}

		@Override
		public void work() {
//			System.out.println("De Normalizer---------------");
			Complex De_Normalizerout[] = new Complex[6];
	        for(int i=0; i<6; i++){
	        	Complex temp = pop();
//	        	System.out.print("\n de norm in = "+temp);
	            De_Normalizerout[i] = temp.times(Math.sqrt(170));
	            push(De_Normalizerout[i]);
//	            System.out.println("\t\tde norm out = "+De_Normalizerout[i]);
	        }
		}
	}
    
    
}
