package receiver;

import org.jscience.mathematics.number.Complex;

import edu.mit.streamjit.api.Filter;

public class CellDeinterleaver extends edu.mit.streamjit.api.Pipeline<Complex, Complex>{
	
	public CellDeinterleaver(){
		this.add(new Cell_Deinterleave());
	}
	
	private static class Cell_Deinterleave extends Filter<Complex, Complex> {
		
		public Cell_Deinterleave() {
			super(8100, 8100);
		}

		@Override
		public void work() {
			System.out.println("============ Cell De Interleaver ================");
			Complex[] cells = new Complex[8100];
			for (int i = 0; i < cells.length; i++) {
				cells[i] = pop();
			}
			Complex[] deinterleaved = do_cell_deinterleave(cells);
			for (int i = 0; i < deinterleaved.length; i++) {
				push(deinterleaved[i]);
			}			
		}
	}
	
	public static Complex[] do_cell_deinterleave(Complex[] D_out) {

		int N_Cells = 8100;
		
		int N_d = 13;
		int p = (int)java.lang.Math.pow(2,N_d);
		
		int S[][]= new int[p][N_d+1];
		for(int i=0;i<p;i++){
			S[i][0]=i;
		}
		
		for(int i=0;i<p;i++){
			S[i][1]=i%2;
		}

		S[2][N_d]=1;
		
		for(int i=3;i<p;i++){
			for(int j=2;j<N_d+1;j++){
				if(j==2){
					S[i][2]= S[i-1][N_d] ^ S[i-1][N_d-1] ^ S[i-1][N_d-4] ^ S[i-1][N_d-6] ;
				}
				else{
					S[i][j]=S[i-1][j-1];
				}
			}
		}
		
		int q=0;
		int[] L = new int[N_Cells];
		for(int i=0;i<p;i++){
			for(int j=N_d;j>0;j--){
				int d=13-j;
				L[q] += S[i][j]*(int)java.lang.Math.pow(2,d);
			}
		if(L[q]<N_Cells){
				q++;
			}
		else{
			L[q]=0;
		}
		
		}		
			
		 Complex Deint_out[] = new Complex[N_Cells];
		for(int i=0;i<N_Cells;i++){
			Deint_out[L[i]]=D_out[i];                   //Procedure of deinterleaving
		}
	
		return Deint_out;

	}

}

