package receiver.streamblocks;


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

public class FrameBuilder_block {
	public static void main(String[] args) throws InterruptedException {
		//compile2streamcompiler
		Compiler2StreamCompiler sc = new Compiler2StreamCompiler();
		sc.maxNumCores(4);
		sc.multiplier(4);
		Benchmarker.runBenchmark(new TransmitterBenchmark(), sc).get(0).print(System.out);
	}
	
	@ServiceProvider(Benchmark.class)
	public static final class TransmitterBenchmark extends SuppliedBenchmark {
		public TransmitterBenchmark() {
			super("Transmitter", TransmitterKernel.class, new Dataset("receiver_data.in",
					(Input)Input.fromBinaryFile(Paths.get("receiver_data.in"), Byte.class, ByteOrder.LITTLE_ENDIAN)
//					, (Supplier)Suppliers.ofInstance((Input)Input.fromBinaryFile(Paths.get("/home/jbosboom/streamit/streams/apps/benchmarks/asplos06/fft/streamit/FFT5.out"), Float.class, ByteOrder.LITTLE_ENDIAN))
			));
		}
	}
	
	public static final class TransmitterKernel extends Pipeline<Byte, Super_Frame> {
		
		public TransmitterKernel() {
			// filter blocks are executer according to this order
//			this.add(new Add5(),new AddPair(),new Add5(),  new Printer());
			this.add(new ConvertToComplex(), new T2_Build(), new Super_Build());
		}		
	}
	
	private static class ConvertToComplex extends Filter<Byte, Complex> {
		
		public ConvertToComplex() {
			super(2, 1);
		}

		@Override
		public void work() {
			Byte a = pop();
			Byte b = pop();
			Complex out = Complex.valueOf(a, b);
//			System.out.println(out);
			push(out);
		}
	}
	
	private static class T2_Build extends Filter<Complex, T2_Frame> {
		int number_of_symbols = 60;
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

