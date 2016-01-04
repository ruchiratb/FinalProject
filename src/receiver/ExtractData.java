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

public class ExtractData extends edu.mit.streamjit.api.Pipeline<Byte, Complex>{
	
	public ExtractData(){
		this.add(new ConvertToComplex());
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
//			System.out.print(out+"\t");
			push(out);
		}
	}

}

