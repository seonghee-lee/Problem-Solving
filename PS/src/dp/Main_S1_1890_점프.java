package dp;

import java.util.*;
import java.io.*;

public class Main_S1_1890_점프 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				
				if(i == N-1 && j == N-1) continue;
				
				int dist = arr[i][j];
				
				int rightC = j + dist;
				if(rightC < N) {
					dp[i][rightC] += dp[i][j];
				}
				
				int downR = i + dist;
				if(downR < N) {
					dp[downR][j] += dp[i][j];
				}
			}
		}
		
		System.out.print(dp[N-1][N-1]);
	}

}
