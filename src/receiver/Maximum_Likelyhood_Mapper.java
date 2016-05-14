package receiver;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.RoundrobinJoiner;
import edu.mit.streamjit.api.RoundrobinSplitter;
import edu.mit.streamjit.api.Splitjoin;

/**
 *
 * @author Nipuna Priyamal
 */
public class Maximum_Likelyhood_Mapper extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
    
	@SuppressWarnings("unchecked")
	public Maximum_Likelyhood_Mapper(){
//		this.add(new ML_Map());
		this.add(
				new Splitjoin<Complex,Complex>(
							new RoundrobinSplitter<Complex>(8100),
							new RoundrobinJoiner<Complex>(8100),
							new ML_Map(), new ML_Map(),new ML_Map(), new ML_Map()
							
				)
		);
	}
	
	private static class ML_Map extends Filter<Complex, Complex> {
		
		public ML_Map() {
			super(8100, 8100);
		}

		@Override
		public void work() {
//			System.out.println("ML Mapper work method-----------------------");
			Complex[] De_Normalizer_Out = new Complex[8100];
			for (int i = 0; i < De_Normalizer_Out.length; i++) {
				De_Normalizer_Out[i] = pop();
//				System.out.print(denorm[i]+"\t");
			}
//			System.out.println("\nbefor call ml_out method");
//			Complex[] ML_out = Maximum_Likelyhood_Out(denorm);
			Complex[] max_likelyhood_out = new Complex[8100];
			
			//////////////////////////////////////////////////////////////////////
			for(int i=0; i<8100; i++){  //8100
	            
	            int realz = 0;
	            int imgez = 0;
	            double real_complex = De_Normalizer_Out[i].getReal();
	            double imge_complex = De_Normalizer_Out[i].getImaginary();
	            
	            int real = (int) De_Normalizer_Out[i].getReal();
	            
	          //  if(real%2==0 && (real == De_Normalizer_Out[i].getReal() || real == De_Normalizer_Out[i].getReal()+1)){
	             if(real%2==0 && real == De_Normalizer_Out[i].getReal()){    
	                double min = -9.999999999999999;
	                double max =  8.999999999999999;
//	              double min = -1;
//	              double max = 1;
	                double random = ThreadLocalRandom.current().nextDouble(min, max);
	 //             Random r = new Random();
//	              int gen_val = r.nextInt(max - min + 1) + min;
	                double gen_val = random;
	                if(gen_val == 0.0000000000){gen_val++;}
	                real_complex = (double)real + (double)(gen_val/10);
	                 System.out.println(real_complex);
	                 System.out.println("%%%%%%%%%%%%"+real);
	            }
	            
	            if(real_complex>0 && (real%2)==0 && real_complex<16){
	                realz = real+1;
	            }
	            else if(real_complex>0 && (real%2)!=0 && real_complex<16){
	                realz = real;
	            }
	            else if(real_complex<0 && (real%2)==0 && real_complex>-16){
	                realz = real-1;
	            }
	            else if(real_complex<0 && (real%2)!=0 && real_complex>-16){
	                realz = real;
	            }
	            if( real_complex>=16 || real>=16){
	                realz = 15;
	            }
	            if(real_complex<=-16 || real<=-16){
	                realz = -15;
	            }
	            
	            int imge = (int) De_Normalizer_Out[i].getImaginary();
	            
//	            if(imge%2==0 && (imge == De_Normalizer_Out[i].getImaginary() || imge == De_Normalizer_Out[i].getImaginary()+1)){
	            if(imge%2==0 && imge == De_Normalizer_Out[i].getImaginary()){
//	                int min = -9;
//	                int max = 8;
	//
//	                Random r = new Random();
//	                int gen_val = r.nextInt(max - min + 1) + min;
//	                
//	                if(gen_val == 0){gen_val++;}
//	                imge_complex = imge + (gen_val/10);
	                double min = -9;
	                double max = 8;
	                double random = ThreadLocalRandom.current().nextDouble(min, max);
	                double gen_val = random;
	                if(gen_val == 0){gen_val++;}
	                imge_complex = (double)imge + (double)(gen_val/10);
	                System.out.println(imge_complex );
	            }
	            
	            if(imge_complex> 0 && (imge%2)==0 && imge_complex<16){
	                imgez = imge+1;
	            }
	            else if(imge_complex>0 && (imge%2)!=0 && imge_complex<16){
	                imgez = imge;
	            }
	            else if(imge_complex<0 && (imge%2)==0 && imge_complex>-16){
	                imgez = imge-1;
	            }
	            else if(imge_complex<0 && (imge%2)!=0 && imge_complex>-16){
	                imgez = imge;
	            }
	            if( imge_complex>16 || imge >=16){
	                imgez = 15;
	            }
	            if(imge_complex<-16 || imge <=-16){
	                imgez = -15;
	            }
	            
	            max_likelyhood_out[i]=Complex.valueOf(realz, imgez);
	        }
			//////////////////////////////////////////////////////////////////////
			
			
//			System.out.println("after call ml_out method");
//			System.out.println("ML_out length: "+ML_out.length);
			for (int i = 0; i < max_likelyhood_out.length; i++) {
//				System.out.print(ML_out[i]+"\t");
				push(max_likelyhood_out[i]);
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
