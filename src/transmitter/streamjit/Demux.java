package transmitter.streamjit;


import edu.mit.streamjit.api.Filter;
import transmitter.streamjit.Eightout;
import transmitter.streamjit.FEC_Frame;

/**
 *
 * @author Nipuna Priyamal
 */
public class Demux extends edu.mit.streamjit.api.Pipeline<FEC_Frame, Eightout>{

	public Demux(){
		this.add(new Demuxer());
	}
	
	private static class Demuxer extends Filter<FEC_Frame, Eightout> {
		
		public Demuxer() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ Demux ================");
			FEC_Frame frame = pop();
//			Eightout out = Demuxout(frame);
			boolean[] inputstream = frame.FEC_frame;
			System.out.println("inputstream length: "+inputstream.length);
			
			/*boolean temp;
	        System.out.println("inputstream--------------");
	   		for (int i = 0; i < 1000; i++) {
	   			temp = inputstream[i];
	   			if(temp){
	   				System.out.print("1");
	   			}else {
	   				System.out.print("0");
	   			}
	   		}
	   		System.out.println();*/
			
			/////////////////////////////////////////////////////////
			int demuxlinelength = 4050; // 3 should be 4050
		     boolean[] demuxout0  = new boolean[demuxlinelength];    int input0  = 0;
		     boolean[] demuxout1  = new boolean[demuxlinelength];    int input1  = 0;
		     boolean[] demuxout2  = new boolean[demuxlinelength];    int input2  = 0;
		     boolean[] demuxout3  = new boolean[demuxlinelength];    int input3  = 0;
		     boolean[] demuxout4  = new boolean[demuxlinelength];    int input4  = 0;
		     boolean[] demuxout5  = new boolean[demuxlinelength];    int input5  = 0;
		     boolean[] demuxout6  = new boolean[demuxlinelength];    int input6  = 0;
		     boolean[] demuxout7  = new boolean[demuxlinelength];    int input7  = 0;
		     boolean[] demuxout8  = new boolean[demuxlinelength];    int input8  = 0;
		     boolean[] demuxout9  = new boolean[demuxlinelength];    int input9  = 0;
		     boolean[] demuxout10 = new boolean[demuxlinelength];    int input10 = 0;
		     boolean[] demuxout11 = new boolean[demuxlinelength];    int input11 = 0;
		     boolean[] demuxout12 = new boolean[demuxlinelength];    int input12 = 0;
		     boolean[] demuxout13 = new boolean[demuxlinelength];    int input13 = 0;
		     boolean[] demuxout14 = new boolean[demuxlinelength];    int input14 = 0;
		     boolean[] demuxout15 = new boolean[demuxlinelength];    int input15 = 0;
		     
		     
		     for(int i = 0;i < inputstream.length; i++){
		         switch (i%16) {
		                case 0:  demuxout15[input15]  =inputstream[i];input15++;
		                         break;
		                case 1:  demuxout1[input1]    =inputstream[i];input1++;
		                         break;    
		                case 2:  demuxout13[input13]  =inputstream[i];input13++;
		                         break;
		                case 3:  demuxout3[input3]    =inputstream[i];input3++;
		                         break;
		                case 4:  demuxout8[input8]    =inputstream[i];input8++;
		                         break;
		                case 5:  demuxout11[input11]  =inputstream[i];input11++;
		                         break;
		                case 6:  demuxout9[input9]    =inputstream[i];input9++;
		                         break;
		                case 7:  demuxout5[input5]    =inputstream[i];input5++;
		                         break;
		                case 8:  demuxout10[input10]  =inputstream[i];input10++;
		                         break;
		                case 9:  demuxout6[input6]    =inputstream[i];input6++;
		                         break;
		                case 10: demuxout4[input4]    =inputstream[i];input4++;
		                         break;
		                case 11: demuxout7[input7]    =inputstream[i];input7++;
		                         break;
		                case 12: demuxout12[input12]  =inputstream[i];input12++;
		                         break;
		                case 13: demuxout2[input2]    =inputstream[i];input2++;
		                         break;
		                case 14: demuxout14[input14]  =inputstream[i];input14++;
		                         break;
		                case 15: demuxout0[input0]    =inputstream[i];input0++;
		                         break;
		         }   
		     }
		           int stremlength = 8100;        //6 = 8100
		           boolean[] stream0 = new boolean[stremlength];    
		           boolean[] stream1 = new boolean[stremlength];    
		           boolean[] stream2 = new boolean[stremlength];    
		           boolean[] stream3 = new boolean[stremlength];    
		           boolean[] stream4 = new boolean[stremlength];    
		           boolean[] stream5 = new boolean[stremlength];    
		           boolean[] stream6 = new boolean[stremlength];    
		           boolean[] stream7 = new boolean[stremlength];    
		          
		           
		           int in = 0;
		           for(int x=0; x<4050; x++){
		               
		                       stream0[in]=demuxout0[x];
		                       stream1[in]=demuxout1[x];
		                       stream2[in]=demuxout2[x];
		                       stream3[in]=demuxout3[x];
		                       stream4[in]=demuxout4[x];
		                       stream5[in]=demuxout5[x];
		                       stream6[in]=demuxout6[x];
		                       stream7[in]=demuxout7[x];
		                   
		                       stream0[in+1]=demuxout8[x];
		                       stream1[in+1]=demuxout9[x];
		                       stream2[in+1]=demuxout10[x];
		                       stream3[in+1]=demuxout11[x];
		                       stream4[in+1]=demuxout12[x];
		                       stream5[in+1]=demuxout13[x];
		                       stream6[in+1]=demuxout14[x];
		                       stream7[in+1]=demuxout15[x];
		              
		               in = in+2;
		           }    
		       
