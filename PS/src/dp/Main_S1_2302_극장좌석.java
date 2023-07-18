package dp;

import java.util.*;
import java.io.*;

public class Main_S1_2302_극장좌석 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] vip = new boolean[N+1];
		for(int i = 1; i<=M; i++) {
			int n = Integer.parseInt(br.readLine());
			vip[n] = true;
		}
		
		int[] dp = new int[N+2];
		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i<=N; i++) {
			dp[i] = dp[i-2] + dp[i-1];
		}
		
		int ans = 1;
		int count = 0;
		for(int i = 1; i<=N; i++) {
			if(vip[i]) {
				ans *= dp[count];
				count = 0;
			} else {
				count++;
			}
		}
		
		ans *= dp[count];
		
		System.out.print(ans);
	}

}
