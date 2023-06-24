package dp;

import java.util.*;
import java.io.*;

public class Main_S2_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		dp[1] = 1;
		for(int i = 2; i<=N; i++) {
			//현재인덱스: i
			int max = 1;
			for(int j = i-1; j>=1; j--) {
				if(arr[j] >= arr[i]) continue;
				max = Math.max(max, dp[j] + 1);
			}
			dp[i] = max;
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.print(ans);
	}
}
