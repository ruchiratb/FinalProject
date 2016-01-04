package transmitter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Parity_Interleave {
	
	public ArrayList<FEC_Frame> do_bitinterleave(ArrayList<FEC_Frame> frames) {
		int framecount = frames.size();
		int N_ldpc = 64800;     //FECFREAME Size
		int K_ldpc = 32400;     //BBFRAME+BCH Size
		int Q_ldpc = 90;		// for 1/2 code rate
		
		boolean[] D_in = new boolean[N_ldpc];
		boolean[] D_out = new boolean[N_ldpc];
		boolean[] Deint = new boolean[N_ldpc];
		
		ArrayList<FEC_Frame> outputframes = new ArrayList<FEC_Frame>();
		
		for (int k = 0; k < framecount; k++) {
			FEC_Frame currentframe = frames.get(k);
			boolean[] inputbits = currentframe.FEC_frame;
			D_in = inputbits;
			
			for(int i=0;i<K_ldpc;i++){
				D_out[i]=D_in[(int) (i)];                              //Information bits are not interleaved
			}	
						
			for(long t=0;t<Q_ldpc;t++){
				for(long s=0;s<360;s++){
					D_out[(int) (K_ldpc+360*t+s)]=D_in[(int) (K_ldpc+Q_ldpc*s+t)]; //parity interleaving
					
				}
			}
			
			/*for(long i=0;i<K_ldpc;i++){
				Deint[(int) i]=D_out[(int) i];
			}*/
			/*
			for(long t=0;t<Q_ldpc;t++){
				for(long s=0;s<360;s++){
					Deint[(int) (K_ldpc+Q_ldpc*s+t)]=D_out[(int) (K_ldpc+360*t+s)]; //parity de interleaving
					
				}
			}*/
			
			outputframes.add(new FEC_Frame(D_out));
		}
		
		
		//System.out.println(D_out[32402]);
		/*for(int i=0;i<N_ldpc;i++){
			System.out.print(D_in[i]+" ");
			System.out.print(D_out[i]+" ");    //output
//			System.out.println(Deint[i]);
			System.out.println();
		}*/
		
		return outputframes;
	}
	
	
}