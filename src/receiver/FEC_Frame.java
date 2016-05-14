package receiver;

public class FEC_Frame {
	

	/*static final int frameLength = 64800; //64800 OR 16200
	static boolean[] FEC_frame = new boolean[frameLength];
	
	static final int Kbch = 48600;
	// LDPC + BCH = 64800 - 48600
	// Kbch = DFL + Padding + 80
	
	static final int DFL = 44600; // need padding of 48600-44600-80 bits	
	static final int pad = Kbch - DFL - 80;	*/	
	
	final int frameLength = 64800; 
	
	final int Kbch = 64720;
	public boolean[] FEC_frame;
	// LDPC + BCH = 64800 - 48600
	// Kbch = DFL + Padding + 80

	public final int BBHeaderLength = 80;
	final int DFL = Kbch - BBHeaderLength; // need padding of 48600-44600-80 bits	
	final int pad = Kbch - DFL - BBHeaderLength;	

	public FEC_Frame() {
		System.out.println("FEC_Frame Default constructor");
	}
	
	public FEC_Frame(boolean[] fec_frame) {
		this.FEC_frame = fec_frame;		
		
	}
	public boolean[] getFEC_Data(){
		return FEC_frame;
	}
}
