package dp;

import java.util.*;
import java.io.*;

public class Main_S1_22869_징검다리건너기small {
	static int N, K;
	static int[][] dp;
	static boolean possible;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		
		int[] stone = new int[N+1];
		String[] inputs = br.readLine().split(" ");
		for(int i = 1; i<=N; i++) {
			stone[i] = Integer.parseInt(inputs[i-1]);
		}
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, -1);
		
		dp[1] = 0;
		for(int i = 1; i<=N; i++) {
			if(dp[i] == -1) continue;
			
			if(dp[N] != -1 && dp[N] <= K) {
				possible = true;
				break;
			}
			
			for(int j = i+1; j<=N; j++) {
				int p = (j-i) * (1 + Math.abs(stone[i] - stone[j]));
				if(p <= K) {
					dp[j] = p;
				}
			}
		}
		
		if(possible) System.out.print("YES");
		else System.out.print("NO");
	}
}
