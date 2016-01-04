package transmitter;

import java.io.IOException;
import java.util.ArrayList;

import org.jscience.mathematics.number.Complex;

public class TransmitterAgent {

	public static void main(String [] args) throws IOException {
		String sourcepath = "E:\\workspace\\FinalProject\\football.ts";
		String sourcebits;
		sourcebits = BitsFromHex.getBitsFromSource(sourcepath);
//		System.out.println("source bits:\n" + sourcebits);
		System.out.println("Total number of bits from source: " + sourcebits.length());
		
		ArrayList<FEC_Frame> frame_list = new ArrayList<FEC_Frame>();
		InputInterface inpif = new InputInterface();
		frame_list = inpif.fill_DataField(sourcebits);
		System.out.println("frames: "+frame_list.size());
//		printDataField(frame_list);
		
		System.out.println("\n\n--------------------- After Header Insertion, before scrambler--------------------");
		frame_list = BB_Header_Insertion.insertHeader(frame_list);
//		printDataField(frame_list);
		
		ArrayList<FEC_Frame> scrambbled_frames = new ArrayList<FEC_Frame>();
		Scrambler scram = new Scrambler();
		scrambbled_frames = scram.ScramblerOut(frame_list);
		System.out.println("\n\n============== scrambled frames ======================");
//		printDataField(scrambbled_frames);
		
		System.out.println("=========== BCH encode ===================");
		Polynomial poly = new Polynomial();
		ArrayList<FEC_Frame> bchencoded = poly.addrandom(scrambbled_frames);
		System.out.println("bchencoded size ="+bchencoded.size());
		System.out.println();
		System.out.println("length ="+bchencoded.get(1).FEC_frame.length);
		System.out.println();
		
		System.out.println("=========== LDPC encode ===================");
		LdpcNewNew ldpc = new LdpcNewNew();
		ArrayList<FEC_Frame> ldpcencoded = ldpc.doldpc(bchencoded);
		System.out.println("bchencoded size ="+ldpcencoded.size());
		System.out.println();
		System.out.println("length ="+ldpcencoded.get(0).FEC_frame.length);
		System.out.println();
		
		System.out.println("================= Bit Interleave ======================");
		Parity_Interleave bitint = new Parity_Interleave();
		ArrayList<FEC_Frame> interleaved_frames = bitint.do_bitinterleave(ldpcencoded);
		System.out.println("    number of interleaved frames= "+interleaved_frames.size());
		
		System.out.println("================= Column Twist ======================");
		Column_Twist twist = new Column_Twist();
		ArrayList<FEC_Frame> twistedframes = twist.do_columntwist(interleaved_frames);
		System.out.println("    number of twisted frames= "+twistedframes.size());
		
		//----------------- demux------------------------------
		System.out.println("\n\n============== demuxed data ======================");
		Demux2 demux = new Demux2();
		Eightout demuxed_arrays = demux.Demuxout(twistedframes);
		printEightout(demuxed_arrays);
		
		Constellation_mapper mapper = new Constellation_mapper();
		Complex[] initial_symbols = mapper.Cellqueue(demuxed_arrays);
		
		Normalizer norm = new Normalizer();
		Complex[] normalized_symbols = norm.Normalizerout(initial_symbols);
		
		Constellation_rotation rotator = new Constellation_rotation();
		Complex[] rotated_symbols = rotator.Rotationout(normalized_symbols);
		
		T2_Frame_Builder t2framebuilder = new T2_Frame_Builder();
		T2_Frame[] t2frames = t2framebuilder.getT2Frames(rotated_symbols);
		System.out.println("Number of T2 Frames = "+t2frames.length);
		
		Super_Frame_Builder superframebuilder = new Super_Frame_Builder();
		Super_Frame[] superframes = superframebuilder.getSuperFrames(t2frames);
		System.out.println("======= number of super frames = "+superframes.length);
	}
	
	
	private static void printDataField(ArrayList<FEC_Frame> frames) {
		for (int k = 0; k < frames.size(); k++) {
			FEC_Frame tempframe = frames.get(k);
			boolean[] data = tempframe.FEC_frame;
			System.out.println("\n-------------- Frame "+(k+1)+"-----------------------");
			for (int i = 0; i < data.length; i++) {
				
				if (data[i] == true) {
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
				}
			}
		}		
	}
	
	private static void printEightout(Eightout eight) {
		printArray(eight.getArray0());		
		printArray(eight.getArray1());		
		printArray(eight.getArray2());		
		printArray(eight.getArray3());		
		printArray(eight.getArray4());		
		printArray(eight.getArray5());		
		printArray(eight.getArray6());		
		printArray(eight.getArray7());		
	}
	
    private static void printArray(boolean[] array) {
		System.out.println();		
			for (int i = 0; i < array.length; i++) {
				if (array[i] == true) {
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
				}
				
				if (i%1000 == 0) {
					System.out.println();
				}
			}
		
	}

}
