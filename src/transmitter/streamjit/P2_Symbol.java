package transmitter.streamjit;

import java.util.BitSet;

public class P2_Symbol {
	static int count0 = 0, count1;
	static BitSet P2 = new BitSet();
	public static int NUM_T2_FRAMES = 2;
	public static int NUM_DATA_SYMBOLS = 64;
	
	public static void main(String[] args) {
		BitSet pre = new BitSet();
		BitSet post = new BitSet();
		pre = getL1PreSignal();
		post = getL1PostSymbol();
	}
	
	static BitSet getL1PreSignal(){
		BitSet L1_pre_signalling = new BitSet(200);
		int index = 0;
		
		// TYPE: 8 bits: 0x00 -> Transport Stream
		index+=8; 
		// BWT_EXT 1 bit: 0 ->extended carrier mode
		index++;
		
		// S1 3 bits: 000 -> SISO s1 in p1 symbol
		index+=3;
		// S4 4 bits: ??? 0 -> s2 in p1 symbol
		index+=4;
		
		//L1_REPITITION_FLAG 1 bit: 0 -> dynamic signaling shall not be provided for the next frame within this frame
		index++;
		// GUARD_INTERVAL 3 bit: ??? -> guard interval
		index+=3;
		// PAPR 4 bit: 0000 -> no PAPR used
		index+=4;
		
		// L1_MOD 4 bit: ???? -> L1 constellation
		index+=4;
		// L1_COD 2 bit: ?? -> code rate
		index+=2;
		// L1_FEC_TYPE 2 bit: ?? -> FEC for L1 post signalling
		index+=2;
		
		// L1_POST_SIZE 18 bit: ?? -> size of L1 post signalling block
		index+=18;
		// L1_POST_INFO_SIZE 18 bit: ?? size of info. part in L1 post signalling
		index+=18;
		
		// PILOT_PATTERN  4 bit: 0110 -> pp7
		index+=2; L1_pre_signalling.set(index); P2.set(index);
		index++;  L1_pre_signalling.set(index); P2.set(index);
		index++;
		
		// TX_ID_AVAILABILITY 8 bit: 0x00 -> no transmitter identification info
		index+=8;
		// CELL_ID 16 bit: 0 ->  no foreseen of geographical cell
		index+=16;
		// NETWORK_ID  16 bit: 0 -> identifies dvb network
		index+=16;
		// T2_SYSTEM_ID 16 bit: -> T2 system within the dvb network
		index+=16;
		// NUM_T2_FRAMES 8 bit: 00000010 -> 2 T2 frames per superframe (min is 2 frames)
		index+=7; L1_pre_signalling.set(index); P2.set(index);
		index++;
		
		// NUM_DATA_SYMBOLS 12 bit: 000000001000  ofdm symbols per T2 frame
		index+=9; L1_pre_signalling.set(index); P2.set(index);
		index+=3;
		
		// REGEN_FLAG 3 bit: 000 -> regeneration types
		index+=3;
		
		// L1_POST_EXTENSION 1 bit: ? - > presence of L1_post_EXTENSION field
		index++;
		// NUM_RF 3 bit 3 bit: 001 -> number of frequencies
		index+=3;
		// CURRENT_RF_IDX 3 bit: 000 -> TFS not supported
		index+=3;
		// T2_VERSION 4 bits: 0000 -> version 1.1.1
		index+=4;
		// L1_POST_SCRAMBLED 1 bit: 0 -> since t2_version is 0000
		index++;
		// T2_BASE_LITE 1 bit: 0 -> t2 version is 0000
		index++;		
		// RESERVED 4 bit -> for future use. used for bias balancing
		index+=4;
		// CRC-32 32 bits -> error detection code
		index+=32;	
				
		System.out.println("L1 pre signalling Length: "+index);		
		return L1_pre_signalling;
	}
	
