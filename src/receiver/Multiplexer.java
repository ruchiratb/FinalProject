package receiver;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.RoundrobinJoiner;
import edu.mit.streamjit.api.RoundrobinSplitter;
import edu.mit.streamjit.api.Splitjoin;
import receiver.FEC_Frame;
import transmitter.streamjit.Eightout;

/**
 *
 * @author Nipuna Priyamal
 */
public class Multiplexer extends edu.mit.streamjit.api.Pipeline<Eightout, FEC_Frame>{
    
	@SuppressWarnings("unchecked")
	public Multiplexer(){
//		this.add(new do_Multiplexing());
		this.add(
				new Splitjoin<Eightout,FEC_Frame>(
							new RoundrobinSplitter<Eightout>(1),
							new RoundrobinJoiner<FEC_Frame>(1),
							new do_Multiplexing(), new do_Multiplexing()
							
				)
		);
	}
	
	private static class do_Multiplexing extends Filter<Eightout, FEC_Frame> {
		
		public do_Multiplexing() {
			super(1, 1);//64800
		}

		@Override
		public void work() {
//			System.out.println("multiplexing---------------------");
			Eightout eightout = pop();			
//			boolean[] streamout = MultiplexerOut(eightout);		
			
			///////////////////////////////////
			 boolean[] array0 = eightout.getArray0();
			 boolean[] array1 = eightout.getArray1();
			 boolean[] array2 = eightout.getArray2();
			 boolean[] array3 = eightout.getArray3();
			 boolean[] array4 = eightout.getArray4();
			 boolean[] array5 = eightout.getArray5();
			 boolean[] array6 = eightout.getArray6();
		     boolean[] array7 = eightout.getArray7(); 
			 
		     
		     /*boolean temp2;
		        System.out.println("Mux Array 0--------------");
		   		for (int i = 0; i < 1000; i++) {
		   			temp2 = array0[i];
		   			if(temp2){
		   				System.out.print("1");
		   			}else {
		   				System.out.print("0");
		   			}
		   		}
		   		System.out.println();*/
//		     printArray(array0, "array0 mux");
//		     printArray(array1, "array1 mux");
		     
		     boolean[] stream = new boolean[64800];
		        boolean[][] sixteenout = new boolean[4050][16];
		        int x = 0;
		        for(int i=0; i<4050; i++){         //3==8100
		            
		            sixteenout[i][0]    =array0[x];
		            sixteenout[i][1]    =array1[x];
		            sixteenout[i][2]    =array2[x];
		            sixteenout[i][3]    =array3[x];
		            sixteenout[i][4]    =array4[x];
		            sixteenout[i][5]    =array5[x];
		            sixteenout[i][6]    =array6[x];
		            sixteenout[i][7]    =array7[x];
		            
		            sixteenout[i][8]    =array0[x+1];
		            sixteenout[i][9]    =array1[x+1];
		            sixteenout[i][10]   =array2[x+1];
		            sixteenout[i][11]   =array3[x+1];
		            sixteenout[i][12]   =array4[x+1];
		            sixteenout[i][13]   =array5[x+1];
		            sixteenout[i][14]   =array6[x+1];
		            sixteenout[i][15]   =array7[x+1];
		            
		            x = x+2;
		        }
		        
		       /* for(int i=0; i<4050; i++){
		            for(int j=0; j<16; j++){
		                System.out.printf(sixteenout[i][j]+" ");
		            }
		            System.out.printf("\n");
		        }*/
		        
		        int count=0;
		        for(int i=0; i<4050; i++){
		            stream[0+count]   =sixteenout[i][15];
		            stream[1+count]   =sixteenout[i][1];
		            stream[2+count]   =sixteenout[i][13];
		            stream[3+count]   =sixteenout[i][3];
		            stream[4+count]   =sixteenout[i][8];
		            stream[5+count]   =sixteenout[i][11];
		            stream[6+count]   =sixteenout[i][9];
		            stream[7+count]   =sixteenout[i][5];
		            stream[8+count]   =sixteenout[i][10];
		            stream[9+count]   =sixteenout[i][6];
		            stream[10+count]  =sixteenout[i][4];
		            stream[11+count]  =sixteenout[i][7];
		            stream[12+count]  =sixteenout[i][12];
		            stream[13+count]  =sixteenout[i][2];
		            stream[14+count]  =sixteenout[i][14];
		            stream[15+count]  =sixteenout[i][0];
		            
		            count=count+16;
		        }
		    
			///////////////////////////////////  		        
//			System.out.println("Multiplexer stream out size = "+stream.length);	
//			printArray(stream, "outputstream mux");
			//======================== SHIFT THE ARRAY ===============================
			
	/*		boolean[] shiftedstream = new boolean[64800];
			
			System.arraycopy(stream, 0, shiftedstream, 1, stream.length-2);
			shiftedstream[0] = true;*/
			//=====================================================
			/*boolean temp;
	        System.out.println("outputstream--------------");
	   		for (int i = 0; i < 1000; i++) {
	   			temp = shiftedstream[i];
	   			if(temp){
	   				System.out.print("1");
	   			}else {
	   				System.out.print("0");
	   			}
	   		}
	   		System.out.println();*/
			
			FEC_Frame out = new FEC_Frame(stream);
			push(out);
			/*for (int i = 0; i < streamout.length; i++) {
				if (streamout[i]) {
					push((byte) 1);
				}else {
					push((byte) 0);
				}
			}*/
			//////////////////////////////////////////////
			/*Random rand = new Random();
			
			for (int i = 0; i < 64800; i++) {
				int x = rand.nextInt(2);
				if (x == 1) {
					push((byte) 1);
				}else {
					push((byte) 0);
				}
			}*/
			/////////////////////////////////////////////		
		}
		private static void printArray(boolean[] array, String text){
			
			 boolean temp;
		        System.out.println(text+"--------------");
		   		for (int i = 0; i < 1000; i++) {
		   			temp = array[i];
		   			if(temp){
		   				System.out.print("1");
		   			}else {
		   				System.out.print("0");
		   			}
		   		}
		   		System.out.println();
		}
		
