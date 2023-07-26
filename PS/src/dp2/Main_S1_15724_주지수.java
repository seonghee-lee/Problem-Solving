package dp2;

import java.util.*;
import java.io.*;

public class Main_S1_15724_주지수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		int[][] arr = new int[N+1][M+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][M+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				dp[i][j] = dp[i][j-1] + arr[i][j];
			}
		}
		
		for(int j = 1; j<=M; j++) {
			for(int i = 1; i<=N; i++) {
				dp[i][j] += dp[i-1][j];
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<K; i++) {
			String[] inputs = br.readLine().split(" ");
			int x1 = Integer.parseInt(inputs[0]);
			int y1 = Integer.parseInt(inputs[1]);
			int x2 = Integer.parseInt(inputs[2]);
			int y2 = Integer.parseInt(inputs[3]);
			
			int sum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
			sb.append(sum).append("\n");
		}
		
		System.out.print(sb);
	}

}
