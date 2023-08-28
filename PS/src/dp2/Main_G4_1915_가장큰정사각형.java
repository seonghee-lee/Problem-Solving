package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_1915_가장큰정사각형 {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N+1][M+1];
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			String[] inputs = br.readLine().split("");
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(inputs[j-1]);
			}
		}
		
		int[][] dp = new int[N+1][M+1];
		int max = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				if(map[i][j] == 0) dp[i][j] = 0;
				else {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
					if(dp[i][j] > max) max = dp[i][j];
				}
			}
		}
		
		System.out.println(max * max);
	}
}
