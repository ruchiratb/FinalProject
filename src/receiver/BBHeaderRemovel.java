package receiver;

import edu.mit.streamjit.api.Filter;
import edu.mit.streamjit.api.RoundrobinJoiner;
import edu.mit.streamjit.api.RoundrobinSplitter;
import edu.mit.streamjit.api.Splitjoin;
import receiver.FEC_Frame;

public class BBHeaderRemovel extends edu.mit.streamjit.api.Pipeline<FEC_Frame, FEC_Frame>{
	
	@SuppressWarnings("unchecked")
	public BBHeaderRemovel(){
//		this.add(new BitsFromFEC());
		this.add(
				new Splitjoin<FEC_Frame,FEC_Frame>(
							new RoundrobinSplitter<FEC_Frame>(1),
							new RoundrobinJoiner<FEC_Frame>(1),
							new BitsFromFEC(), new BitsFromFEC()
							
				)
		);
	}
	
	private static class BitsFromFEC extends Filter<FEC_Frame, FEC_Frame> {
		
		public BitsFromFEC() {
			super(1,1);  // 32208-80
		}

		@Override
		public void work() {
//			System.out.println("Remove BB header and push data only-------------");
			FEC_Frame current_frame = pop();
			boolean[] datawithbb = current_frame.getFEC_Data();
			boolean[] data = new boolean[64720];
			
			
			for (int i = 0; i < data.length; i++) {
				data[i] = datawithbb[i+80];
			}
			
			FEC_Frame frame = new FEC_Frame(data);
			push(frame);
		}
	}
}
