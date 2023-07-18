package dp;

import java.util.*;
import java.io.*;

public class Main_S1_12026_BOJ거리 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		
		char[] inputs = br.readLine().toCharArray();
		for(int i = 1; i<=N; i++) {
			if(inputs[i-1] == 'B') arr[i] = 0;
			else if(inputs[i-1] == 'O') arr[i] = 1;
			else arr[i] = 2;
		}
		
		boolean[] b = new boolean[N+1];
		boolean[] o = new boolean[N+1];
		boolean[] j = new boolean[N+1];
		
		int[] dp = new int[N+1];		//dp[i] : i까지 오는데 필요한 에너지 양의 최솟값
		int INF = 20000000;
		Arrays.fill(dp, INF);
		dp[1] = 0;
		b[1] = true;
		
		for(int i = 2; i<=N; i++) {
			int cur = arr[i];
			if(cur == 0) {	//b
				b[i] = true;
				for(int k = 1; k<=i; k++) {
					if(j[k]) {
						int energy = (i - k) * (i - k);
						int val = dp[k] + energy;
						dp[i] = Math.min(dp[i], val);
					}
				}
			}
			else if(cur == 1) {	//o
				o[i] = true;
				for(int k = 1; k<=i; k++) {
					if(b[k]) {
						int energy = (i - k) * (i - k);
						int val = dp[k] + energy;
						dp[i] = Math.min(dp[i], val);
					}
				}
			}
			else {		//j
				j[i] = true;
				for(int k = 1; k<=i; k++) {
					if(o[k]) {
						int energy = (i - k) * (i - k);
						int val = dp[k] + energy;
						dp[i] = Math.min(dp[i], val);
					}
				}
			}
		}
		
		if(dp[N] == INF) System.out.print(-1);
		else {
			System.out.print(dp[N]);
		}
	}
}
