package transmitter;

import org.jscience.mathematics.number.Complex;

/**
 *
 * @author Nipuna Priyamal
 */
public class Constellation_rotation {
    
    Complex[] Rotationout(Complex[] Normalizerout){
        int length = Normalizerout.length;
        System.out.println("constellation rotation length "+length);
        double angle = Math.atan(0.0625)*180*(1/Math.PI);   //atan value should be in degrees
        double theta = (Math.PI)*angle/180;
        System.out.println("theta = "+theta);
        double real = Math.cos(theta*(Math.PI)/180);
        double img = Math.sin(theta*(Math.PI)/180);
        Complex RQD = Complex.valueOf(real, img);
        System.out.println("RQD = "+RQD.getReal()+"\t"+RQD.getImaginary()+"\n");
        
        Complex rotationout[] = new Complex[length];
        for(int i=0; i<length; i++){
            if(i!=0){
               Complex re = Normalizerout[i].times(RQD);
               Complex im = Normalizerout[i-1].times(RQD); 
               
               rotationout[i] = Complex.valueOf(re.getReal(), im.getImaginary());
            }
            if(i==0){
               Complex re = Normalizerout[0].times(RQD);
               Complex im = Normalizerout[5].times(RQD); 
               
               rotationout[i] = Complex.valueOf(re.getReal(), im.getImaginary());
            }
                        
        }
        return rotationout;
        
    }
}
