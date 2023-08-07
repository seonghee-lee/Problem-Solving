package dp2;

import java.util.*;

public class Main_G5_2225_합분해 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] dp = new int[K+1][N+1];	//dp[i][j]: 숫자 i개를 써서 j를 만드는 경우의 수
		for(int i = 0; i<=K; i++) {
			dp[i][0] = 1;
		}
		
		int MOD = 1000000000;
		
		for(int i = 1; i<=K; i++) {
			for(int j = 1; j<=N; j++) {
				dp[i][j] = dp[i-1][j] % MOD + dp[i][j-1] % MOD;
			}
		}
		
		System.out.print(dp[K][N] % MOD);
	}

}
