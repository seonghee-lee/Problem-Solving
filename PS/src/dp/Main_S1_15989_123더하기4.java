package dp;

import java.util.*;
import java.io.*;
/*
 * [2차원 DP 문제]
 * dp[i][j] ==> i:임의의 정수, j: 수식이 j로 끝남
 * 중복을 지원하지 않는다 ==> 수식이 오름차순 정렬되어 있어야 한다.
 * 오름차순을 만들어야하기 때문에 2차원 배열을 사용하는 것!! 끝나는 숫자가 뭔 지 알아야 하니까.
 * */
public class Main_S1_15989_123더하기4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[10001][4];
		dp[1][1] = 1;
		dp[1][2] = 0;
		dp[1][3] = 0;
		
		dp[2][1] = 1;
		dp[2][2] = 1;
		
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;
		
		for(int i = 4; i<=10000; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];	//마지막에 2가 더해지기 때문에 i-2를 쓰고, 2또는 1로 끝나야한다.
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		StringBuilder sb = new StringBuilder(10001);
		for(int t = 1; t<=T; t++) {
			int n = Integer.parseInt(br.readLine());
			int ans = dp[n][1] + dp[n][2] + dp[n][3];
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
