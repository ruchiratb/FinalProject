package transmitter.streamjit;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

public class Byte_Pop_Push extends edu.mit.streamjit.api.Pipeline<Byte, Byte>{

	public Byte_Pop_Push(){
		this.add(new pop_push());
	}
	
	private static class pop_push extends Filter<Byte, Byte> {
		
		public pop_push() {
			super(64, 64);
		}

		@Override
		public void work() {
			StringBuilder builder = new StringBuilder();
			byte[] data = new byte[64];
			int counter = 0;
			builder.setLength(0);
			for (int i = 0; i < data.length; i++) {				
				data[i] = pop();
				builder.append(data[i]+" ");
			}
			System.out.println(builder.toString());
			for (int i = 0; i < 1000000; i++) {
				counter++;
			}
			for (int i = 0; i < data.length; i++) {
				push(data[i]);
			}
		}
	}
}
