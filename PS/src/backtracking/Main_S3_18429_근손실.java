package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_18429_근손실 {
	static int N, K, count;
	static int[] A, numbers;
	static boolean[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		numbers = new int[N];
		selected = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		System.out.println(count);
	}
	private static void perm(int cnt) {
		if(cnt == N) {
			
			int total = 500;
			for(int i = 0; i<N; i++) {
				total += numbers[i];
				total -= K;
				if(total < 500) return;
			}
			count++;
			return;
		}
		
		for(int i = 0 ; i<N; i++) {
			if(selected[i]) continue;
			numbers[cnt] = A[i];
			selected[i] = true;
			perm(cnt+1);
			selected[i] = false;
		}
	}

}
