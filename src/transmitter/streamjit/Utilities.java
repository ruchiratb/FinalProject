package transmitter.streamjit;

import transmitter.FEC_Frame;

public class Utilities {
	
	public void printDataField(FEC_Frame frame) {
			boolean[] data = frame.FEC_frame;			
			System.out.println("\n-------------- Frame Length ="+data.length);
			for (int i = 0; i < data.length; i++) {
				if (i%100 == 0) {
					System.out.println();
				}
				if (data[i] == true) {
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
				}
			}					
	}
	
//  private static void printArray(boolean[] array) {
//		
//			for (int i = 0; i < array.length; i++) {
//				
//				if (array[i] == true) {
//					System.out.print("1 ");
//				}else {
//					System.out.print("0 ");
//				}
//			}
//		
//	}
}
