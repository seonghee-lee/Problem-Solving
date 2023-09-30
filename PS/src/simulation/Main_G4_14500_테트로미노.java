package simulation;

import java.util.*;
import java.io.*;

public class Main_G4_14500_테트로미노 {
	static int N, M, max;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean[][] visited;	//이거를 인자로 넘겨줬을 때 시간초과가 났었음. 웬만하면 밖으로 빼는걸로.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N][M];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
				checkT(i, j);
			}
		}
		System.out.println(max);
	}
	
	private static void dfs(int r, int c, int dist, int sum) {
		if(dist == 4) {
			if(sum > max) max = sum;
			return;
		}
		for(int i = 0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs(nr, nc, dist+1, sum + map[nr][nc]);
			visited[nr][nc] = false;
		}
	}
	
	private static void checkT(int r, int c) {
		int sum = 0;
		if(r+1 < N && c+2 < M) {
			sum = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1];
			if(sum > max) max = sum;
		}
		if(r+2 < N && c+1 < M) {
			sum = map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c+1];
			if(sum > max) max = sum;
		}
		if(r-1 >= 0 && c+2 < M) {
			sum = map[r][c] + map[r][c+1] + map[r][c+2] + map[r-1][c+1];
			if(sum > max) max = sum;
		}
		if(r+2 < N && c-1 >= 0) {
			sum = map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c-1];
			if(sum > max) max = sum;
		}
	}
}
