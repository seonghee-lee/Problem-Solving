package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_10819_차이를최대로 {
	static int N, max;
	static int[] inputs, result;
	static boolean[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new int[N];
		result = new int[N];
		selected = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		perm(0);
		System.out.println(max);
		
	}
	private static void perm(int cnt) {
		if(cnt == N) {

			int calc = 0;
			for(int i = 0; i<N-1; i++) {
				calc += Math.abs(result[i] - result[i+1]);
			}
			
			max = Math.max(max, calc);
			
			return;
		}
		for(int i = 0; i<N; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			result[cnt] = inputs[i];
			perm(cnt+1);
			selected[i] = false;
		}
	}

}
