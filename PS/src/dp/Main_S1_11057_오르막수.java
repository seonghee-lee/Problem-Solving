package dp;

import java.util.*;

public class Main_S1_11057_오르막수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	//길이가 N인 오르막 수
		
		int[] dp = new int[11];
		
		if(N == 1) {
			System.out.print(10);
			return;
		}
		
		for(int i = 1; i<=10; i++) {
			dp[i] = dp[i-1] + i;
		}
		
		if(N >= 3) {
			int count = N;
			while(count-- > 2) {
				for(int i = 1; i<=10; i++) {
					dp[i] = (dp[i-1] % 10007) + (dp[i] % 10007);
				}
			}
		}
		System.out.print(dp[10] % 10007);
	}
}
