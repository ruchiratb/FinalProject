package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Pipeline;

public class ReceiverTerminal_2 extends edu.mit.streamjit.api.Pipeline<Complex, Byte>{
	
	public ReceiverTerminal_2(){
		this.add(new ReceiverKernel());
	}
	
	public static final class ReceiverKernel extends Pipeline<Complex, Byte> {
		
		public ReceiverKernel() {
			this.add(
					new FFT()
					,new Constellation_Derotation()
					,new De_Normalizer()
					,new Maximum_Likelyhood_Mapper()
					,new Constellation_De_mapper()
//					,new Multiplexer()
//					,new FEC_Frame_Builder()
//					,new Bit_DeInterleaver()
//					,new LDPC_Decoder()
//					,new BBHeaderRemovel()
//					,new DataWriter()
			);
		}		
	}
		

}

