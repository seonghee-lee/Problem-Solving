package dp;

import java.util.*;

public class Main_S5_9655_돌게임 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N+1];
		dp[0] = 0;
		dp[1] = 1;
		if(N == 1) {
			System.out.println("SK");
			return;
		}
		
		if(N >= 2) {
			dp[2] = 2;
			if(N >= 3) {
				dp[3] = 1;
				for(int i = 4; i<=N; i++) {
					dp[i] = Math.min(dp[i-1], dp[i-3]) + 1;
				}
				if(dp[N] % 2 == 0) System.out.println("CY");
				else System.out.println("SK");
			} else {
				System.out.println("CY");
				return;
			}
		}
	}
}
