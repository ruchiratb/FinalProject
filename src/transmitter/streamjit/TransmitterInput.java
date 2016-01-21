package transmitter.streamjit;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TransmitterInput {

	public static void main(String[] args) {
		int data_length=128512;		
		
		DataOutputStream out=null;
//		DataInputStream in=null;
		try {
			out = new DataOutputStream(new FileOutputStream("transmitter_data.in"));
//			in = new DataInputStream(new FileInputStream("data.in"));				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Random r=new Random();
				
		for (int i = 0; i < data_length; i++) {
			int l=r.nextInt(2);		
			
			try {
				out.write(l);
				System.out.print(l+" ");
//				byte val=in.readByte();
//				System.out.println(val);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
//			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Done..");
	}

}

