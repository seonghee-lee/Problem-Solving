package dp;

import java.util.*;
import java.io.*;

// 맞 왜 틀........
public class Main_S1_22871_징검다리건너기large {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		String[] inputs = br.readLine().split(" ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(inputs[i-1]);
		}
		
		long[] dp = new long[N+1];	//dp[i]: i번 돌로 건너왔을 때 쓴 최소 힘
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[1] = 0;
		
		for(int i = 2; i<=N; i++) {	//i: 현재 돌
			for(int j = 1; j<i; j++) {	//j: i에 오기 직전 돌
				long cur = Math.max(dp[j], (i-j) * (1 + Math.abs(arr[i] - arr[j])));
				dp[i] = Math.min(dp[i], cur);
			}
		}
		System.out.print(dp[N]);
	}
}
