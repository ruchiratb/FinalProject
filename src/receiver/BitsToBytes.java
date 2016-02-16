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
import transmitter.streamjit.DataWriter;

public class BitsToBytes extends edu.mit.streamjit.api.Pipeline<Byte, Byte>{
	
	public BitsToBytes(){
		this.add(new MakeBytes());
	}
	
	private static class MakeBytes extends edu.mit.streamjit.api.Filter<Byte, Byte> {
		
		public MakeBytes() {
			super(8, 1);
		}

		@Override
		public void work() {
//			System.out.println("bits to byte------------------------");
			String path = "E:\\workspace\\FinalProject\\testvideo.yuv";
			byte[] data_array = new byte[8];
			StringBuilder builder = new StringBuilder();
			byte byte_value;
							
			for (int i = 0; i < 8; i++) {
				data_array[i] = pop();
				builder.append(data_array[i]);
			}
			byte_value = (byte) Integer.parseInt(builder.toString(),2);
//			System.out.println(builder.toString()+"      "+byte_value);
//			WriteToFile writer = new WriteToFile();
//			writer.appendToFile(byte_value, path);
			System.out.println(byte_value+" ");
			push(byte_value);		
			
		}

	}
}
