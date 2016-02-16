package receiver.java;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class InputstreamBuilder {

	public static void main(String[] args) {
		int data_length= 352*144*3*10;	
//		writefile(data_length);
		
		DataOutputStream out=null;
//		DataInputStream in=null;
		try {
			out = new DataOutputStream(new FileOutputStream("E:\\Project\\inputdata\\numbers2.in"));
//			in = new DataInputStream(new FileInputStream("data.in"));				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Random r=new Random();
				
		for (int i = 0; i < data_length; i++) {
			byte l=(byte) r.nextInt(2);		
			
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
	
	private static void writefile(int length){
		Random r=new Random();
		int l;
		String text;
		for (int i = 0; i < length; i++) {
			l = r.nextInt(30);
			text = String.valueOf(l);
			try(
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("yuvtextdata.txt", true)))) {
				out.println(text);
			}catch (IOException e) {
				e.printStackTrace();
			}			
		}
		System.out.println("Done..");
	}

}

