package dp;

import java.util.*;

public class Main_S3_17626_FourSquares {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N+1];
		dp[0] = 0;
		
		for(int i = 1; i<=N; i++) {
			dp[i] = dp[i-1] + 1;
			int root = (int)Math.sqrt(i);
			for(int j = root; j > 0; j--) {
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
			}
		}
		System.out.print(dp[N]);
	}
}
