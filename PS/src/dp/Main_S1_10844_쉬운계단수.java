package dp;

import java.util.*;

public class Main_S1_10844_쉬운계단수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long mod = 1000000000;
		
		long[][] dp = new long[N+1][10];
		for(int i = 1; i<=9; i++) {
			dp[1][i] = 1;
		}
		
		if(N > 1) {
			for(int i = 2; i<=N; i++) {
				for(int j = 0; j<=9; j++) {
					if(j == 0) {
						dp[i][j] = dp[i-1][1] % mod;
					} else if(j == 9) {
						dp[i][j] = dp[i-1][8] % mod;
					} else {
						dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
					}
				}
			}
		}
		
		long ans = 0;
		for(int i = 0; i<=9; i++) {
			ans  = (ans + dp[N][i]) % mod;
		}
		
		System.out.print(ans % mod);
		
	}

}
