package dp;

import java.util.*;
import java.io.*;

public class Main_G5_2294_동전2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		
		int[] arr = new int[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[K+1];
		int INF = 20000;
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i = 1; i<=K; i++) {
			for(int j = 1; j<=N; j++) {
				int curCoin = arr[j];
				
				if(i - curCoin < 0) continue;
				
				dp[i] = Math.min(dp[i], 1 + dp[i - curCoin]);
			}
		}
		
		if(dp[K] == INF) System.out.print(-1);
		else System.out.print(dp[K]);
	}

}
