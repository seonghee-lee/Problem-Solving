package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_6603_로또 {
	static int N;
	static int[] inputs, result;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			inputs = new int[N];
			result = new int[N];
			
			for(int i = 0; i<N; i++) {
				inputs[i] = Integer.parseInt(st.nextToken());
			}
			
			sb = new StringBuilder(100);
			
			combination(0, 0);
			System.out.println(sb);
		}
	}

	private static void combination(int cnt, int start) {
		if(cnt == 6) {
			for(int i = 0; i<6; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start; i<N; i++) {
			result[cnt] = inputs[i];
			combination(cnt+1, i+1);
		}
	}

}
