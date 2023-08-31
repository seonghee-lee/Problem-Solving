package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_2758_로또 {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		long[][] dp = new long[11][2001];	//dp[i][j]: i번째 자리에서 j를 선택하는 경우의 수
		
		for(int i = 1; i<=2000; i++) dp[1][i] = 1;
		
		int startJ = 1;
		
		for(int i = 2; i<=10; i++) {
			for(int j = startJ; j<=2000; j++) {
				int prev = j / 2;
				for(int k = startJ / 2; k<=prev; k++) {
					dp[i][j] += dp[i-1][k];
				}
			}
			startJ *= 2;
		}
		
		for(int t = 1; t<=T; t++) {
			String[] inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			M = Integer.parseInt(inputs[1]);
			
			long sum = 0;
			for(int i = 1; i<=M; i++) sum += dp[N][i];
			
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb);
	}
}
