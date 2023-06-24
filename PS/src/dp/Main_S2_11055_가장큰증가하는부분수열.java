package dp;

import java.util.*;
import java.io.*;

public class Main_S2_11055_가장큰증가하는부분수열 {

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
			int max = 0;
			for(int j = i-1; j>=1; j--) {
				if(arr[i] <= arr[j]) continue;
				max = Math.max(max, dp[j]);
			}
			dp[i] = max + arr[i];
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.print(ans);
	}

}
