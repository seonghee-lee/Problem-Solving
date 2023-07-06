package dp;

import java.util.*;
import java.io.*;

public class Main_S1_1149_RGB거리_다시 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			String[] inputs = br.readLine().split(" ");
			for(int j = 1; j<=3; j++) {
				arr[i][j] = Integer.parseInt(inputs[j-1]);
			}
		}
		
		int[][] dp = new int[N+1][N+1];		//i번 집을 j색으로 칠할 때 비용합의 최솟값
		dp[1][1] = arr[1][1];
		dp[1][2] = arr[1][2];
		dp[1][3] = arr[1][3];
		
		for(int i = 2; i<=N; i++) {
			dp[i][1] = arr[i][1] + Math.min(dp[i-1][2], dp[i-1][3]);
			dp[i][2] = arr[i][2] + Math.min(dp[i-1][1], dp[i-1][3]);
			dp[i][3] = arr[i][3] + Math.min(dp[i-1][1], dp[i-1][2]);
		}
		
		int min = Math.min(dp[N][1], Math.min(dp[N][2], dp[N][3]));
		System.out.print(min);
	}
}
