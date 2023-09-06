package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_1695_팰린드롬만들기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		int[] reverse = new int[N+1];
		
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			reverse[N-i+1] = arr[i];
		}
		
		int[][] dp = new int[N+1][N+1];		//dp[i][j]: arr(0:i]와 reverse(0:j]의 최장공통수열의 길이
		
		for(int i = 1; i<=N; i++) {
			int cur = reverse[i];
			for(int j = 1; j<=N; j++) {
				if(cur != arr[j]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				} else {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
			}
		}
		
		int lcsLength = dp[N][N];
		
		System.out.println(N - lcsLength);
		
	}
}
