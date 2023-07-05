package dp;

import java.util.*;
import java.io.*;

public class Main_S1_21317_징검다리건너기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][2];
		
		for(int i = 1; i<N; i++) {
			String[] inputs = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(inputs[0]);
			arr[i][1] = Integer.parseInt(inputs[1]);
		}
		
		int K = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[21][2];
		int INF = 1000000;
		
		if(N == 1) {
			System.out.print(0);
			return;
		}
		
		dp[2][0] = arr[1][0];	dp[2][1] = INF;
		dp[3][0] = Math.min(dp[2][0] + arr[2][0], dp[1][0] + arr[1][1]);	
		dp[3][1] = INF;
		
		for(int i = 4; i<=N; i++) {
			dp[i][0] = Math.min(dp[i-1][0] + arr[i-1][0], dp[i-2][0] + arr[i-2][1]);
			
			int temp1 = dp[i-3][0] + K;
			int temp2 = Math.min(dp[i-1][1] + arr[i-1][0], dp[i-2][1] + arr[i-2][1]);
			dp[i][1] = Math.min(temp1, temp2);
		}
		
		System.out.print(Math.min(dp[N][0], dp[N][1]));
	}
}
