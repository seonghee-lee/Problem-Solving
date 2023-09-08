package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_21923_곡예비행 {
	static int[] dr = {1, 0};
	static int[] dc1 = {0, -1};
	static int[] dc2 = {0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		int[][] map = new int[N+1][M+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] up = new long[N+1][M+1];
		long[][] down = new long[N+1][M+1];
		
		up[N][1] = map[N][1];
		down[N][M] = map[N][M];
		
		for(int i = N; i > 0; i--) {
			for(int j = 1; j<=M; j++) {
				if(i == N && j == 1) continue;
				long max = Long.MIN_VALUE;
				int nr, nc;
				for(int d = 0; d<2; d++) {
					nr = i + dr[d];
					nc = j + dc1[d];
					if(nr <= 0 || nr > N || nc <= 0 || nc > M) continue;
					max = Math.max(max, up[nr][nc]);
				}
				up[i][j] = map[i][j] + max;
			}
		}
		
		for(int i = N; i>0; i--) {
			for(int j = M; j>0; j--) {
				if(i == N && j == M) continue;
				long max = Long.MIN_VALUE;
				int nr, nc;
				for(int d = 0; d<2; d++) {
					nr = i + dr[d];
					nc = j + dc2[d];
					if(nr <= 0 || nr > N || nc <= 0 || nc > M) continue;
					max = Math.max(max, down[nr][nc]);
				}
				down[i][j] = map[i][j] + max;
			}
		}
		
		long sumMax = Long.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			for(int j =1; j<=M; j++) {
				up[i][j] += down[i][j];
				if(up[i][j] > sumMax) sumMax = up[i][j];
			}
		}
		
		System.out.println(sumMax);
	}

}
