package dp;

import java.util.*;

public class Main_S4_2839_설탕배달 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int INF = 10000;
		int[] dp = new int[5001];
		dp[0] = 0;
		dp[1] = INF;	dp[2] = INF;
		dp[3] = 1;
		dp[4] = INF;
		dp[5] = 1;
		
		for(int i = 6; i<=N; i++) {
			dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
		}
		
		if(dp[N] >= INF)	System.out.println(-1);
		else System.out.println(dp[N]);
	}

}
