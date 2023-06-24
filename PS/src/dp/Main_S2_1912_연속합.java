package dp;

import java.util.*;
import java.io.*;

public class Main_S2_1912_연속합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		dp[0] = 0;
		for(int i = 1; i<=N; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
	}

}
