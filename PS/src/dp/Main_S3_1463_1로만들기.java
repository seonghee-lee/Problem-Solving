package dp;

import java.util.*;

public class Main_S3_1463_1로만들기 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int[] dp = new int[X+1];
		
		dp[1] = 0;
		for(int i = 2; i<=X; i++) {
			int a = i % 3 == 0 ? dp[i/3] + 1 : Integer.MAX_VALUE;
			int b = i % 2 == 0 ? dp[i/2] + 1 : Integer.MAX_VALUE;
			int c = dp[i-1] + 1;
			dp[i] = Math.min(a, Math.min(b,  c));
		}
		System.out.print(dp[X]);
	}

}
