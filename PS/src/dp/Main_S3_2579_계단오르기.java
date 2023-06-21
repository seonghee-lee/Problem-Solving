package dp;

import java.util.*;
import java.io.*;
/*
 * dp[i][j] : j개의 계단을 연속해서 올랐을 때 i번째 계단에서 가질 수 있는 최대 점수
 *            단, i번째 계단은 반드시 밟아야한다.
 * j는 1 또는 2만 가능함 (연속해서 3개 계단을 못 오르니까)
 * dp[k][1] = max(dp[k-2][1], dp[k-2][2]) + S[k]
 * dp[k][2] = dp[k-1][1] + S[k]
 * 
 * 초기화
 * dp[1][1] = S[1] , dp[1][2] = 0
 * dp[2][1] = S[2] , dp[2][2] = S[1]+S[2]
 * */
public class Main_S3_2579_계단오르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N+1];
		for(int i = 1; i<=N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		if(N == 1) {
			System.out.println(stairs[1]);
			return;
		}
		
		int[][] dp = new int[N+1][3];
		dp[1][1] = stairs[1];	dp[1][2] = 0;
		dp[2][1] = stairs[2];	dp[2][2] = stairs[1] + stairs[2];
		
		for(int i = 3; i<=N; i++) {
			dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
			dp[i][2] = dp[i-1][1] + stairs[i];
		}
		
		int ans = Math.max(dp[N][1], dp[N][2]);
		System.out.print(ans);
	}
}
