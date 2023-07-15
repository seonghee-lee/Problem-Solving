package dp;

import java.util.*;

public class Main_S1_1309_동물원 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[][] dp = new int[N+1][3];
		dp[1][0] = 1; dp[1][1] = 1;	dp[1][2] = 1;
		
		if(N >= 2) {
			for(int i = 2; i<=N; i++) {
				dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
				dp[i][1] = (dp[i-1][0] + dp[i-1][2]) %9901;
				dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
			}
		}
		
		int ans = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
		System.out.print(ans);
	}

}
