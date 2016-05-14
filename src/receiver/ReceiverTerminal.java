package receiver;

import edu.mit.streamjit.api.Pipeline;
import transmitter.streamjit.T2_Frame;

public class ReceiverTerminal extends edu.mit.streamjit.api.Pipeline<T2_Frame, Byte>{
	
	public ReceiverTerminal(){
		this.add(new ReceiverKernel());
	}
	
	public static final class ReceiverKernel extends Pipeline<Byte, Byte> {
		
		public ReceiverKernel() {
			this.add(
					new FFT()
					,new CellDeinterleaver()
					,new Constellation_Derotation()
					,new De_Normalizer()
					,new Maximum_Likelyhood_Mapper()
					,new Constellation_De_mapper()
					,new Multiplexer()					
					,new Column_De_Twister()
					,new Bit_DeInterleaver()
					,new DeScrambbler()
					,new BBHeaderRemovel()
					,new PushDataBits() 
					,new BitsToBytes()
					,new ByteToSource()
			);
		}		
	}
		

}

