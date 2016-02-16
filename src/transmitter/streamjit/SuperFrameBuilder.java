package transmitter.streamjit;

import edu.mit.streamjit.api.Filter;

public class SuperFrameBuilder extends edu.mit.streamjit.api.Pipeline<T2_Frame, Super_Frame>{
	
	public SuperFrameBuilder(){
		this.add(new Super_Builder());
	}
	
	private static class Super_Builder extends Filter<T2_Frame, Super_Frame> {
		
		public Super_Builder() {
			super(2, 1);
		}

		@Override
		public void work() {
//			System.out.println("============= Super Frame Builder =============");
			T2_Frame[] t2frames = new T2_Frame[2];	
			t2frames[0] = pop();
			t2frames[1] = pop();
			Super_Frame superframe = new Super_Frame(t2frames[0], t2frames[1]);
			push(superframe);
		}
	}

}