		       /* boolean temp2;
		        System.out.println("Demux Array 0--------------");
		   		for (int i = 0; i < 1000; i++) {
		   			temp2 = stream0[i];
		   			if(temp2){
		   				System.out.print("1");
		   			}else {
		   				System.out.print("0");
		   			}
		   		}
		   		System.out.println();
		   		System.out.println("Demux Array 1--------------");
		   		for (int i = 0; i < 1000; i++) {
		   			temp2 = stream1[i];
		   			if(temp2){
		   				System.out.print("1");
		   			}else {
		   				System.out.print("0");
		   			}
		   		}
		   		System.out.println();
		   		System.out.println("Demux Array 2--------------");
		   		for (int i = 0; i < 1000; i++) {
		   			temp2 = stream2[i];
		   			if(temp2){
		   				System.out.print("1");
		   			}else {
		   				System.out.print("0");
		   			}
		   		}
		   		System.out.println();
		   		System.out.println("Demux Array 3--------------");
		   		for (int i = 0; i < 1000; i++) {
		   			temp2 = stream3[i];
		   			if(temp2){
		   				System.out.print("1");
		   			}else {
		   				System.out.print("0");
		   			}
		   		}
		   		System.out.println();*/
//		           printArray(stream0, "stream0 demux");
//		           printArray(stream1, "stream1 demux");
		     Eightout out =  new Eightout(stream0,stream1,stream2,stream3,stream4,stream5,stream6,stream7);
			//////////////////////////////////////////////////////////
			
			
			push(out);
		}
		
		private static void printArray(boolean[] array , String text){

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
	}
	
