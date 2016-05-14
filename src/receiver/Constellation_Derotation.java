package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.RoundrobinJoiner;
import edu.mit.streamjit.api.RoundrobinSplitter;
import edu.mit.streamjit.api.Splitjoin;

/**
 *
 * @author Nipuna Priyamal
 */
public class Constellation_Derotation extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
	
	@SuppressWarnings("unchecked")
	public Constellation_Derotation(){
//		this.add(new DeRotator());
		this.add(
				new Splitjoin<Complex,Complex>(
							new RoundrobinSplitter<Complex>(8100),
							new RoundrobinJoiner<Complex>(8100),
							new DeRotator(), new DeRotator()
							
				)
		);
	}
    
private static class DeRotator extends edu.mit.streamjit.api.Filter<Complex, Complex> {
		
		public DeRotator() {
			super(8100, 8100);
		}

		@Override
		public void work() {
			int length = 8100;
//			System.out.println("constellation de-rotation-----------");
			Complex[] before_rotate = new Complex[length];
			for (int i = 0; i < before_rotate.length; i++) {
				before_rotate[i] = pop();
//				System.out.println("before rotate: "+before_rotate[i]);
			}
//			Complex[] after_rotate = De_Rotationout(before_rotate);
			Complex[] after_rotate = new Complex[length];
			///////////////////////////////////////////////////////////
			double angle = Math.atan(0.0625)*180*(1/Math.PI);   //atan value should be in degrees
	        double theta = (Math.PI)*angle/180;
//	        System.out.println("theta = "+theta);
	        double real = Math.cos(theta*(Math.PI)/180);
	        double img = Math.sin(theta*(Math.PI)/180);
	        Complex RQD = Complex.valueOf(real, img);
//	        System.out.println("RQD = "+RQD.getReal()+"\t"+RQD.getImaginary()+"\n");
	        
	        for(int i=0; i<length; i++){
	            if(i!=length-1){                       // 5 = (no of cells -1)
	                double f_real = before_rotate[i].getReal();
	                double f_imge = before_rotate[i+1].getImaginary(); 
	                Complex f_temp = Complex.valueOf(f_real, f_imge);
	               
	                after_rotate[i] = f_temp.divide(RQD);
	            }
	            if(i==length-1){
	               double f_real = before_rotate[i].getReal();
	               double f_imge = before_rotate[0].getImaginary(); 
	               Complex f_temp = Complex.valueOf(f_real, f_imge);
	               
	               after_rotate[i] = f_temp.divide(RQD);
	            }
	                        
	        }
	        
			////////////////////////////////////////////////////////////
			for (int i = 0; i < after_rotate.length; i++) {
				push(after_rotate[i]);
			}
		}
		
		private static Complex[] De_Rotationout(Complex[] g){
	        int length = 8100;
	        double angle = Math.atan(0.0625)*180*(1/Math.PI);   //atan value should be in degrees
	        double theta = (Math.PI)*angle/180;
	        System.out.println("theta = "+theta);
	        double real = Math.cos(theta*(Math.PI)/180);
	        double img = Math.sin(theta*(Math.PI)/180);
	        Complex RQD = Complex.valueOf(real, img);
	        System.out.println("RQD = "+RQD.getReal()+"\t"+RQD.getImaginary()+"\n");
	        System.gc();
	        Complex de_rotationout[] = new Complex[6];
	        
	        for(int i=0; i<length; i++){
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
