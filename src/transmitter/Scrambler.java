package transmitter;

import java.util.ArrayList;

/**
 *
 * @author Nipuna Priyamal
 */
public class Scrambler {
    
//    public static void main(String[] args) {
//
//		boolean [] inputstream = new boolean[] {false,true,true,false,false,true,true,true,true,false,false,true,false,false,false,true,false,false,false,false};
//                // input data sequence 100101010000000
//              
//                boolean[] outputstream = new boolean[inputstream.length];
//                outputstream = ScramblerOut(inputstream);
//                System.out.print("----------------Final Output-----------------  "+"\n");
//                for (int i = 0; i <inputstream.length; i++) {
//        			System.out.print(inputstream[i]+"  ");
//        		}
//                System.out.println();
//		        for (int i = 0; i <outputstream.length; i++) {
//					System.out.print(outputstream[i]+"  ");
//				}
//    }
    
  public ArrayList<FEC_Frame> ScramblerOut(ArrayList<FEC_Frame> frames){
        int BBFRAMElength = 20;
        int counter = 0;
        boolean newinput = false;
        ArrayList<FEC_Frame> output_frames = new ArrayList<FEC_Frame>();
        for (int a = 0; a < frames.size(); a++) {
        	FEC_Frame current_frame = frames.get(a);
        	boolean[] inputstream = current_frame.FEC_frame;
        	boolean[] outputstream = new boolean[inputstream.length];
        	boolean[] initialdata = new boolean[] {true,false,false,true,false,true,false,true,false,false,false,false,false,false,false};
        	for(int i=0; i<inputstream.length; i++){
        		if(counter>BBFRAMElength){counter = 0; initialdata = new boolean[] {true,false,false,true,false,true,false,true,false,false,false,false,false,false,false};}
        		if(counter<=BBFRAMElength){
        			newinput = initialdata[13]^initialdata[14];
        			outputstream[i] = inputstream[i]^newinput;
        			for(int k=14;k>0; k--){
        				initialdata[k]=initialdata[k-1];
        			}
        			initialdata[0]=newinput;
                    counter++;
        		}
        		
        	} 	
        	output_frames.add(new FEC_Frame(outputstream));
		}
//        System.out.println("==============after scrambler==============");
//        printDataField(output_frames);
        return output_frames;
        
    }
  
//  private static void printDataField(ArrayList<FEC_Frame> frames) {
//		for (int k = 0; k < frames.size(); k++) {
//			FEC_Frame tempframe = frames.get(k);
//			boolean[] data = tempframe.FEC_frame;
//			System.out.println("\n-------------- Frame "+(k+1)+"-----------------------");
//			for (int i = 0; i < data.length; i++) {
//				
//				if (data[i] == true) {
//					System.out.print("1 ");
//				}else {
//					System.out.print("0 ");
//				}
//			}
//		}
//		
//	}
  
//  private static void printArray(boolean[] array) {
//		
//			for (int i = 0; i < array.length; i++) {
//				
//				if (array[i] == true) {
//					System.out.print("1 ");
//				}else {
//					System.out.print("0 ");
//				}
//			}
//		
//	}
}
