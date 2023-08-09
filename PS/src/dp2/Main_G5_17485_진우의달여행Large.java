package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_17485_진우의달여행Large {
	static int N, M;
	static int[][] map;
	static int[] dr = {0, -1, -1, -1};	//이전 방향을 나타낸다.
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N+1][M+1];
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N+1][M+1][4];	//dp[i][j][k] : map[i][j]까지 도달했을 때 최소 연료 비용, k는 이전 방향: 1,2,3
		
		for(int i = 0; i<=N; i++) {
			for(int j = 0; j<=M; j++) {
				for(int k = 0; k<4; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int j = 1; j<=M; j++) {
			dp[1][j][1] = map[1][j];
			dp[1][j][2] = map[1][j];
			dp[1][j][3] = map[1][j];
		}
		
		for(int i = 2; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				int nr, nc;
				int min = Integer.MAX_VALUE;
				for(int d = 1; d<=3; d++) {
					//이전 좌표
					nr = i + dr[d];
					nc = j + dc[d];
					
					if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
					
					if(d == 1) min = Math.min(dp[nr][nc][2], dp[nr][nc][3]);
					else if(d == 2) min = Math.min(dp[nr][nc][1], dp[nr][nc][3]);
					else min = Math.min(dp[nr][nc][1], dp[nr][nc][2]);
					
					dp[i][j][d] = map[i][j] + min;
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for(int j = 1; j<=M; j++) {
			for(int k = 1; k<=3; k++) {
				if(dp[N][j][k] < ans) {
					ans = dp[N][j][k];
				}
			}
		}
		
		System.out.print(ans);
	}

}

