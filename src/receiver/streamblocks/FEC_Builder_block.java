package receiver.streamblocks;

import java.nio.ByteOrder;
import java.nio.file.Paths;
import java.util.Arrays;

import org.jscience.mathematics.number.Complex;

import com.jeffreybosboom.serviceproviderprocessor.ServiceProvider;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.Input;
import edu.mit.streamjit.api.Pipeline;
import edu.mit.streamjit.impl.compiler2.Compiler2StreamCompiler;
import edu.mit.streamjit.test.Benchmark;
import edu.mit.streamjit.test.Benchmarker;
import edu.mit.streamjit.test.SuppliedBenchmark;
import transmitter.FEC_Frame;

public class FEC_Builder_block {
	public static void main(String[] args) throws InterruptedException {
		//compile2streamcompiler
		Compiler2StreamCompiler sc = new Compiler2StreamCompiler();
		sc.maxNumCores(1);
		sc.multiplier(1);
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
	
	public static final class TransmitterKernel extends edu.mit.streamjit.api.Pipeline<Byte, Byte> {
		
		public TransmitterKernel() {
			// filter blocks are executer according to this order
//			this.add(new Add5(),new AddPair(),new Add5(),  new Printer());
			this.add(
					new buildFEC(),
					new LDPC_Decode_block(),
//					new DataFieldTemp(),
					new BBHeaderRemove_block()
//					new BitsFromFEC()
					,new DataWriter()
			);
		}		
	}
	
	private static class buildFEC extends edu.mit.streamjit.api.Filter<Byte, FEC_Frame> {
		
		public buildFEC() {
			super(64800, 1);
		}

		@Override
		public void work() {
			System.out.println("fec frame--------------------");
			boolean[] fec_data = new boolean[64800];
			for (int i = 0; i < fec_data.length; i++) {
				byte temp = pop();
				if (temp == 1) {
					fec_data[i] = true;
				}else {
					fec_data[i] = false;
				}				
			}
			FEC_Frame frame = new FEC_Frame(fec_data);
			push(frame);
		}
	}
/*	
	private static class DataFieldTemp extends Filter<Byte, FEC_Frame> {
		
		public DataFieldTemp() {
			super(32208, 1);
		}

		@Override
		public void work() {
			System.out.println("data field--------------------");
			boolean[] fec_data = new boolean[32208];
			for (int i = 0; i < fec_data.length; i++) {
				byte temp = pop();
				if (temp == 1) {
					fec_data[i] = true;
				}else {
					fec_data[i] = false;
				}
				
				FEC_Frame frame = new FEC_Frame(fec_data);
				push(frame);
				
			}
		}
	}
	
	private static class BitsFromFEC extends Filter<Byte, Byte> {
		
		public BitsFromFEC() {
			super(32208, 32208-80);
		}

		@Override
		public void work() {
			boolean[] datawithbb = new boolean[32208];
			for (int i = 0; i < datawithbb.length; i++) {
				Byte temp = pop();
				if (temp == 1) {
//					System.out.print("1");
					datawithbb[i] = true;
				} else {
//					System.out.print("0");
					datawithbb[i] = false;
				}
			}
			boolean[] data = new boolean[32208-80];
			for (int i = 0; i < data.length; i++) {
				data[i] = datawithbb[i+80];
			}
			for (int i = 0; i < data.length; i++) {
				if (data[i]) {
					System.out.print("1");
					push((byte) 1);
				} else {
					System.out.print("0");
					push((byte) 0);
				}
				if (i%1000 ==0) {
					System.out.println();
				}
			}
		}
	}

	private static class Decode extends Filter<FEC_Frame, FEC_Frame> {
		
		public Decode() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("LDPC decoding--------------------");
			FEC_Frame frame_with_ldpc = pop();
			boolean[] ldpc_array = frame_with_ldpc.getFEC_Data();
//			boolean[] data_array = Arrays.copyOfRange(ldpc_array, 0, 32208);  // from 0 to 32207  Kbch length = 32208
			boolean[] data_array = new boolean[32208];
			FEC_Frame data_frame = new FEC_Frame(data_array);
			push(data_frame);
		}
	}
*/
	
	
}
