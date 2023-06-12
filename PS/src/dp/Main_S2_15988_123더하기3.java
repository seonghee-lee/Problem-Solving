package dp;

import java.util.*;
import java.io.*;
/*
 * 오버플로우!!!!
 * 시간초과!!!!
 * */
public class Main_S2_15988_123더하기3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int divNum = 1000000009;
		
		long[] dp = new long[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i = 4; i<1000001; i++) {
			dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % divNum;
		}
		
		StringBuilder sb = new StringBuilder(1000001);
		
		for(int t = 1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		}
		
		System.out.println(sb);
	}

}
