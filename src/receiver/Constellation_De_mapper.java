package receiver;

import transmitter.streamjit.Eightout;
import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

/**
 *
 * @author Nipuna Priyamal
 */
public class Constellation_De_mapper extends edu.mit.streamjit.api.Pipeline<Complex, Eightout>{
    
	public Constellation_De_mapper(){
		this.add(new Demapper());
	}
	
	private static class Demapper extends Filter<Complex, Eightout> {		
		
		public Demapper() {
			super(64800, 1);
		}

		@Override
		public void work() {
			int arraylength = 8100;
//			System.out.println("Constellation demapper work method -----------------------");
			Complex[] ML_out = new Complex[arraylength];
			for (int i = 0; i < ML_out.length; i++) {
				ML_out[i] = pop();
//				System.out.print(ML_out[i]+"  ");
			}
			
//			Eightout demap_out = ConstDemapperOut(ML_out);
			Eightout demap_out;
			/////////////////////////////////////////////////////////////

	         boolean[] array0 = new boolean[arraylength];
	         boolean[] array1 = new boolean[arraylength];
	         boolean[] array2 = new boolean[arraylength];
	         boolean[] array3 = new boolean[arraylength];
	         boolean[] array4 = new boolean[arraylength];
	         boolean[] array5 = new boolean[arraylength];
	         boolean[] array6 = new boolean[arraylength];
	         boolean[] array7 = new boolean[arraylength];
	              
	         for(int i=0; i<8100; i++){//3 == 4050
	             
	               int real = 0;
	               int img  = 0;
	               real = (int) ML_out[i].getReal();
	               img  = (int) ML_out[i].getImaginary();
	               int realz   =   0;
	               int imgz    =   0;
	               //System.out.println(real+"\t"+img);
//	               System.out.print("real="+real+"\timg="+img);
	               switch (real) {
	                case -15  : realz = 1;array0[i]=true;
	                        break;
	                case -13  : realz = 65;array0[i]=true;array6[i]=true;
	                        break;
	                case -11  : realz = 81;array0[i]=true;array4[i]=true;array6[i]=true;
	                        break;
	                case -9   : realz = 17;array0[i]=true;array4[i]=true;
	                        break;
	                case -7   : realz = 21;array0[i]=true;array2[i]=true;array4[i]=true;
	                        break;
	                case -5   : realz = 85;array0[i]=true;array2[i]=true;array4[i]=true;array6[i]=true;
	                        break;
	                case -3   : realz = 69;array0[i]=true;array2[i]=true;array6[i]=true;
	                        break;
	                case -1   : realz = 5;array0[i]=true;array2[i]=true;
	                        break;
	                case 1    : realz = 4;array2[i]=true;
	                        break;
	                case 3    : realz = 68;array2[i]=true;array6[i]=true;
	                        break;
	                case 5    : realz = 84;array2[i]=true;array4[i]=true;array6[i]=true;
	                        break;
	                case 7    : realz = 20;array2[i]=true;array4[i]=true;
	                        break;
	                case 9    : realz = 16;array4[i]=true;
	                        break;
	                case 11   : realz = 80;array4[i]=true;array6[i]=true;
	                        break;
	                case 13   : realz = 64;array6[i]=true;
	                        break;
	                case 15   : realz = 0;
	                        break;
	         }
	             
	         switch (img) {
	                case -15  : imgz = 2;array1[i]=true;
	                        break;
	                case -13  : imgz = 130;array1[i] =true;array7[i]=true;
	                        break;
	                case -11  : imgz = 162;array1[i]=true;array5[i]=true;array7[i]=true;
	                        break;
	                case -9   : imgz = 34;array1[i]=true;array5[i]=true;
	                        break;
	                case -7   : imgz = 42;array1[i]=true;array3[i]=true;array5[i]=true;
	                        break;
	                case -5   : imgz = 170;array1[i]=true;array3[i]=true;array5[i]=true;array7[i]=true;
	                        break;
	                case -3   : imgz = 138;array1[i]=true;array3[i]=true;array7[i]=true;
	                        break;
	                case -1   : imgz = 10;array1[i]=true;array3[i]=true;
	                        break;
	                case 1    : imgz = 8;array3[i]=true;
	                        break;
	                case 3    : imgz = 136;array3[i]=true;array7[i]=true;
	                        break;
	                case 5    : imgz = 168;array3[i]=true;array5[i]=true;array7[i]=true;
	                        break;
	                case 7    : imgz = 40;array3[i]=true;array5[i]=true;
	                        break;
	                case 9    : imgz = 32;array5[i]=true;
	                        break;
	                case 11   : imgz = 160;array5[i]=true;array7[i]=true;
	                        break;
	                case 13   : imgz = 128;array7[i]=true;
	                        break;
	                case 15   : imgz = 0;
	                        break;
	         }
//	         System.out.println("\t\trealz="+realz+"\timgz="+imgz);
	         
	         }
//	         System.out.println("\n");
	         demap_out = new Eightout(array0,array1,array2,array3,array4,array5,array6,array7);
			/////////////////////////////////////////////////////
//			System.out.println("eightout push-------------------");
			push(demap_out);
		}
		
