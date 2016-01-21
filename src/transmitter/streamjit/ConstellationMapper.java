package transmitter.streamjit;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
/**
 *
 * @author Nipuna Priyamal
 */

public class ConstellationMapper extends edu.mit.streamjit.api.Pipeline<Eightout, Complex>{
    
	public ConstellationMapper(){
		this.add(new Mapper());
	}
	
	private static class Mapper extends Filter<Eightout, Complex> {
		
		public Mapper() {
			super(1, 8100);
		}

		@Override
		public void work() {
			System.out.println("============ Constellation Mapper ================");
			Eightout eight = pop();
			Complex[] cells = Cellqueue(eight);
			for (int i = 0; i < 8100; i++) {
				push(cells[i]);
			}
		}
	}
     static Complex[] Cellqueue(Eightout arrays) {
        
    	 boolean array0[] = arrays.getArray0();
    	 boolean array1[] = arrays.getArray1();
    	 boolean array2[] = arrays.getArray2();
    	 boolean array3[] = arrays.getArray3();
    	 boolean array4[] = arrays.getArray4();
    	 boolean array5[] = arrays.getArray5();
    	 boolean array6[] = arrays.getArray6();
    	 boolean array7[] = arrays.getArray7();
    	 
    	 int length = array0.length;
         int real = 0;
         int img  = 0;
         
         double realz   =   0;
         double imgz    =   0;
         Complex Constmapout[] = new Complex[length]; //6=8100
      
         
         for(int i=0; i<length; i++){        //3 == 4050
             
            if(array0[i]==true){
                real    = real  + (int)(Math.pow(2, 0));
            }
            if(array1[i]==true){
                img     = img   + (int)(Math.pow(2, 1));
            }
            if(array2[i]==true){
                real    = real  + (int)(Math.pow(2, 2));
            }
            if(array3[i]==true){
                img     = img   + (int)(Math.pow(2, 3));
            }
            if(array4[i]==true){
                real    = real  + (int)(Math.pow(2, 4));
            }
            if(array5[i]==true){
                img     = img   + (int)(Math.pow(2, 5));
            }
            if(array6[i]==true){
                real    = real  + (int)(Math.pow(2, 6));
            }
            if(array7[i]==true){
                img     = img   + (int)(Math.pow(2, 7));
            }
            
           
            
             switch (real) {
                case 1  : realz = -15;
                        break;
                case 65 : realz = -13;
                        break;
                case 81 : realz = -11;
                        break;
                case 17 : realz = -9;
                        break;
                case 21 : realz = -7;
                        break;
                case 85 : realz = -5;
                        break;
                case 69 : realz = -3;
                        break;
                case 5  : realz = -1;
                        break;
                case 4  : realz = 1;
                        break;
                case 68 : realz = 3;
                        break;
                case 84 : realz = 5;
                        break;
                case 20 : realz = 7;
                        break;
                case 16 : realz = 9;
                        break;
                case 80 : realz = 11;
                        break;
                case 64 : realz = 13;
                        break;
                case 0  : realz = 15;
                        break;
         }
             
         switch (img) {
                case 2  : imgz = -15;
                        break;
                case 130: imgz = -13;
                        break;
                case 162: imgz = -11;
                        break;
                case 34 : imgz = -9;
                        break;
                case 42 : imgz = -7;
                        break;
                case 170: imgz = -5;
                        break;
                case 138: imgz = -3;
                        break;
                case 10 : imgz = -1;
                        break;
                case 8  : imgz = 1;
                        break;
                case 136: imgz = 3;
                        break;
                case 168: imgz = 5;
                        break;
                case 40 : imgz = 7;
                        break;
                case 32 : imgz = 9;
                        break;
                case 160: imgz = 11;
                        break;
                case 128: imgz = 13;
                        break;
                case 0  : imgz = 15;
                        break;
         }    
         
        
         Constmapout[i]=Complex.valueOf(realz, imgz);
         real   = 0;
         img    = 0;
         realz  = 0;
         imgz   = 0; 
    }
         return Constmapout;
 }   
}   
