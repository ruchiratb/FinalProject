package receiver.java;

/**
 *
 * @author Nipuna Priyamal
 */
public class De_Scrambler {
    
        public static void main(String[] args) {

		boolean [] inputstream = new boolean[] {false,true,true,false,false,true,true,true,true,false,false,true,false,false,false,true,false,false,false,false,true,false,true,false,true,false,true,false};
                //boolean [] inputstream = new boolean[] {false,true,true,false,false,true,false,false,false,true,true,false,false,true,true,true,false,false,false,false};
                // input data sequence 100101010000000
              
                boolean[] outputstream = new boolean[inputstream.length];
                outputstream = ScramblerOut(inputstream);
                System.out.print("----------------Final Output-----------------  "+"\n");
		for (int i = 0; i <inputstream.length; i++) {
			System.out.print(outputstream[i]+"  ");
		}
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