     static Eightout Demuxout(FEC_Frame frame){
     FEC_Frame current_frame;
     boolean[] inputstream;
   	 int stremlength = 8100;        //6 = 8100
   	 boolean[] stream0 = new boolean[stremlength];    
   	 boolean[] stream1 = new boolean[stremlength];    
   	 boolean[] stream2 = new boolean[stremlength];    
   	 boolean[] stream3 = new boolean[stremlength];    
   	 boolean[] stream4 = new boolean[stremlength];    
   	 boolean[] stream5 = new boolean[stremlength];    
   	 boolean[] stream6 = new boolean[stremlength];    
   	 boolean[] stream7 = new boolean[stremlength];    
   	 
   	 
   	 int demuxlinelength =4050; // 3 should be 4050
/*	 boolean[] demuxout0 = new boolean[demuxlinelength];    int input0  = 0;
	 boolean[] demuxout1 = new boolean[demuxlinelength];    int input1  = 0;
	 boolean[] demuxout2 = new boolean[demuxlinelength];    int input2  = 0;
	 boolean[] demuxout3 = new boolean[demuxlinelength];    int input3  = 0;
	 boolean[] demuxout4 = new boolean[demuxlinelength];    int input4  = 0;
	 boolean[] demuxout5 = new boolean[demuxlinelength];    int input5  = 0;
	 boolean[] demuxout6 = new boolean[demuxlinelength];    int input6  = 0;
	 boolean[] demuxout7 = new boolean[demuxlinelength];    int input7  = 0;
	 boolean[] demuxout8 = new boolean[demuxlinelength];    int input8  = 0;
	 boolean[] demuxout9 = new boolean[demuxlinelength];    int input9  = 0;
	 boolean[] demuxout10 = new boolean[demuxlinelength];   int input10 = 0;
	 boolean[] demuxout11 = new boolean[demuxlinelength];   int input11 = 0;
	 boolean[] demuxout12 = new boolean[demuxlinelength];   int input12 = 0;
	 boolean[] demuxout13 = new boolean[demuxlinelength];   int input13 = 0;
	 boolean[] demuxout14 = new boolean[demuxlinelength];   int input14 = 0;
	 boolean[] demuxout15 = new boolean[demuxlinelength];   int input15 = 0;*/
   	
    	 current_frame = frame;
    	 inputstream = current_frame.FEC_frame;
//    	 System.out.println("input stream size = "+inputstream.length);  	 
    	 
    	 boolean[] demuxout0 = new boolean[demuxlinelength];    int input0  = 0;
    	 boolean[] demuxout1 = new boolean[demuxlinelength];    int input1  = 0;
    	 boolean[] demuxout2 = new boolean[demuxlinelength];    int input2  = 0;
    	 boolean[] demuxout3 = new boolean[demuxlinelength];    int input3  = 0;
    	 boolean[] demuxout4 = new boolean[demuxlinelength];    int input4  = 0;
    	 boolean[] demuxout5 = new boolean[demuxlinelength];    int input5  = 0;
    	 boolean[] demuxout6 = new boolean[demuxlinelength];    int input6  = 0;
    	 boolean[] demuxout7 = new boolean[demuxlinelength];    int input7  = 0;
    	 boolean[] demuxout8 = new boolean[demuxlinelength];    int input8  = 0;
    	 boolean[] demuxout9 = new boolean[demuxlinelength];    int input9  = 0;
    	 boolean[] demuxout10 = new boolean[demuxlinelength];   int input10 = 0;
    	 boolean[] demuxout11 = new boolean[demuxlinelength];   int input11 = 0;
    	 boolean[] demuxout12 = new boolean[demuxlinelength];   int input12 = 0;
    	 boolean[] demuxout13 = new boolean[demuxlinelength];   int input13 = 0;
    	 boolean[] demuxout14 = new boolean[demuxlinelength];   int input14 = 0;
    	 boolean[] demuxout15 = new boolean[demuxlinelength];   int input15 = 0;
    	 
    	 for(int i = 0;i < inputstream.length; i++){
    		 switch (i%16) {
    		 case 0:  demuxout15[input15]  =inputstream[i];input15++;
    		 break;
    		 case 1:  demuxout1[input1]    =inputstream[i];input1++;
    		 break;    
    		 case 2:  demuxout13[input13]  =inputstream[i];input13++;
    		 break;
    		 case 3:  demuxout3[input3]    =inputstream[i];input3++;
    		 break;
    		 case 4:  demuxout8[input8]    =inputstream[i];input8++;
    		 break;
    		 case 5:  demuxout11[input11]  =inputstream[i];input11++;
    		 break;
    		 case 6:  demuxout9[input9]    =inputstream[i];input9++;
    		 break;
    		 case 7:  demuxout5[input5]    =inputstream[i];input5++;
    		 break;
    		 case 8:  demuxout10[input10]  =inputstream[i];input10++;
    		 break;
    		 case 9:  demuxout6[input6]    =inputstream[i];input6++;
    		 break;
    		 case 10: demuxout4[input4]    =inputstream[i];input4++;
    		 break;
    		 case 11: demuxout7[input7]    =inputstream[i];input7++;
    		 break;
    		 case 12: demuxout12[input12]  =inputstream[i];input12++;
    		 break;
    		 case 13: demuxout2[input2]    =inputstream[i];input2++;
    		 break;
    		 case 14: demuxout14[input14]  =inputstream[i];input14++;
    		 break;
    		 case 15: demuxout0[input0]    =inputstream[i];input0++;
    		 break;
    		 }   
    	 }
    	 int in = 0;
    	 for(int x=0; x<demuxlinelength; x++){
    		 //for(int j=0; j<16; j++){
    		 //  if(j<8){
    		 stream0[in]=demuxout0[x];
    		 stream1[in]=demuxout1[x];
    		 stream2[in]=demuxout2[x];
    		 stream3[in]=demuxout3[x];
    		 stream4[in]=demuxout4[x];
    		 stream5[in]=demuxout5[x];
    		 stream6[in]=demuxout6[x];
    		 stream7[in]=demuxout7[x];
    		 //}
    		 // if(j>=8){
    		 stream0[in+1]=demuxout8[x];
    		 stream1[in+1]=demuxout9[x];
    		 stream2[in+1]=demuxout10[x];
    		 stream3[in+1]=demuxout11[x];
    		 stream4[in+1]=demuxout12[x];
    		 stream5[in+1]=demuxout13[x];
    		 stream6[in+1]=demuxout14[x];
    		 stream7[in+1]=demuxout15[x];
    		 // }
    		 // }
    		 in = in+2;
    	 }    
	         
     
     return new Eightout(stream0,stream1,stream2,stream3,stream4,stream5,stream6,stream7);
     //return new Eightout(demuxout0,demuxout1, demuxout2,demuxout3,demuxout4,demuxout5, demuxout6,demuxout7,demuxout8,demuxout9, demuxout10,demuxout11,demuxout12,demuxout13, demuxout14,demuxout15);
}
}

  