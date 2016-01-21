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
			super(1, 1);
		}

		@Override
		public void work() {
			Complex cellin = pop();
			Complex cellout = cellin.divide(Math.sqrt(170));
			push(cellout);
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
