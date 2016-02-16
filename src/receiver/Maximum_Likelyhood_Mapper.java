package receiver;

import java.util.Random;
import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

/**
 *
 * @author Nipuna Priyamal
 */
public class Maximum_Likelyhood_Mapper extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
    
	public Maximum_Likelyhood_Mapper(){
		this.add(new ML_Map());
	}
	
	private static class ML_Map extends Filter<Complex, Complex> {
		
		public ML_Map() {
			super(8, 8);
		}

		@Override
		public void work() {
//			System.out.println("ML Mapper work method-----------------------");
			Complex[] denorm = new Complex[8];
			for (int i = 0; i < denorm.length; i++) {
				denorm[i] = pop();
//				System.out.print(denorm[i]+"\t");
			}
//			System.out.println("\nbefor call ml_out method");
//			Complex[] ML_out = Maximum_Likelyhood_Out(denorm);
			Complex[] ML_out = new Complex[8];
			
			//////////////////////////////////////////////////////////////////////
			for(int i=0; i<8; i++){
//	            System.out.println("i ="+i);
	            int realz = 0;
	            int imgez = 0;
	            double real_complex = denorm[i].getReal();
	            double imge_complex = denorm[i].getImaginary();
	            
	            int real = (int) denorm[i].getReal();
	            
	            if(real%2==0 && (real == denorm[i].getReal() || real == denorm[i].getReal()+1)){
	                int min = -9;
	                int max = 8;

	                Random r = new Random();
	                int gen_val = r.nextInt(max - min + 1) + min;
	                
	                if(gen_val == 0){gen_val++;}
	                real_complex = real + (gen_val/10);
	            }
	            
	            if(real_complex>0 && (real%2)==0){
	                realz = real+1;
	            }
	            else if(real_complex>0 && (real%2)!=0){
	                realz = real;
	            }
	            else if(real_complex<0 && (real%2)==0){
	                realz = real-1;
	            }
	            else if(real_complex<0 && (real%2)!=0){
	                realz = real;
	            }
	            
	            int imge = (int) denorm[i].getImaginary();
	            
	            if(imge%2==0 && (imge == denorm[i].getImaginary() || imge == denorm[i].getImaginary()+1)){
	                int min = -9;
	                int max = 8;

	                Random r = new Random();
	                int gen_val = r.nextInt(max - min + 1) + min;
	                
	                if(gen_val == 0){gen_val++;}
	                imge_complex = imge + (gen_val/10);
	            }
	            
	            if(imge_complex> 0 && (imge%2)==0){
	                imgez = imge+1;
	            }
	            else if(imge_complex>0 && (imge%2)!=0){
	                imgez = imge;
	            }
	            else if(imge_complex<0 && (imge%2)==0){
	                imgez = imge-1;
	            }
	            else if(imge_complex<0 && (imge%2)!=0){
	                imgez = imge;
	            }
	            
	            ML_out[i]=Complex.valueOf(realz, imgez);
	        }
			//////////////////////////////////////////////////////////////////////
			
			
//			System.out.println("after call ml_out method");
//			System.out.println("ML_out length: "+ML_out.length);
			for (int i = 0; i < ML_out.length; i++) {
//				System.out.print(ML_out[i]+"\t");
				push(ML_out[i]);
			}
//			System.out.println();
		}
		
		private static Complex[] Maximum_Likelyhood_Out(Complex[] De_Normalizer_Out){
	        System.out.println("Maximum likelyhood out method----------------");
	        Complex [] max_likelyhood_out = new Complex[6];   //6=8100
	        
	        for(int i=0; i<6; i++){
//	            System.out.println("i ="+i);
	            int realz = 0;
	            int imgez = 0;
	            double real_complex = De_Normalizer_Out[i].getReal();
	            double imge_complex = De_Normalizer_Out[i].getImaginary();
	            
	            int real = (int) De_Normalizer_Out[i].getReal();
	            
	            if(real%2==0 && (real == De_Normalizer_Out[i].getReal() || real == De_Normalizer_Out[i].getReal()+1)){
	                int min = -9;
	                int max = 8;

	                Random r = new Random();
	                int gen_val = r.nextInt(max - min + 1) + min;
	                
	                if(gen_val == 0){gen_val++;}
	                real_complex = real + (gen_val/10);
	            }
	            
	            if(real_complex>0 && (real%2)==0){
	                realz = real+1;
	            }
	            else if(real_complex>0 && (real%2)!=0){
	                realz = real;
	            }
	            else if(real_complex<0 && (real%2)==0){
	                realz = real-1;
	            }
	            else if(real_complex<0 && (real%2)!=0){
	                realz = real;
	            }
	            
	            int imge = (int) De_Normalizer_Out[i].getImaginary();
	            
	            if(imge%2==0 && (imge == De_Normalizer_Out[i].getImaginary() || imge == De_Normalizer_Out[i].getImaginary()+1)){
	                int min = -9;
	                int max = 8;

	                Random r = new Random();
	                int gen_val = r.nextInt(max - min + 1) + min;
	                
	                if(gen_val == 0){gen_val++;}
	                imge_complex = imge + (gen_val/10);
	            }
	            
	            if(imge_complex> 0 && (imge%2)==0){
	                imgez = imge+1;
	            }
	            else if(imge_complex>0 && (imge%2)!=0){
	                imgez = imge;
	            }
	            else if(imge_complex<0 && (imge%2)==0){
	                imgez = imge-1;
	            }
	            else if(imge_complex<0 && (imge%2)!=0){
	                imgez = imge;
	            }
	            
	            max_likelyhood_out[i]=Complex.valueOf(realz, imgez);
	        }
	        
	        return max_likelyhood_out;
	    } 
	}
	
       
}
