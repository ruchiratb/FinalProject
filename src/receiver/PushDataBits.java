package receiver;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import receiver.FEC_Frame;

public class PushDataBits extends edu.mit.streamjit.api.Pipeline<FEC_Frame, Byte>{
	
	public PushDataBits(){
		this.add(new DataPush());
	}
	
	private static class DataPush extends edu.mit.streamjit.api.Filter<FEC_Frame, Byte> {
		
		public DataPush() {
			super(1, 64720);   // 32128
		}

		@Override
		public void work() {
//			System.out.println("data push----------------------");
			FEC_Frame current_frame = pop();
			boolean[] data_array = current_frame.getFEC_Data();
//			System.out.println("data array length="+data_array.length);
			StringBuilder builder = new StringBuilder();
			byte value;
			for (int i = 0; i < data_array.length; i++) {
				boolean temp = data_array[i];
				if (temp) {
					builder.append("1");
					value = 1;
				}else {
					builder.append("0");
					value = 0;
				}
//				appendToFile2(value);
				/////////////////////////////////////////////////////////////////////////

				
				/////////////////////////////////////////////////////////////////////////
				push(value);
			}
//			System.out.println("data bits from FEC: "+builder.toString().substring(0, 400));
//			appendToFile(builder.toString());
		}
		
		private static void appendToFile(String text){
			try(
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("final_output.txt", true)))) {
					System.out.println("appending....");
				    out.println(text);
			}catch (IOException e) {
				    e.printStackTrace();
			}
		}
		
private static void appendToFile2(byte value){
			
			DataOutputStream out=null;
//			DataInputStream in=null;
			try {
				out = new DataOutputStream(new FileOutputStream("testoutput.yuv",true));
//				in = new DataInputStream(new FileInputStream("data.in"));				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Random r=new Random();							
				
				try {
					out.write(value);
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
			
		}
	}
}
