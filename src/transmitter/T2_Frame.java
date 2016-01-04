package transmitter;

import java.util.BitSet;

import org.jscience.mathematics.number.Complex;

public class T2_Frame {

	Complex[] data;
	public T2_Frame(Complex[] frame_symbols){
		this.data = frame_symbols;
	}	
	
/*	public  Complex[] getDataSymbols(){
		double[] real = {5, 1.4, -3, 7.9, -4, 3.3, -5, 8.4};
		double[] img  = {5, 1.7, 2, -5.7, 7, -3.3, 2, -5.4};
		P2_Symbol p2 = new P2_Symbol();
		datasymbols_per_T2Frame  = p2.NUM_DATA_SYMBOLS;

		Complex [] cells = new Complex[datasymbols_per_T2Frame];
		for (int i = 0; i < datasymbols_per_T2Frame; i++) {
			cells[i] = Complex.valueOf(real[i], img[i]);
		}
		
		return cells;
	}*/
	
/*	public int getDataSymbolsPerT2Frame(){
		P2_Symbol p2 = new P2_Symbol();
		return p2.NUM_DATA_SYMBOLS;
	}
	
	public int geP2SymbolLength(){
		P2_Symbol p2 = new P2_Symbol();
		return p2.getPreLength()+p2.getPostLength();
	}*/
	
/*	public static BitSet getp2symbol() {
		BitSet p2_pre = new BitSet();
		BitSet p2_post = new BitSet();
		int preLength, postLength, p2_Symbol_Length;
		int p1_symbol_length = 7;
		P2_Symbol p2 = new P2_Symbol();
		p2_pre = p2.getL1PreSignal();
		p2_post = p2.getL1PostSymbol();
		preLength = p2.getPreLength();
		postLength = p2.getPostLength();
		p2_Symbol_Length = preLength + postLength;
		System.out.println(p2_Symbol_Length);
		
		p1Insertion();
		p2Insertion(p1_symbol_length, preLength, postLength, p2_pre, p2_post);
		
		System.out.println(p2_pre);
		System.out.println(p2_post);
//		System.out.println(p2symbol);
		
		datasymbols_per_T2Frame  = p2.NUM_DATA_SYMBOLS;
//		System.out.println(datasymbols_per_T2Frame);		
		
		return p2symbol;
	}*/
	
/*	private static void p2Insertion(int p1Length, int preLength, int postLength, BitSet pre, BitSet post){
		for (int i = 0; i < preLength; i++) {
			if (pre.get(i)) {
				p2symbol.set(p1Length+i);
			}
		}
		
		for (int i = 0; i < postLength; i++) {
			if (post.get(i)) {
				p2symbol.set((i+preLength+p1Length));
			}
		}
	}*/

}
