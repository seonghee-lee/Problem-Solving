package dp;

import java.io.*;

public class Main_S5_1010_다리놓기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			String[] inputs = br.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int M = Integer.parseInt(inputs[1]);
			
			int[][] dp = new int[M+1][M+1];
			for(int i = 1; i<=M; i++) {
				dp[i][0] = 1;
				dp[i][i] = 1;
			}
			
			for(int i = 2; i<=M; i++) {
				for(int j = 1; j<i; j++) {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
			sb.append(dp[M][N]).append("\n");
		}
		System.out.print(sb);
	}

}
