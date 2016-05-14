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
import receiver.ReceiverTerminal;

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
		////E:\\videocoding\\yuv\\randomsource.yuv
		//E:\\Project\\inputdata\\data.in
		public TransmitterBenchmark() {
			super("Transmitter", TransmitterKernel.class, new Dataset("E:\\videocoding\\yuv\\stefan5.yuv",
					(Input)Input.fromBinaryFile(Paths.get("E:\\videocoding\\yuv\\stefan5.yuv"), Byte.class, ByteOrder.LITTLE_ENDIAN)
			));
		}
	}
	
	public static final class TransmitterKernel extends Pipeline<Byte, Complex> {
		
		public TransmitterKernel() {
			this.add(
					  new ByteToBits()  		
					, new InputInterface()		
					, new BB_Header_Insertion()
					, new Scrambler()			
					, new Parity_Interleaver()	
					, new Column_Twist()	
					, new Demux()
					, new ConstellationMapper()
					, new Normalizer()			
					, new ConstellationRotator()
					, new CellInterleaver()		
					, new T2FrameBuilder()
					, new SuperFrameBuilder()
					, new IFFT() 
					, new Channel()
					, new ReceiverTerminal()
			);
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

}