		private static Eightout ConstDemapperOut(Complex [] max_likelyhoodout){
	        
	         boolean[] array0 = new boolean[8];
	         boolean[] array1 = new boolean[8];
	         boolean[] array2 = new boolean[8];
	         boolean[] array3 = new boolean[8];
	         boolean[] array4 = new boolean[8];
	         boolean[] array5 = new boolean[8];
	         boolean[] array6 = new boolean[8];
	         boolean[] array7 = new boolean[8];
	              
	         for(int i=0; i<8; i++){//3 == 4050
	             
	               int real = 0;
	               int img  = 0;
	               real = (int) max_likelyhoodout[i].getReal();
	               img  = (int) max_likelyhoodout[i].getImaginary();
	               int realz   =   0;
	               int imgz    =   0;
//	               System.out.println(real+"\t"+img);
	               
	               switch (real) {
	                case -15  : realz = 1;array0[i]=true;
	                        break;
	                case -13  : realz = 65;array0[i]=true;array6[i]=true;
	                        break;
	                case -11  : realz = 81;array0[i]=true;array4[i]=true;array6[i]=true;
	                        break;
	                case -9   : realz = 17;array0[i]=true;array4[i]=true;
	                        break;
	                case -7   : realz = 21;array0[i]=true;array2[i]=true;array4[i]=true;
	                        break;
	                case -5   : realz = 85;array0[i]=true;array2[i]=true;array4[i]=true;array6[i]=true;
	                        break;
	                case -3   : realz = 69;array0[i]=true;array2[i]=true;array6[i]=true;
	                        break;
	                case -1   : realz = 5;array0[i]=true;array2[i]=true;
	                        break;
	                case 1    : realz = 4;array2[i]=true;
	                        break;
	                case 3    : realz = 68;array2[i]=true;array6[i]=true;
	                        break;
	                case 5    : realz = 84;array2[i]=true;array4[i]=true;array6[i]=true;
	                        break;
	                case 7    : realz = 20;array2[i]=true;array4[i]=true;
	                        break;
	                case 9    : realz = 16;array4[i]=true;
	                        break;
	                case 11   : realz = 80;array4[i]=true;array6[i]=true;
	                        break;
	                case 13   : realz = 64;array6[i]=true;
	                        break;
	                case 15   : realz = 0;
	                        break;
	         }
	             
	         switch (img) {
	                case -15  : imgz = 2;array1[i]=true;
	                        break;
	                case -13  : imgz = 130;array1[i] =true;array7[i]=true;
	                        break;
	                case -11  : imgz = 162;array1[i]=true;array5[i]=true;array7[i]=true;
	                        break;
	                case -9   : imgz = 34;array1[i]=true;array5[i]=true;
	                        break;
	                case -7   : imgz = 42;array1[i]=true;array3[i]=true;array5[i]=true;
	                        break;
	                case -5   : imgz = 170;array1[i]=true;array3[i]=true;array5[i]=true;array7[i]=true;
	                        break;
	                case -3   : imgz = 138;array1[i]=true;array3[i]=true;array7[i]=true;
	                        break;
	                case -1   : imgz = 10;array1[i]=true;array3[i]=true;
	                        break;
	                case 1    : imgz = 8;array3[i]=true;
	                        break;
	                case 3    : imgz = 136;array3[i]=true;array7[i]=true;
	                        break;
	                case 5    : imgz = 168;array3[i]=true;array5[i]=true;array7[i]=true;
	                        break;
	                case 7    : imgz = 40;array3[i]=true;array5[i]=true;
	                        break;
	                case 9    : imgz = 32;array5[i]=true;
	                        break;
	                case 11   : imgz = 160;array5[i]=true;array7[i]=true;
	                        break;
	                case 13   : imgz = 128;array7[i]=true;
	                        break;
	                case 15   : imgz = 0;
	                        break;
	         }
//	         System.out.println(realz+"\t"+imgz);
	         
	         }
//	         System.out.println("\n");    
	        return new Eightout(array0,array1,array2,array3,array4,array5,array6,array7);
	        
	    }
	}
	
   
}
