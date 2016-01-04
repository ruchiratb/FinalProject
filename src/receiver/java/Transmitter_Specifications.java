package receiver.java;

public final class Transmitter_Specifications {
	
	// P1 field
	final boolean MIXED = false; 
	
	// P2 field
	final boolean EXTENDED_CARRIER_MODE = false; //normal mode
	final boolean REPITITION_FLAG = false;
	final int PAPR = 0; //not used 4 bits
	final int NUM_T2_FRAMES = 2;  // 8 bit
	final int NUM_DATA_SYMBOLS = 60;  // 12 bit
	final int REGEN_FLAG = 0; // 3 bit
	final int SUB_SLICES_PER_FRAME = 1;  //15 bit
	final int NUM_RF = 1;
	final int FREQUENCY = 10000000;  // 32 bit
	final int PLP_ID = 0;  // one PLP   8 bit
	final int PLP_TYPE = 0;
	final boolean PLP_ROTATION = true;  // constellation rotation used
	final int PreLength = 200;
	final int PostLength = 350;
	final int FFT_Size = 32;
	
	static final int Kbch = 24400;
	
	public int get_Num_data_symbols(){
		return NUM_DATA_SYMBOLS;
	}
}