	static BitSet getL1PostSymbol(){
		int index = 0;
		int preSignalLength = 200;
		BitSet L1_post_signalling = new BitSet();
		
		/**
		 * configurable field
		 */
		//SUB_SLICES_PER_FRAME 15 bit: 1D -> no type 2 PLP
		index+=15; L1_post_signalling.set(index); P2.set(preSignalLength+index);
		
		// NUM_PLP 8bit: 00000001 -> number of PLPs
		index+=8; L1_post_signalling.set(index); P2.set(preSignalLength+index);
		// NUM_AUX 4bit: 0000 -> no auxilliary streams
		index+=4;
		//AUX_CONFIG_RFU 8bit: for future use
		index+=8;
		// RF_IDX 3bit : 0 -> NUM_RF -1 = 0
		index+=3;
		// FREQUENCY 32 bit: 0 -> not known at the time of construction (Hz)
		index+=32;
		
		/*
		 *  S2 = 0000 therefore these 3 field are not required
		 */
	    // FEF_TYPE, FEF_LENGTH, FEF_INTERVAL
		
		/*
		 *  number of PLP is 1. therefore PLP loop has only one iteration
		 */
		//PLP_ID 8bit: 0 -> only one PLP
		index+=8;
		// PLP_TYPE 3bit: 000 -> common PLP
		index+=3;
		//PLP_PAYLOAD_TYPE 5bit: 00011 -> TS
		index+=4; L1_post_signalling.set(index); P2.set(preSignalLength+index);
		index++;  L1_post_signalling.set(index); P2.set(preSignalLength+index);
		//FF_FLAG 1bit: 0 -> TFS not used
		index++;
		//FIRST_RF_IDX: 3bit: 000 -> TFS not used
		index+=3;
		//FIRST_FRAME_IDX 8bit ?? - > IDX of the first frame of the superframe
		index+=8;
		// PLP_GROUP_ID 8bit: ??? -> PLP group of the current PLP
		index+=8;
		// PLP_COD 3bit: ?? -> code rate of associated PLP
		index+=3;
		//PLP_MOD 3bit: 011 -> modulation used by PLP  256-QAM
		index+=2; L1_post_signalling.set(index); P2.set(preSignalLength+index);
		index++;  L1_post_signalling.set(index); P2.set(preSignalLength+index);
		
		// PLP_ROTATION 1bit : 1 constellation rotation is used
		index++;  L1_post_signalling.set(index); P2.set(preSignalLength+index);
		// PLP_FEC_TYPE 2 bit: ?? -> FEC type used
		index+=2;
		
		//PLP_NUM_BLOCKS_MAX 10bit: ??? -> max value for PLP_NUM_BLOCKS
		index+=10;
		// FRAME_INTERVAL 8bit: 1 -> T2 frame interval of the current PLP for within the superframe. Appear in every frame
		index+=8; L1_post_signalling.set(index); P2.set(preSignalLength+index);
		
		/*
		 * TIME_IL_LENGTH and TIME_IL_TYPE shall only both be set to '0' when NUM_PLP is set to '1'. 
		 */
		//TIME-IL_LENGTH 8bit: 00000000 -> Time interleaving not used for current PLP
		index+=8;
		// TIME_IL_TYPE 1bit: 0 
		index++;
		
		/*
		 * No in-band A signalling and in-band B signalling
		 */
		// IN-BAND_A_FLAG 1bit : 0
		index++;
		// IN-BAND_B_FLAG : 0
		index++;
		
		// RESERVED_1 11bit ->future use. used for bias balancing
		index+=11;
		
		//PLP_MODE 2bit: 01 -> PLP mode is normal mode (NM)
		index+=2; L1_post_signalling.set(index); P2.set(preSignalLength+index);
		
		//STATIC_FLAG 1bit : 1  -> scheduling for the current PLP varies from T2 frame to T2 frame(0) or remain static (1)
		index++;
		//STATIC_PADDING_FLAG 1bit : 1  - > 
		index++; L1_post_signalling.set(index); P2.set(preSignalLength+index); 
		
		/*
		 * PLP looping is over 
		 */
		// FEF_LENGTH_MSB  2bit:  -> 2 MSB of FEF_LENGTH  for future use. but NOT for bias balancing
		index+=2;
		//RESERVED_2  30bit, for future use. Sometimes used for bias balancing
		index+=30;
		
		/*
		 * next two field are not used because NUM_AUX = 0000
		 */
		// AUX_STREAM_TYPE
		// AUX_PRIVATE_CONF
		
		/**
		 * Dynamic field
		 */
		// FRAME_IDX 8bit :  -> index of the current T2 frame within a super frame
		index+=8;
		// SUB_SLICE_INTERVAL 22 bit: 0 -> No type 2 PLP
		index+=22;
		//TYPE_2_START  22bit : 0  -> No type 2 PLP
		index+=22;
		//L1_CHANGE_COUNTER 8bit: 0 -> number of super frames ahead of config change has. 0 for no future change foreseen
		index+=8;
		//START_RF_IDX 3bit: 0 -> TFS not used
		index+=3;
		//RESERVED_1 8bit: for future use. sometimes used for bias balancing
		index+=8;
		
		/**
		 * PLP looping fields
		 */
		//PLP_ID 8bit: same as the order within the PLP loop in the L1-post configurable signalling
		index+=8;
		//PLP_START  22bit ???
		index+=22;
		//PLP_NUM_BLOCKS 10bit:  ??  -> number of FEC blocks
		index+=10;
		//RESERVED_2 8bit: for future use. sometimes used for bias balancing
		index+=8;
		/*
		 * end of PLP looping
		 */
		
		//RESERVED_3 8bit: for future use. sometimes used for bias balancing
		index+=8;
		
		/*
		 * next field is not applicable since NUM_AUX = 0;
		 */
		// AUX_PRIVATE_DYN
		
		/**
		 * L1 post extension field is not present since L1_POST_EXTENSION is set to 0
		 */
		
		/**
		 * CRC-32 for L1 post signaling
		 */
		index+=32;
		
		System.out.println("L1 post signalling Length: "+index);
		
		return L1_post_signalling;
	}
	
	public int getPreLength(){
		return 200;
	}
	
	public int getPostLength(){
		return 350;
	}
	
}
