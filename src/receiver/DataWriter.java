package receiver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class DataWriter extends edu.mit.streamjit.api.Pipeline<FEC_Frame, Byte>{
	
	public DataWriter(){
		this.add(new WriteToFile());
	}
	
	private static class WriteToFile extends edu.mit.streamjit.api.Filter<FEC_Frame, Byte> {
		
		public WriteToFile() {
			super(1, 32128);
		}

		@Override
		public void work() {
			FEC_Frame current_frame = pop();
			boolean[] data_array = current_frame.getFEC_Data();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < data_array.length; i++) {
				boolean temp = data_array[i];
				if (temp) {
					builder.append("1");
					push((byte) 1);
				}else {
					builder.append("0");
					push((byte) 0);
				}
			}
			appendToFile(builder.toString());
		}
		
		private void appendToFile(String text){
			try(
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("final_output.txt", true)))) {
				    out.println(text);
			}catch (IOException e) {
				    e.printStackTrace();
			}
		}
	}
}
