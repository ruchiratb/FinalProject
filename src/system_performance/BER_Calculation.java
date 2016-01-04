package system_performance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BER_Calculation {

	public static void main(String[] args) throws IOException {
		String input, output;
		NumberFormat formatter = new DecimalFormat();
		File input_file  = new File("E:\\workspace\\FYP\\inputstream.txt");
		File output_file = new File("E:\\workspace\\FYP\\final.txt");
		BufferedReader reader;
		
		// get input file to a string
		reader = new BufferedReader(new FileReader(input_file));		
		input = reader.readLine().toString();	
//		System.out.println(input);
		reader.close();
		
		// get output file to a string
		reader = new BufferedReader(new FileReader(output_file));	
		output = reader.readLine().toString();
//		System.out.println(output);

		double BER = getBER(input, output);
//		System.out.println("BIT ERROR RATE: "+BER);
		formatter = new DecimalFormat("0.#####E0");
	    System.out.println("BIT ERROR RATE: "+formatter.format(BER)); 
		System.out.println();
		
		reader.close();
		
	}
	
	private static double getBER(String input, String output){
		long start = System.currentTimeMillis();
		int input_length, output_length;
		input_length = input.length();
		output_length = input.length();
		System.out.println("Input Length: "+input_length);
		System.out.println("Output Length: "+output_length);
		
		int difference = Math.abs((input_length - output_length));
		System.out.println("Difference: "+difference);
		
		int compareUpto;
		if (input_length > output_length) {
			compareUpto = output_length;
		}else {
			compareUpto = input_length;
		}
		
		int errors = 0;
		
		int total_errors;
		for (int i = 0; i < compareUpto-2; i+=2) {
			if (input.charAt(i) != output.charAt(i)) {
				errors++;
			}
		}
		System.out.println("errors: "+errors);
		total_errors = errors+difference;
		System.out.println("total errors: "+total_errors);
		double ber = (double)total_errors/input_length;
		long end = System.currentTimeMillis();
		System.out.println("Execution Time: "+(end - start)+" ms");
		
		return ber;
		
		
	}

}
