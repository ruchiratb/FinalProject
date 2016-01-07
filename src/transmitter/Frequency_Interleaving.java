package transmitter;

public class Frequency_Interleaving {
	
		public static void main(String[] args) {
			int N_Data = 8100;
			int M_max = 32768;
			int N_r = 15;    //log2M
			int l=0;          // Number of the symbol of the frame
			int In[] = new int[N_Data];
			for(int i=0;i<N_Data;i++){
				In[i]=i;
			}
			
			int S[][]= new int[M_max][N_r];
			for(int i=0;i<M_max;i++){
				S[i][0]=i;
			}
					
			S[2][N_r-1]=1;
			
			for(int i=3;i<M_max;i++){
				for(int j=1;j<N_r;j++){
					if(j==1){
						S[i][1]= S[i-1][N_r-1] ^ S[i-1][N_r-2] ^ S[i-1][N_r-3] ^ S[i-1][N_r-13] ;
					}
					else{
						S[i][j]=S[i-1][j-1];
					}
				}
			}
			int S_1[][]= new int[M_max][N_r];
			for(int i=0;i<M_max;i++){
				for(int j=0;j<N_r;j++){
					if(j==0){
						S_1[i][0] =S[i][0];
					}
					else if(j==1){
						S_1[i][1] =S[i][8];
					}
					else if(j==2){
						S_1[i][2] =S[i][9];
					}
					else if(j==3){
						S_1[i][3] =S[i][14];
					}
					else if(j==4){
						S_1[i][4] =S[i][4];
					}
					else if(j==5){
						S_1[i][5] =S[i][6];
					}
					else if(j==6){
						S_1[i][6] =S[i][13];
					}
					else if(j==7){
						S_1[i][7] =S[i][3];
					}
					else if(j==8){
						S_1[i][8] =S[i][2];
					}
					else if(j==9){
						S_1[i][9] =S[i][12];
					}
					else if(j==10){
						S_1[i][10] =S[i][5];
					}
					else if(j==11){
						S_1[i][11] =S[i][10];
					}
					else if(j==12){
						S_1[i][12] =S[i][11];
					}
					else if(j==13){
						S_1[i][13] =S[i][1];
					}
					else{
						S_1[i][14] =S[i][7];
					}
					
				}
			}
			
			
			
			int p=0;
			int[] H = new int[N_Data+1];
			for(int i=0;i<M_max;i++){
				for(int j=N_r-1;j>0;j--){
					int d=14-j;
					H[p] += S_1[i][j]*(int)java.lang.Math.pow(2,d);
				}
				H[p] += (i%2)*(int)java.lang.Math.pow(2,N_r-1);
			if(H[p]<N_Data){
					p++;
				}
			else{
				H[p]=0;
			}
			
			}
			
			
			
			
			
			int[] D_out = new int[N_Data];  
			
			if(l%2==0){
				for(int i=0;i<N_Data;i++){         
					D_out[H[i]]=In[i];                         //Procedure of interleaving
			}
			}
			
			else{
				for(int i=0;i<N_Data;i++){         
					D_out[i]=In[H[i]];                         //Procedure of interleaving
				}
			}
			
			
			int[] Deint_out = new int[N_Data];     //Parameters for deinterleave
			
			if(l%2==0){
			for(int i=0;i<N_Data;i++){
				Deint_out[i]=D_out[H[i]];                   //Procedure of deinterleaving
			}
			}
			
			else{
				for(int i=0;i<N_Data;i++){
					Deint_out[H[i]]=D_out[i];                   //Procedure of deinterleaving
				}	
			}
			
			
			for(int i=1000;i<1100;i++){
				System.out.print(In[i]+"   ");
				System.out.print(H[i]+"   ");
				System.out.print(D_out[i]+"   ");
				System.out.println(Deint_out[i]);
			}
			// TODO Auto-generated method stub

		}

	}


