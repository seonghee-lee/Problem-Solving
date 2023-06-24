package dp;

import java.util.*;

public class Main_S3_11727_2xn타일링2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N+1];
		dp[1] = 1;
		if(N == 1) {
			System.out.println(1);
			return;
		}
		
		dp[2] = 3;
		for(int i = 3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
		}
		System.out.print(dp[N]);
	}
}
