package receiver;

import java.nio.ByteOrder;
import java.nio.file.Paths;
import java.util.Random;

import org.jscience.mathematics.number.Complex;

import com.jeffreybosboom.serviceproviderprocessor.ServiceProvider;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.Input;

import edu.mit.streamjit.api.Pipeline;

import edu.mit.streamjit.impl.compiler2.Compiler2StreamCompiler;
import edu.mit.streamjit.test.Benchmark;
import edu.mit.streamjit.test.Benchmarker;
import edu.mit.streamjit.test.SuppliedBenchmark;

public class ExtractData extends edu.mit.streamjit.api.Pipeline<Byte, Complex>{
	
	public ExtractData(){
		this.add(new ConvertToComplex());
	}
	
	private static class ConvertToComplex extends Filter<Byte, Complex> {
		
		public ConvertToComplex() {
			super(128, 64);
		}

		
		@Override
		public void work(){
			Random rand = new Random();
			byte real, img;
//			System.out.println();
			for (int i = 0; i < 64; i++) {
				real = pop(); img=pop();
//				System.out.print("pair : "+real+" "+img+" ");
				Complex out = Complex.valueOf(real+rand.nextGaussian(), img+rand.nextGaussian());
				push(out);
			}
		}
		/*public void work() {
			Byte a = pop();
			Byte b = pop();
			Complex out = Complex.valueOf(a, b);
			System.out.print(out+"\t");
			push(out);
		}*/
		
	}

}

