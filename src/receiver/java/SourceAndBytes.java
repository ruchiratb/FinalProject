package receiver.java;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.imageio.ImageIO;


import java.nio.file.Path;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SourceAndBytes {
	
	public static void main(String[] args) throws IOException {
//		frame size of yuv is WIDTH X HEIGHT X 1.5
//		WIDTH = 352, HEIGHT = 288
		Path path = Paths.get("E:\\videocoding\\yuv\\stefan.yuv");
		byte[] randomdata = Files.readAllBytes(path);
		for (int i = 0; i < randomdata.length; i++) {
			randomdata[i] = (byte) (randomdata[i] +10);
		}
		byteToSource(randomdata);

	}

	private static void byteToSource(byte[] data){
		System.out.println("data length ="+data.length);
		
		DataOutputStream out=null;
//		DataInputStream in=null;
		try {
			out = new DataOutputStream(new FileOutputStream("E:\\videocoding\\yuv\\randomsource.yuv"));
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

	
	private static byte[] readBytesFromImage(Path path) throws IOException{

		int length = Files.readAllBytes(path).length;
		      FileInputStream fis = null;
		      int i = 0;
		      char c;
		      byte[] bs = new byte[length];
		         // create new file input stream
		         fis = new FileInputStream(path.toAbsolutePath().toString());
		      
		      try{
		        	 // read bytes to the buffer
		        	 i=fis.read(bs);
		        	 	        	 
		        	 
		        	 // for each byte in buffer
		        	 for(byte b:bs)
		        	 {
		        		 
		        		 // converts byte to character
		        		 c=(char)b;
		        		 
		        		 // print
//		        		 System.out.print(c);
		        	 }  
//					createImage(bs, "Image"+j);
		      }catch(Exception ex){
		         // if any error occurs
		         ex.printStackTrace();
		      }finally{
		         
		         // releases all system resources from the streams
		         if(fis!=null)
		            fis.close();
		      }
		  return bs;
	}
	
	public static void StreamBuilder(byte[] data) {
		int data_length=data.length;		
		
		DataOutputStream out=null;
//		DataInputStream in=null;
		try {
			out = new DataOutputStream(new FileOutputStream("E:\\workspace\\FYP\\data.in"));
//			in = new DataInputStream(new FileInputStream("data.in"));				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		for (int i = 0; i < 150000; i++) {
			int l=data[i];		
			
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
