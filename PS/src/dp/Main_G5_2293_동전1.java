package dp;

import java.util.*;
import java.io.*;

public class Main_G5_2293_동전1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		int[] value = new int[N+1];
		for(int i = 1; i<=N; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[N+1][K+1];	//dp[i][j] : ~i 동전을 사용해서 j 금액을 만드는 경우의 수
		
		for(int i = 1; i<=K; i++) {
			int cur = value[1];
			if(i % cur == 0) dp[1][i] = 1;
		}
		
		for(int i = 2; i<= N; i++) {
			int cur = value[i];
			for(int j = 1; j<=K; j++) {
				if(cur > j) {	//cur 동전을 사용하지 못한다.
					dp[i][j] = dp[i-1][j];
				} else if(cur == j) {
					dp[i][j] = dp[i-1][j] + dp[i][j-cur] + 1;
				} else {
					dp[i][j] = dp[i-1][j] + dp[i][j-cur];
				}
			}
		}
		
//		for(int i = 0; i<=N; i++) {
//			for(int j = 0; j<=K; j++) {
//				System.out.printf("%d ", dp[i][j]);
//			}System.out.println();
//		}
		
		System.out.print(dp[N][K]);
	}

}
