package transmitter.streamjit;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

/**
 *
 * @author Nipuna Priyamal
 */
public class Normalizer extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
    
	public Normalizer(){
		this.add(new Normalize());
	}
	
	private static class Normalize extends Filter<Complex, Complex> {
		
		public Normalize() {
			super(8100, 8100);
		}

		@Override
		public void work() {			
			System.out.println("Normalizer---------------------");
			Complex out, temp;
	        for(int i=0; i<8100; i++){
	        	temp = pop();
//	        	System.out.print("\n de norm in = "+temp);
	            out = temp.divide(Math.sqrt(170));
//	            System.out.println(out);
	            push(out);
//	            System.out.println("\t\tde norm out = "+De_Normalizerout[i]);
	        }
		}
	}
    /*Complex[] Normalizerout(Complex[] Constmapout){
        int length = Constmapout.length;
        Complex Normalizerout[] = new Complex[length];
        for(int i=0; i<length; i++){
            Normalizerout[i] = Constmapout[i].divide(Math.sqrt(170));
        }
        return Normalizerout;
        
    }*/
    
}
