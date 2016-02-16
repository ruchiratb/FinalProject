package transmitter.streamjit;

import java.io.BufferedReader;
import java.io.FileReader;import java.util.Scanner;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;


public class LDPC extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	public LDPC(){
		this.add(new LDPC_Encoder());
	}

	private static class LDPC_Encoder extends Filter<FEC_Frame, FEC_Frame> {
		
		public LDPC_Encoder() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ LDPC ================");
			FEC_Frame frame = pop();
			FEC_Frame frame2 = do_ldpc(frame);
			push(frame2);
		}
	}
	
    public static FEC_Frame do_ldpc(FEC_Frame frame) {
        boolean information[]= frame.FEC_frame;
        System.out.println("information length = "+information.length);
        boolean output[]= new boolean[64800];
        boolean parity[]= new boolean[32400];       
       
       System.out.println("Reading File from Java code");
       String fileName="E:\\workspace\\FinalProject\\table-A1-for-LDPC-code-rate-half.txt";
       
       
    	   FEC_Frame currentframe = frame;
    	   information = currentframe.FEC_frame;
    	   try{
    		   FileReader inputFile = new FileReader(fileName);
    		   BufferedReader bufferReader = new BufferedReader(inputFile);
    		   String line;
    		   Scanner scan;
    		   int x=0;
    		   int y=1;
    		   int m=1;
    		   while ((line = bufferReader.readLine()) != null)   {
    			   scan = new Scanner(line);
    			   
    			   while(scan.hasNextInt()){
    				   int t;
    				   t = scan.nextInt();
    				   //System.out.println(t);//ok
    				   parity[t] ^=(information[x]);
    				   int tt = (t+((y%360)*60))%32400;
    				   while(m<360+x){
    					   if (m+x > 32399) {
							break;
    					   }
    					   parity[tt] ^=(information[m+x]);
    					   m++;
    				   }
    				   m=x+1;
    			   }
    			   y++;
    			   x+=360;
    		   }
    		   
    		   bufferReader.close();
    	   }catch(Exception e){
    		   System.out.println("Error while reading file line by line:" + e.getMessage());
    		   e.printStackTrace();
    	   }
    	   
    	   for( int i=0; i<32400; i++ ){
    		   output[i] = information[i];
    	   }
    	   for( int i=32400; i<64800; i++ ){
    		   output[i] = parity[i-32400];
    	   }
       
       return new FEC_Frame(output);
    }
}


    
    

