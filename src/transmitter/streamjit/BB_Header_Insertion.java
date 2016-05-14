package transmitter.streamjit;

import edu.mit.streamjit.api.Filter;
import transmitter.streamjit.FEC_Frame;

/**
 * 
 * @author Ruchira
 *
 */
/*
 * BB Header is abbreviated for Base-Band Header. This describes the format of the Data Field.
 * This has a length of 8 bytes (80 bits)
 * MATTYPE(2), UPL(2), DFL(2), SYNC(1), SYNCD(2), CRC-8 MODE(1)	
 */
public class BB_Header_Insertion extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{

	public BB_Header_Insertion(){
		this.add(new BBHeader());
	}
	
	private static String header_str = "11100000000000000110011101000011010110010010001001000111000000000000000000100010";
	private static int header_length = 80;
	
	private static class BBHeader extends Filter<FEC_Frame, FEC_Frame> {
		
		public BBHeader() {
			super(1, 1);
		}

		@Override
		public void work() {
			System.out.println("============ BB Header Insertion ================");
			FEC_Frame frame = pop();
			FEC_Frame frame2 = insertHeader(frame);
			push(frame2);
		}

	}
	
	public static FEC_Frame insertHeader(FEC_Frame frame) {
		boolean[] header_data;
			header_data = frame.FEC_frame;
			System.out.println(header_data.length);
			for (int j = 0; j < header_length ; j++) {
				if (header_str.charAt(j) == '1') {
					header_data[j] = true;
				} else {
					header_data[j] = false;
				}
			}
			
			/*System.out.println("\nafter header");
			for (int i = 0; i < 100; i++) {
				if (header_data[i] == true) {
					System.out.print("1");
				}else {
					System.out.print("0");
				}
				
			}
			System.out.println();*/
		
		return frame;
	}
	
	public static void printHeader() {
		byte[] bbHeader;
		int tempint;
		bbHeader = getBBHeader();
		String tempstr;
		StringBuilder builder = new StringBuilder();
		for (int i = bbHeader.length-1; i >=0 ; i--) {
			tempint = (int)(bbHeader[i]&0xff);
			tempstr = String.format("%8s", Integer.toBinaryString(tempint)).replace(' ', '0');
			builder.append(tempstr);
			System.out.print((bbHeader[i] & 0xff)+"  ");			
		}
		System.out.println(builder);
		System.out.println(builder.length());
		
	}
	
	public static byte[] getBBHeader(){
		byte[] header = new byte[10];
		// 1st byte: CRC8 MODE => CRC8 code XORed with MODE
		byte crc = 34;
		byte mode = 0; // since Normal Mode. For High Efficiency Mode = 1
		header[0] = (byte) (crc ^ mode);
		
		// 2nd byte, 3rd byte: syncd - 1, syncd - 2
		// set to 0, assuming transmission start from the beggining of the data field
		header[1] = 0;
		header[2] = 0;
		
		// 4th byte: SYNC byte => 1st byte of the user packet
		// for TS it is 47hex = 71decimal
		header[3] = 71;
		
		// 5th byte, 6 th byte : DFL
		header[4] = 34;
		header[5] = 89;
		
		// 7th byte, 8 th byte : UPL
		header[6] = 67;
		header[7] = 103;
		
		// 9th, 10th byte: MATYPE
		// MATYPE - 2: for single input stream = 0
		header[8] = 0;  
		// MATYPE - 1
		// 1 1 1 ? 0 0 _ _
		// TS/GS(2), SIS/MIS(1), CCM/ACM(1), ISSYI(1), NPD(1), EXT(future use) (2)
		header[9] = (byte) 224;
		
		return header;
	}

}