		private static boolean[] MultiplexerOut(Eightout eightout){
			 boolean[] array0 = eightout.getArray0();
			 boolean[] array1 = eightout.getArray1();
			 boolean[] array2 = eightout.getArray2();
			 boolean[] array3 = eightout.getArray3();
			 boolean[] array4 = eightout.getArray4();
			 boolean[] array5 = eightout.getArray5();
			 boolean[] array6 = eightout.getArray6();
		     boolean[] array7 = eightout.getArray7(); 
			  
			  boolean[] stream = new boolean[64800];
		        boolean[][] sixteenout = new boolean[4050][16];
		        int x = 0;
		        for(int i=0; i<8100; i++){         //3==8100
		            
		            sixteenout[i][0]    =array0[x];
		            sixteenout[i][1]    =array1[x];
		            sixteenout[i][2]    =array2[x];
		            sixteenout[i][3]    =array3[x];
		            sixteenout[i][4]    =array4[x];
		            sixteenout[i][5]    =array5[x];
		            sixteenout[i][6]    =array6[x];
		            sixteenout[i][7]    =array7[x];
		            
		            sixteenout[i][8]    =array0[x+1];
		            sixteenout[i][9]    =array1[x+1];
		            sixteenout[i][10]   =array2[x+1];
		            sixteenout[i][11]   =array3[x+1];
		            sixteenout[i][12]   =array4[x+1];
		            sixteenout[i][13]   =array5[x+1];
		            sixteenout[i][14]   =array6[x+1];
		            sixteenout[i][15]   =array7[x+1];
		            
		            x = x+2;
		        }
		        
		      /*  for(int i=0; i<3; i++){
		            for(int j=0; j<16; j++){
		                System.out.printf(sixteenout[i][j]+" ");
		            }
		            System.out.printf("\n");
		        }*/
		        
		        int count=0;
		        for(int i=0; i<8100; i++){
		            stream[0+count]   =sixteenout[i][15];
		            stream[1+count]   =sixteenout[i][1];
		            stream[2+count]   =sixteenout[i][13];
		            stream[3+count]   =sixteenout[i][3];
		            stream[4+count]   =sixteenout[i][8];
		            stream[5+count]   =sixteenout[i][11];
		            stream[6+count]   =sixteenout[i][9];
		            stream[7+count]   =sixteenout[i][5];
		            stream[8+count]   =sixteenout[i][10];
		            stream[9+count]   =sixteenout[i][6];
		            stream[10+count]  =sixteenout[i][4];
		            stream[11+count]  =sixteenout[i][7];
		            stream[12+count]  =sixteenout[i][12];
		            stream[13+count]  =sixteenout[i][2];
		            stream[14+count]  =sixteenout[i][14];
		            stream[15+count]  =sixteenout[i][0];
		            
		            count=count+16;
		        }
		        return stream;
		        
		    }
	}
	
  
}
