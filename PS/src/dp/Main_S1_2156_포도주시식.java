package dp;

import java.util.*;
import java.io.*;

public class Main_S1_2156_포도주시식 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N+1];
		dp[1] = arr[1];
		if(N == 1) {
			System.out.print(dp[1]);
			return;
		}
		
		dp[2] = arr[1] + arr[2];
		int max = dp[2];
		
		for(int i = 3; i<=N; i++) {
			//이전 잔을 마심
			int a = arr[i] + arr[i-1] + dp[i-3];
			
			//이전 잔을 안 마심
			int b = arr[i] + dp[i-2];
			
			int abMax = Math.max(a, b);
			
			//현재 잔을 안 마심
			int c = dp[i-1];
			
			max = Math.max(max, Math.max(c, abMax));
			dp[i] = max;
		}
		
		System.out.print(dp[N]);
		
	}

}
