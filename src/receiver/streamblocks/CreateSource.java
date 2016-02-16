package receiver.streamblocks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.jeffreybosboom.serviceproviderprocessor.ServiceProvider;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.Input;

import edu.mit.streamjit.api.Pipeline;

import edu.mit.streamjit.impl.compiler2.Compiler2StreamCompiler;
import edu.mit.streamjit.test.Benchmark;
import edu.mit.streamjit.test.Benchmarker;
import edu.mit.streamjit.test.SuppliedBenchmark;

public class CreateSource {
	public static void main(String[] args) throws InterruptedException {
		Compiler2StreamCompiler sc = new Compiler2StreamCompiler();
		sc.maxNumCores(4);
		sc.multiplier(4);
		Benchmarker.runBenchmark(new TransmitterBenchmark(), sc).get(0).print(System.out);
	}
	
	@ServiceProvider(Benchmark.class)
	public static final class TransmitterBenchmark extends SuppliedBenchmark {
		public TransmitterBenchmark() {
			super("Transmitter", TransmitterKernel.class, new Dataset("stream.in",
					(Input)Input.fromBinaryFile(Paths.get("stream.in"), Byte.class, ByteOrder.LITTLE_ENDIAN)
//					, (Supplier)Suppliers.ofInstance((Input)Input.fromBinaryFile(Paths.get("/home/jbosboom/streamit/streams/apps/benchmarks/asplos06/fft/streamit/FFT5.out"), Float.class, ByteOrder.LITTLE_ENDIAN))
			));
		}
	}
	
	public static final class TransmitterKernel extends Pipeline<Byte, Byte> {
		
		public TransmitterKernel() {
			this.add(new WriteSource());
		}		
	}
	
	private static class WriteSource extends Filter<Byte, Byte> {
		
		public WriteSource() {
			super(8, 1);
		}

		@Override
		public void work() {
			StringBuilder builder = new StringBuilder();
			builder.setLength(0);
			byte[] bit8 = new byte[8];
			for (int i = 0; i < bit8.length; i++) {
				bit8[i] = pop();
				builder.append(bit8[i]);
			}
			System.out.print(builder.toString());
			byte value = (byte) Integer.parseInt(builder.toString(), 2);
			System.out.println("      "+value);
			try {
				appendToFile(String.valueOf(value));
//				Files.write(Paths.get("C:\\Users\\Ruchira\\Desktop\\testfile.txt"), "the text".getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("illegal argument exception");
				e.printStackTrace();
			}
			push(value);
		}
		
		private static void appendToFile(String text) throws IOException{
			 BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\Ruchira\\Desktop\\testfile.txt"));
			 out.write(text);
			 out.close();
//	         out = new BufferedWriter(new FileWriter("C:\\Users\\Ruchira\\Desktop\\testfile.txt",true));
//	         out.write("aString2");
//	         out.close();
	        
	       /* BufferedReader in = new BufferedReader(new FileReader("filename"));
	        String str;
	        while ((str = in.readLine()) != null) {
	           System.out.println(str);
	        }*/
		}
		
		
	private static void byteToSource(byte data){
		
		DataOutputStream out=null;
//		DataInputStream in=null;
		try {
			out = new DataOutputStream(new FileOutputStream("E:\\videocoding\\video.yuv"));
//			in = new DataInputStream(new FileInputStream("data.in"));				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
			byte l=data;		
			
			try {
				out.write(l);
			} catch (IOException e) {
				e.printStackTrace();
			}
/*		
		try {
//			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
		
		
	}

	private static class WriteBytes extends Filter<Byte, Byte> {
		
		public WriteBytes() {
			super(8, 1);
		}

		@Override
		public void work() {
			StringBuilder builder = new StringBuilder();
			byte[] bit8 = new byte[8];
			for (int i = 0; i < bit8.length; i++) {
				bit8[i] = pop();
				builder.append(bit8[i]);
			}
			System.out.print(builder.toString());
			byte value = (byte) Integer.parseInt(builder.toString(), 2);
			System.out.println("      "+value);
			push(value);

		}
		
		
	private static void byteToSource(byte[] data){
		System.out.println("data length ="+data.length);
		
		DataOutputStream out=null;
//		DataInputStream in=null;
		try {
			out = new DataOutputStream(new FileOutputStream("E:\\videocoding\\video.yuv"));
//			in = new DataInputStream(new FileInputStream("data.in"));				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		for (int i = 0; i < data.length; i++) {
			byte l=data[i];		
			
			try {
				out.write(l);
//				System.out.print(l+" ");
//				byte val=in.readByte();
//				System.out.println(val);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
//			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Done..");
	}
	}
	
}
