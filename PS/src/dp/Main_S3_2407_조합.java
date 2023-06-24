package dp;

import java.math.BigInteger;
import java.util.*;

public class Main_S3_2407_조합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		BigInteger[][] dp = new BigInteger[n+1][n+1];
		for(int i = 1; i<=n; i++) {
			dp[i][0] = new BigInteger("1");
			dp[i][i] = new BigInteger("1");
		}
		
		if(n == m) {
			System.out.print(1);
			return;
		}
		
		for(int i = 2; i<=n; i++) {
			for(int j = 1; j<i; j++) {
				dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
			}
		}
		System.out.print(dp[n][m]);
	}
}
