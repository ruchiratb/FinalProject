package transmitter;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * This converts a hex representation of a file content in to binary data
 * so the previous module should provide the hex data of the video file.
 */
public class BitsFromHex {

	public static String getBitsFromSource (String filepath) throws IOException {
//		HexFromTS ht = new HexFromTS();
//		String[] hexdata = ht.getHexData();
		
		File file = new File(filepath);
		HexDump dump = new HexDump();
		String hex = dump.hexDump(file);		
//		System.out.println("hex data\n"+hex);
		System.out.println(hex.length());  // 30720000
		
		int number_of_values = hex.length()/3;
		System.out.println(number_of_values);   // 10240000
		String[] hexdata = new String[number_of_values];
		StringTokenizer st = new StringTokenizer(hex, " ");
		int index = 0;
		while (st.hasMoreTokens()) {
			hexdata[index] = st.nextToken();
			index++;
		}
		
		StringBuilder builder = new StringBuilder();
		String temphex, tempstr;
		int tempint;
		
		for (int i = 0; i < hexdata.length; i++) {
			temphex = hexdata[i];
			tempint = Integer.parseInt(temphex, 16 );
			tempstr = String.format("%8s", Integer.toBinaryString(tempint)).replace(' ', '0');
			
			builder.append(tempstr);
		}
		
//		System.out.println(builder);
		System.out.println("BitsFromHex done...");
		return builder.toString();
	}

}
