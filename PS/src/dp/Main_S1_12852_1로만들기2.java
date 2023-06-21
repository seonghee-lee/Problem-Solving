package dp;

import java.util.*;
import java.io.*;

public class Main_S1_12852_1로만들기2 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N+1];
		int[] memo = new int[N+1];
		
		dp[1] = 0;
		memo[1] = 0;
		
		for(int i = 2; i<=N; i++) {
			int min = dp[i-1] + 1;
			int memoMin = i - 1;
			
			if(i % 3 == 0) {
				if(dp[i/3] + 1 <= min) memoMin = i / 3;
				min = Math.min(min, dp[i/3] + 1);
			} 
			
			if(i % 2 == 0) {
				if(dp[i/2] + 1 <= min) memoMin = i / 2;
				min = Math.min(min, dp[i/2] + 1);
			}
			
			dp[i] = min;
			memo[i] = memoMin;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dp[N]).append("\n").append(N).append(" ");
		int idx = N;
		while(true) {
			int cur = memo[idx];
			if(cur == 0) break;
			idx = cur;
			sb.append(cur).append(" ");
			if(cur <= 1) {
				break;
			}
		}
		
		System.out.print(sb);
	}

}
