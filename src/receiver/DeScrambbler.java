package receiver;

import java.util.Arrays;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class DeScrambbler extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	public DeScrambbler(){
		this.add(new de_scrambble());
	}
	
	private static class de_scrambble extends Filter<FEC_Frame, FEC_Frame> {
		
		public de_scrambble() {
			super(1,1);  // 32208-80
		}

		@Override
		public void work() {
			System.out.println("De Scrambbler-------------");
			FEC_Frame current_frame = pop();
			boolean[] inputstream = current_frame.getFEC_Data();
//			boolean[] outputstream = ScramblerOut(inputstream);
			
			//////////////////////////
			int BBFRAMElength = 64800;
	        int counter = 0;
	        boolean newinput = false;
	        boolean[] outputstream = new boolean[inputstream.length];
	        boolean[] initialdata = new boolean[] {true,false,false,true,false,true,false,true,false,false,false,false,false,false,false};
	        for(int i=0; i<inputstream.length; i++){
	            if(counter>BBFRAMElength){counter=0;initialdata = new boolean[] {true,false,false,true,false,true,false,true,false,false,false,false,false,false,false};}
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
			///////////////////////
			
			FEC_Frame frame = new FEC_Frame(outputstream);
			push(frame);
		}
		
		private static boolean[] ScramblerOut(boolean [] inputstream){
	        int BBFRAMElength = 17;
	        int counter = 0;
	        boolean newinput = false;
	        boolean[] outputstream = new boolean[inputstream.length];
	        boolean[] initialdata = new boolean[] {true,false,false,true,false,true,false,true,false,false,false,false,false,false,false};
	        for(int i=0; i<inputstream.length; i++){
	            if(counter>BBFRAMElength){counter=0;initialdata = new boolean[] {true,false,false,true,false,true,false,true,false,false,false,false,false,false,false};}
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
	        return outputstream;
	    }
	}
}
