package dp;

import java.util.*;
import java.io.*;

public class Main_S1_11660_구간합구하기5 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		int[][] arr = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			dp[1][i] = dp[1][i-1] + arr[1][i];
			dp[i][1] = dp[i-1][1] + arr[i][1];
		}
		
		for(int i = 2; i<=N; i++) {
			for(int j = 2; j<=N; j++) {
				dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + arr[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder(200000);
		for(int m = 1; m<=M; m++) {
			String[] inputs = br.readLine().split(" ");
			int x1 = Integer.parseInt(inputs[0]);
			int y1 = Integer.parseInt(inputs[1]);
			int x2 = Integer.parseInt(inputs[2]);
			int y2 = Integer.parseInt(inputs[3]);
			
			int ans = -1;
			if(x1 == x2 && y1 == y2) {
				ans = arr[x1][y1];
			} else if(x1 == 1 && y1 == 1 && x2 == N && y2 == N) {
				ans = dp[N][N];
			}
			else{
				ans = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
			}
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
	}

}
