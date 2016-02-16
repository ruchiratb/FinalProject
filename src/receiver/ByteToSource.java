package receiver;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class ByteToSource extends edu.mit.streamjit.api.Pipeline<Byte, Byte>{
	
	public ByteToSource(){
		this.add(new WriteToFile());
	}
	
	private static class WriteToFile extends edu.mit.streamjit.api.Filter<Byte, Byte> {
		
		public WriteToFile() {
			super(1, 1);
		}

		@Override
		public void work() {
//			System.out.println("byte to source----------------------");
			byte value = pop();
			appendToFile(value);
			///////////////////////////////////////

/*			DataOutputStream out=null;		
				try {
					out = new DataOutputStream(new FileOutputStream("testsource.txt",true));
					out.write(value);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
*/
			/////////////////////////////////////////
			push(value);
			
		}
		
		
		private static void appendToFile(byte value){
			
			DataOutputStream out=null;
//			DataInputStream in=null;
			try {
				out = new DataOutputStream(new FileOutputStream("testsource.yuv",true));
//				in = new DataInputStream(new FileInputStream("data.in"));				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}					
				
				try {
					out.write(value);
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			try {
//				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
