package transmitter.streamjit;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class Writer extends edu.mit.streamjit.api.Pipeline<Complex, Byte>{

	public Writer(){
		this.add(new WriteToFile());
	}
	
	private static class WriteToFile extends Filter<Complex, Byte> {
		
		public WriteToFile() {
			super(64, 128);
		}

		@Override
		public void work() {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < 64; i++) {
					Complex data = pop();
					int a = (int) data.getReal();
					int b = (int) data.getImaginary();
					builder.append(a+" "+b+" ");
					push((byte) a);
					push((byte) b);
				}
				
			try {
				String text = builder.toString();
				System.out.println("writer.....  "+text);				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			
			
/*			
			try(
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("final_output.txt", true)))) {
//					System.out.println("appending....");
				    out.println(text);
			}catch (IOException e) {
				    e.printStackTrace();
			}
			
			*/
			
			/*
			DataOutputStream out=null;
//			DataInputStream in=null;
			try {
				out = new DataOutputStream(new FileOutputStream("transmitter_out.in"));
//				in = new DataInputStream(new FileInputStream("data.in"));				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			
				int l=pop();		
				
				try {
					out.write(l);
//					System.out.print(l+" ");
//					byte val=in.readByte();
//					System.out.println(val);
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
			
//			System.out.println("Done..");
*/		}
		
		private static void appendToFile(String text){
			try(
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("final_output.txt", true)))) {
//					System.out.println("appending....");
				    out.println(text);
			}catch (IOException e) {
				    e.printStackTrace();
			}
		}
	}

}

