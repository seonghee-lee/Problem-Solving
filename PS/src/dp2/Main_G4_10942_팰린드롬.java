package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_10942_팰린드롬 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N+1][N+1];	//dp[s][e]: arr[s] ~ arr[e]가 펠린드롬이면 1, 아니면 0
		
		//한 자리 수열은 펠린드롬이다.
		for(int i = 1; i<=N; i++) {
			dp[i][i] = 1;
		}
		
		//두자리 수열일 때 arr[s] == arr[e]면 펠린드롬이다.
		for(int s = 1; s<N; s++) {
			int e = s + 1;
			if(arr[s] == arr[e]) dp[s][e] = 1;
		}
		
		//세자리 이상일 때, arr[s] == arr[e] 이고 arr[s+1] ~ arr[e-1]이 펠린드롬이면 펠린드롬이다.
		//거꾸로 dp 배열을 채우면서 조건 비교한다.
		for(int s = N-2; s >=1; s--) {
			for(int e = N; e > s + 1; e--) {
				if(arr[s] != arr[e]) continue;
				if(dp[s+1][e-1] == 1) dp[s][e] = 1;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=M; i++) {
			String[] se = br.readLine().split(" ");
			int s = Integer.parseInt(se[0]);
			int e = Integer.parseInt(se[1]);
			
			sb.append(dp[s][e]).append("\n");
		}
		
		System.out.print(sb);
	}
}
