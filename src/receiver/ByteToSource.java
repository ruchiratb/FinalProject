package receiver;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteToSource extends edu.mit.streamjit.api.Pipeline<Byte, Byte>{
	
	public ByteToSource(){
		this.add(new WriteToFile());
	}
	
	public static class WriteToFile extends edu.mit.streamjit.api.Filter<Byte, Byte> {
		
		public WriteToFile() {
			super(20, 20);
		}

		@Override
		public void work() {
//			System.out.println("byte to source----------------------");
//			byte value = pop();
//			appendToFile(value);
			///////////////////////////////////////
			byte[] data = new byte[20];
			for (int i = 0; i < data.length; i++) {
				data[i] = pop();
				push(data[i]);
			}
			byteToSource(data);
			/////////////////////////////////////////
//			push(value);
			
		}
		
		public static void byteToSource(byte[] data){
			System.out.println("data length ="+data.length);
			int size = data.length;
			
			DataOutputStream out=null;
//			DataInputStream in=null;
			try {
				out = new DataOutputStream(new FileOutputStream("E:\\Project\\out.yuv",true));
//				in = new DataInputStream(new FileInputStream("data.in"));				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			for (int i = 0; i < size; i++) {
				byte l=data[i];		
				
				try {
					out.write(l);
//					System.out.print(l+" ");
//					byte val=in.readByte();
//					System.out.println(val);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
//				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Done..");
		}
		
		
		public static void appendToFile(byte value){
			
			DataOutputStream out=null;
//			DataInputStream in=null;
			try {
				out = new DataOutputStream(new FileOutputStream("E:\\videocoding\\yuv\\out.yuv",true));
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
