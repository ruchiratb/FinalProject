package transmitter.streamjit;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

/**
 *
 * @author Nipuna Priyamal
 */
public class Scrambler extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
    
	public Scrambler(){
		this.add(new Scramble());
	}
	
	private static class Scramble extends Filter<FEC_Frame, FEC_Frame> {
		
		public Scramble() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ Scrambler ================");
			FEC_Frame frame = pop();
			FEC_Frame frame2 = ScramblerOut(frame);
			push(frame2);
		}
	}
	
  public static FEC_Frame ScramblerOut(FEC_Frame frame){
        int BBFRAMElength = 64800;
        int counter = 0;
        boolean newinput = false;
        boolean[] inputstream = frame.FEC_frame;
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
        	
        return new FEC_Frame(outputstream);
        
    }  

}
