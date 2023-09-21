package backtracking;

import java.util.*;
import java.io.*;

public class Main_G3_9944_NxM보드완주하기 {
	static int N, M, min, dotCnt;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};	//상, 좌, 하, 우
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringTokenizer st;
		int caseNum = 0;
		
		while((input = br.readLine()) != null) {
			caseNum++;
			st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			for(int i = 0; i<N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			dotCnt = 0;
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(map[i][j] == '.') dotCnt++;
				}
			}
			
			min = Integer.MAX_VALUE;
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(map[i][j] == '*') continue;
					visited[i][j] = true;
					go(0, 1, i, j);
					visited[i][j] = false;
				}
			}
			
			if(min == Integer.MAX_VALUE) min = -1;
			System.out.println("Case "+caseNum+": "+min);
		}
	}
	
	private static void go(int level, int dist, int r, int c) {
		
		if(level > min) return;
		
		if(dist == dotCnt) {
			min = Math.min(min, level);
			return;
		}

		int nr, nc, move;
		for(int d = 0; d<4; d++) {
			move = 0;
			while(true) {
				nr = r + dr[d];
				nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == '*') break;
				
				move++;
				visited[nr][nc] = true;
				
				r = nr;
				c = nc;
			}
			
			if(move == 0) continue;	//방향을 바꾼다.
			
			go(level+1, dist + move, r, c);
			
			for(int i = 0; i<move; i++) {
				visited[r][c] = false;
				r = r - dr[d];
				c = c - dc[d];
			}
		}
	}
}
