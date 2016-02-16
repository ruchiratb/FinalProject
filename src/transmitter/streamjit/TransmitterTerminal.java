package transmitter.streamjit;


import java.io.IOException;
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
import receiver.ByteToSource;
import receiver.ReceiverTerminal_2;
import transmitter.FEC_Frame;

public class TransmitterTerminal {
	public static void main(String[] args) throws InterruptedException, IOException {	
		System.out.println("========================== TRANSMITTER START =============================");
		//compile2streamcompiler
		Compiler2StreamCompiler sc = new Compiler2StreamCompiler();
		sc.maxNumCores(4);
		sc.multiplier(4);
		Benchmarker.runBenchmark(new TransmitterBenchmark(), sc).get(0).print(System.out);
	}
	
	@ServiceProvider(Benchmark.class)
	public static final class TransmitterBenchmark extends SuppliedBenchmark {
		public TransmitterBenchmark() {
			super("Transmitter", TransmitterKernel.class, new Dataset("E:\\videocoding\\yuv\\randomsource.yuv",
					(Input)Input.fromBinaryFile(Paths.get("E:\\videocoding\\yuv\\randomsource.yuv"), Byte.class, ByteOrder.LITTLE_ENDIAN)
//					, (Supplier)Suppliers.ofInstance((Input)Input.fromBinaryFile(Paths.get("/home/jbosboom/streamit/streams/apps/benchmarks/asplos06/fft/streamit/FFT5.out"), Float.class, ByteOrder.LITTLE_ENDIAN))
			));
		}
	}
	
	public static final class TransmitterKernel extends Pipeline<Byte, Complex> {
		
		public TransmitterKernel() {
			this.add(
//					new Print()
					new ByteToBits()
					, new InputInterface()
					, new BB_Header_Insertion()
					, new Scrambler()
//	//				, new FakePolynomial()  // 32400 is already filled in input interface stage
//					, new LDPC()
					, new Parity_Interleaver()
					, new Column_Twist()
					, new Demux()
					, new ConstellationMapper()
//					, new Normalizer()
//					, new ConstellationRotator()
//	//				, new CellInterleaver()
//	//				, new GCD_4()
//					, new T2FrameBuilder()
//					, new SuperFrameBuilder()
//	//				, new IFFT() 
//					 new Byte_Pop_Push()
//	//				, new Rician_Channel()
					, new ReceiverTerminal_2()
//				, new ByteToSource()
//	//				, new ReceiverTerminal_2()
			);
		}		
	}

	private static class Print extends Filter<Byte, Byte> {
		
		public Print() {
			super(1, 1);
		}

		@Override
		public void work() {
			byte a = pop();
			System.out.println(a);
			push(a);
		}
		
	}
	
private static class ByteToBits extends Filter<Byte, Byte> {
		
		public ByteToBits() {
			super(1, 8);
		}

		@Override
		public void work() {
			byte n = pop();
//			System.out.println(n);
			String s = String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
			
			// for negative numbers toBinaryString method returns a string with length 32. Need to get the 8 rightmost values of it.
			if (s.length() > 8) {
				s = s.substring(s.length()-8, s.length());
			}
//			System.out.println(n+"    "+s);
//			System.out.println(s);
			

			byte x;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '1') {
					x = 1;
				} else {
					x = 0;
				}
				push(x);
			}
		}
		
	}
	
	
	private static class FakePolynomial extends Filter<FEC_Frame, FEC_Frame> {
		
		public FakePolynomial() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ Fake Polynomial =============");
			FEC_Frame frame = pop();
			boolean[] data = frame.FEC_frame;
			boolean[] bchdata = new boolean[32400];
			
			for (int i = 0; i < data.length; i++) {
				bchdata[i] = data[i];
			}
			push(new FEC_Frame(bchdata));
		}
		
	}
	
	
	
/*	
	private static class Fill_Data_Field extends Filter<Byte, FEC_Frame> {
		
		public Fill_Data_Field() {
			super(32128, 1);
		}

		@Override
		public void work() {
			System.out.println("============================");
			boolean[] data = new boolean[32128];
			byte temp;
			for (int i = 0; i < data.length; i++) {
				temp = pop();
				if(temp == 1)
					data[i] = true;
				else
					data[i] = false;
			}
			FEC_Frame frame = new FEC_Frame(data);
//			printDataField(frame);
			push(frame);
		}
		
	}
*/
}

