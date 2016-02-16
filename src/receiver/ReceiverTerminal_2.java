package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Pipeline;
import transmitter.FEC_Frame;

public class ReceiverTerminal_2 extends edu.mit.streamjit.api.Pipeline<FEC_Frame, Byte>{
	
	public ReceiverTerminal_2(){
		this.add(new ReceiverKernel());
	}
	
	public static final class ReceiverKernel extends Pipeline<FEC_Frame, Byte> {
		
		public ReceiverKernel() {
			this.add(
//					new FFT()
//					,new Constellation_Derotation()
//					,new De_Normalizer()
//					,new Maximum_Likelyhood_Mapper()
//					,new Constellation_De_mapper()
//					,new FEC_Builder()
//					,new FEC_Frame_Builder()
////					,new Bit_DeInterleaver()
//					,new LDPC_Decoder()
//					,new BBHeaderRemovel()
//					,new PushDataBits()  
//					,new BitsToBytes()
//					,new ByteToSource()
					
//					new FFT()
//					new Pop_Push()
//					new Constellation_Derotation()
//					,new De_Normalizer()
//					,new Maximum_Likelyhood_Mapper()
					new Constellation_De_mapper()
					,new Multiplexer()
//					,new FEC_Builder()   no need now
//					,new FEC_Frame_Builder()   no need now
					,new Column_De_Twister()
					,new Bit_DeInterleaver()
//					,new LDPC_Decoder()
					,new DeScrambbler()
					,new BBHeaderRemovel()
					,new PushDataBits()  
					,new BitsToBytes()
//					,new ByteToSource()
			);
		}		
	}
		

}

