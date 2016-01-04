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

public class ExtractData_block {
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
	
	public static final class TransmitterKernel extends Pipeline<Byte, Complex> {
		
		public TransmitterKernel() {
			// filter blocks are executer according to this order
//			this.add(new Add5(),new AddPair(),new Add5(),  new Printer());
			this.add(new ConvertToComplex());
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
			System.out.println(out);
			push(out);
		}
	}

}

