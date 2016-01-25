package receiver;

import org.jscience.mathematics.number.Complex;

/**
 *
 * @author Nipuna Priyamal
 */
public class Constellation_Derotation extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
	
	public Constellation_Derotation(){
		this.add(new DeRotator());
	}
    
private static class DeRotator extends edu.mit.streamjit.api.Filter<Complex, Complex> {
		
		public DeRotator() {
			super(6, 6);
		}

		@Override
		public void work() {
			System.out.println("----------constellation de-rotation-----------");
			Complex[] before_rotate = new Complex[6];
			for (int i = 0; i < before_rotate.length; i++) {
				before_rotate[i] = pop();
			}
			Complex[] after_rotate = De_Rotationout(before_rotate);
			for (int i = 0; i < after_rotate.length; i++) {
				push(after_rotate[i]);
			}
		}
		
		private static Complex[] De_Rotationout(Complex[] g){
	        
	        double angle = Math.atan(0.0625)*180*(1/Math.PI);   //atan value should be in degrees
	        double theta = (Math.PI)*angle/180;
	        System.out.println("theta = "+theta);
	        double real = Math.cos(theta*(Math.PI)/180);
	        double img = Math.sin(theta*(Math.PI)/180);
	        Complex RQD = Complex.valueOf(real, img);
	        System.out.println("RQD = "+RQD.getReal()+"\t"+RQD.getImaginary()+"\n");
	        System.gc();
	        Complex de_rotationout[] = new Complex[6];
	        
	        for(int i=0; i<6; i++){
	            if(i!=5){                       // 5 = (no of cells -1)
	                double f_real = g[i].getReal();
	                double f_imge = g[i+1].getImaginary(); 
	                Complex f_temp = Complex.valueOf(f_real, f_imge);
	               
	               de_rotationout[i] = f_temp.divide(RQD);
	            }
	            if(i==5){
	               double f_real = g[i].getReal();
	               double f_imge = g[0].getImaginary(); 
	               Complex f_temp = Complex.valueOf(f_real, f_imge);
	               
	               de_rotationout[i] = f_temp.divide(RQD);
	            }
	                        
	        }
	        
	        return de_rotationout;
	        
	    }
	}
	
    
    
    
}
