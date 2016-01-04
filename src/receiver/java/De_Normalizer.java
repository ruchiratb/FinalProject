package receiver.java;

import org.jscience.mathematics.number.Complex;

/**
 *
 * @author Nipuna Priyamal
 */
public class De_Normalizer {
    
    Complex[] De_Normalizerout(Complex[] De_rotationout){
        
        Complex De_Normalizerout[] = new Complex[6];
        for(int i=0; i<6; i++){
            De_Normalizerout[i] = De_rotationout[i].times(Math.sqrt(170));
        }
        return De_Normalizerout;
        
    }
}
