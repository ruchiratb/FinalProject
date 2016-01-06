package receiver;

import transmitter.FEC_Frame;

public class FEC_Frame_Builder extends edu.mit.streamjit.api.Pipeline<Byte, FEC_Frame> {

	public FEC_Frame_Builder(){
		this.add(new Build_FEC());
	}
	
	private static class Build_FEC extends edu.mit.streamjit.api.Filter<Byte, FEC_Frame> {
		
		public Build_FEC() {
			super(64800, 1);
		}

		@Override
		public void work() {
			System.out.println("fec frame--------------------");
			boolean[] fec_data = new boolean[64800];
			for (int i = 0; i < fec_data.length; i++) {
				byte temp = pop();
				if (temp == 1) {
					fec_data[i] = true;
				}else {
					fec_data[i] = false;
				}				
			}
			FEC_Frame frame = new FEC_Frame(fec_data);
			push(frame);
		}
	}
}
