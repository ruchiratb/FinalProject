package receiver.java;

import org.jscience.mathematics.number.Complex;

public class Const_Derotator {

	public static Complex[] deRotate(Complex[] before_rotate){
		Complex[] after_rotate = new Complex[6];
		///////////////////////////////////////////////////////////
		double angle = Math.atan(0.0625)*180*(1/Math.PI);   //atan value should be in degrees
        double theta = (Math.PI)*angle/180;
        System.out.println("theta = "+theta);
        double real = Math.cos(theta*(Math.PI)/180);
        double img = Math.sin(theta*(Math.PI)/180);
        Complex RQD = Complex.valueOf(real, img);
        System.out.println("RQD = "+RQD.getReal()+"\t"+RQD.getImaginary()+"\n");
        
        for(int i=0; i<6; i++){
            if(i!=5){                       // 5 = (no of cells -1)
                double f_real = before_rotate[i].getReal();
                double f_imge = before_rotate[i+1].getImaginary(); 
                Complex f_temp = Complex.valueOf(f_real, f_imge);
               
                after_rotate[i] = f_temp.divide(RQD);
            }
            if(i==5){
               double f_real = before_rotate[i].getReal();
               double f_imge = before_rotate[0].getImaginary(); 
               Complex f_temp = Complex.valueOf(f_real, f_imge);
               
               after_rotate[i] = f_temp.divide(RQD);
            }
                        
        }
        
        return after_rotate;
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
