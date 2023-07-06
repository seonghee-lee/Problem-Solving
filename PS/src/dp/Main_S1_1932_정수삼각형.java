package dp;

import java.util.*;
import java.io.*;

public class Main_S1_1932_정수삼각형 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][N+1];
		dp[1][1] = arr[1][1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				dp[i][j] = arr[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			if(dp[N][i] > max) max = dp[N][i];
		}
		
		System.out.print(max);
	}

}
