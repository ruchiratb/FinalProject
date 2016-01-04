package receiver;


import java.nio.ByteOrder;
import java.nio.file.Paths;

import org.jscience.mathematics.number.Complex;

import com.jeffreybosboom.serviceproviderprocessor.ServiceProvider;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.Input;

import edu.mit.streamjit.api.Pipeline;

import edu.mit.streamjit.impl.compiler2.Compiler2StreamCompiler;
import edu.mit.streamjit.test.Benchmark;
import edu.mit.streamjit.test.Benchmarker;
import edu.mit.streamjit.test.SuppliedBenchmark;
import receiver.java.Transmitter_Specifications;
import transmitter.Super_Frame;
import transmitter.T2_Frame;

public class FrameBuilder extends edu.mit.streamjit.api.Pipeline<Complex, Super_Frame> {
	
	public FrameBuilder(){
		this.add(new T2_Build(), new Super_Build());
	}
	
	private static class T2_Build extends Filter<Complex, T2_Frame> {
		public T2_Build() {
			super(60, 1);
		}

		@Override
		public void work() {
			System.out.println("\nt2 frame----------------");
			Complex[] data_symbols = new Complex[60];
			for (int i = 0; i < data_symbols.length; i++) {
				Complex c = pop();
				System.out.print(c+"\t");
				data_symbols[i] = c;
			}
			push(new T2_Frame(data_symbols));
		}
		
		
	}
	
	private static class Super_Build extends Filter<T2_Frame, Super_Frame> {
		int number_of_t2 = 2;
		public Super_Build() {
			super(2, 1);
		}

		@Override
		public void work() {
			System.out.println("\nsuper frame----------------");
			Super_Frame sframe = new Super_Frame(pop(), pop());
			push(sframe);
		}
		
		
	}

}

