package transmitter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bit_Interleaver {
	static FEC_Frame fec = new FEC_Frame();
	static int N_ldpc = fec.frameLength;     //FECFREAME Size
	
	public ArrayList<FEC_Frame> interleave(ArrayList<FEC_Frame> frames) {
		System.out.println("============ Inside Bit Interleaver==============");
		ArrayList<FEC_Frame> interleaved_frames = new ArrayList<FEC_Frame>();
		int number_of_frames = frames.size();
		int K_ldpc = fec.Kbch;     //BBFRAME+BCH Size
		int Q_ldpc = 4;		//
		boolean[] D_in, D_out;
		for (int a = 0; a < number_of_frames; a++) {			
			D_in = frames.get(a).FEC_frame;
			D_out = new boolean[N_ldpc];
			int framecount = 0;
			
			for (long k = 0; k < number_of_frames; k++) {
//			System.out.println("count: "+(framecount+1));
				for(int i=0;i<K_ldpc;i++){
					D_out[i]=D_in[(int) (i+(framecount*N_ldpc))];                              //Information bits are not interleaved
				}	
				
				for(long t=0;t<Q_ldpc;t++){
					for(long s=0;s<360;s++){
						D_out[(int) (K_ldpc+360*t+s)]=D_in[(int) (K_ldpc+Q_ldpc*s+t)]; //parity interleaving
						
					}
				}

				framecount++;
			}
/*				
			for(int i=0;i<N_ldpc;i++){
				System.out.print(D_in[i]+" ");
				System.out.println(D_out[i]+" ");    //output
			}*/
			
		}
		
		return interleaved_frames;
	}
	
	private static boolean[] generateData(){
		boolean[] data = new boolean[N_ldpc];
		Random r = new Random();
		for (int i = 0; i < data.length; i++) {
			if (r.nextDouble() > 0.5) {
				data[i] = true;
			}else {
				data[i] = false;
			}
		}
		return data;
	}
}