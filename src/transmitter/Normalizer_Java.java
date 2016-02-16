package transmitter;

import org.jscience.mathematics.number.Complex;

/**
 *
 * @author Nipuna Priyamal
 */
public class Normalizer_Java {
    
    Complex[] Normalizerout(Complex[] Constmapout){
        int length = Constmapout.length;
        Complex Normalizerout[] = new Complex[length];
        for(int i=0; i<length; i++){
            Normalizerout[i] = Constmapout[i].divide(Math.sqrt(170));
            System.out.println(Normalizerout[i]);
        }
        return Normalizerout;
        
    }
    
}
