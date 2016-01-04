package transmitter;

/*
 * source : http://www.rgagnon.com/javadetails/java-0667.html
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;

public class HexDump {

  public static String hexDump(File file) throws IOException {
	  FileWriter fw = new FileWriter("outfile.txt");
      InputStream is = new FileInputStream(file);
      StringBuilder sb1 = new StringBuilder();
      while (is.available() > 0) {
        for (int j = 0; j < 16; j++) {
           if (is.available() > 0) {
             int value = (int) is.read();
             sb1.append(String.format("%02X ", value));             
           }
           else {
             for (;j < 16;j++) {
               sb1.append("   ");
             }
           }
        }
        
        if (sb1.length() > 241550) {
			break;
		}

      }
      fw.write(sb1.toString());
      is.close();
      fw.close();
      return sb1.toString();
  }

  
//  public static void main(String args[]) throws Exception {
//    // dump to the console
////	  HexDump.hexDump(System.out, new File("football.ts"));
//    // dump to a file
//	  String s = HexDump.hexDump(new File("football.ts"));
//	  System.out.println("Done.");
//  }  
}