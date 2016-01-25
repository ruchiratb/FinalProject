package transmitter.streamjit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;
import transmitter.FEC_Frame;

public class DataWriter extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
	
	public DataWriter(){
		this.add(new WriteToFile());
	}
	
	private static class WriteToFile extends edu.mit.streamjit.api.Filter<Complex, Complex> {
		
		public WriteToFile() {
			super(64, 64);
		}

		@Override
		public void work() {
			System.out.println("write to file----------------------");
			Complex[] data_array = new Complex[64];
			StringBuilder builder = new StringBuilder();
			Complex temp;
			for (int i = 0; i < data_array.length; i++) {
				temp = pop();
				data_array[i] = temp;
//				builder.append(temp.getReal()+" "+temp.getImaginary()+" ");
				builder.append((int)temp.getReal()+" "+(int)temp.getImaginary()+" ");
			}
			System.out.println("write...."+ builder.toString());
			appendToFile(builder.toString());
		}
		
		private static void appendToFile(String text){
			try(
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stream.in", true)))) {
					System.out.println("appending....");
				    out.println(text);
			}catch (IOException e) {
				    e.printStackTrace();
			}
		}
	}
}
