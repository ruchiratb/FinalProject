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
			FEC_Frame frame2 = doldpc(frame);
			push(frame2);
		}
	}
	
    public static FEC_Frame doldpc(FEC_Frame frame) {
        boolean information[]= new boolean[32400];
        boolean output[]= new boolean[64800];
        boolean parity[]= new boolean[32400];
        /*for( int i=0; i<32400; i++ ){
        	parity[i] = false;
        }*/
/*        
        
        String fileNameo="C:\\Users\\User\\Desktop\\binary.txt";
       try{
          FileReader inputFile = new FileReader(fileNameo);
          BufferedReader bufferReader = new BufferedReader(inputFile);
          String line;
          Scanner scan;
          int x=0;
          
          while ((line = bufferReader.readLine()) != null)   {
              
             // System.out.println(line);
            scan = new Scanner(line);
            
                while(scan.hasNextInt()){
                int t;
                    t = scan.nextInt();
               if (t==0){
                information[x] =false;
                }
                
                else if (t==1){
                information[x] =true;
                }
                else{
                System.out.println("wrong");
                }
                //System.out.println(x);
                //System.out.println(t);
                //System.out.println(information[x]);
                x++;
                }
          }
          System.out.println(x);
          //System.out.println(information[t]);
          bufferReader.close();
       }catch(Exception e){
          System.out.println("Error while reading file line by line:" + e.getMessage());                      
       }*/
       
      /* for(int i=0; i<32400; i++){
           System.out.println(information[i]); 
       }*/
       
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
    			   // System.out.println(line);
    			   //ArrayList checklist = new ArrayList();
    			   scan = new Scanner(line);
    			   
    			   while(scan.hasNextInt()){
    				   int t;
    				   t = scan.nextInt();
    				   //System.out.println(t);//ok
    				   parity[t] ^=(information[x]);
    				   //System.out.println(parity[t]);
    				   int tt = (t+((y%360)*60))%32400;
    				   //System.out.println(tt);//ok
    				   
    				   while(m<360+x){
    					   if (m+x > 32399) {
							break;
    					   }
    					   parity[tt] ^=(information[m+x]);
    					   m++;
    					   //System.out.println(parity[tt]);
    				   }
    				   m=x+1;
    			   }
    			   y++;
    			   x+=360;
    			   //System.out.println(line);
    		   }
    		   
    		   bufferReader.close();
    	   }catch(Exception e){
    		   System.out.println("Error while reading file line by line:" + e.getMessage());
    		   e.printStackTrace();
    	   }
    	   
    	   for(int i=0; i<32400; i++){
    		   //System.out.println(parity[i]); 
    	   }
    	   //System.out.println(parity[4]);
    	   
    	   for( int i=0; i<32400; i++ ){
    		   output[i] = information[i];
    	   }
    	   for( int i=32400; i<64800; i++ ){
    		   output[i] = parity[i-32400];
    		   //System.out.println(output[i]);
    	   }
    	   for( int i=0; i<64800; i++ ){
//    		   System.out.println(output[i]);
    	   }
    	   
//    	   ldpcencodedframes.add(new FEC_Frame(output));
       
       return new FEC_Frame(output);
    }
}


    
    

