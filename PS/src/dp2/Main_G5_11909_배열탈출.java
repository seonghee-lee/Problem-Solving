package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_11909_배열탈출 {
	static int[] dr = {-1, 0};
	static int[] dc = {0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] dp = new long[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				dp[i][j] = Long.MAX_VALUE;
			}
		}
		dp[1][1] = 0;
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				int nr, nc;
				
				for(int d = 0; d<2; d++) {
					nr = i + dr[d];
					nc = j + dc[d];
					
					if(nr > 0 && nr <= N && nc > 0 && nc <= N) {
						if(map[i][j] < map[nr][nc]) {
							dp[i][j] = Math.min(dp[i][j], dp[nr][nc]);
						} else {
							int diff = map[i][j] - map[nr][nc];
							dp[i][j] = Math.min(dp[i][j], dp[nr][nc] + diff + 1);
						}
					}
				}
			}
		}
		
		System.out.println(dp[N][N]);
	}

}
