package dp;

import java.util.*;
import java.io.*;

public class Main_S1_9465_스티커 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[3][N+1];
			
			for(int i = 1; i<=2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j<=N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[3][N+2];
			for(int i = 1; i<=2; i++) {
				for(int j = 2; j<=N+1; j++) {
					dp[i][j] = arr[i][j-1];
				}
			}
			
			for(int i = 2; i<=N+1; i++) {
				for(int j = 1; j<=2; j++) {
					if(j == 1) {
						dp[j][i] += Math.max(dp[j+1][i-1], dp[j+1][i-2]);
					} else {
						dp[j][i] += Math.max(dp[j-1][i-1], dp[j-1][i-2]);
					}
				}
			}
			
			int ans = Math.max(dp[1][N+1], dp[2][N+1]);
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}

}
