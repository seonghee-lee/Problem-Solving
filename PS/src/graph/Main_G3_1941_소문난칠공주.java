package graph;

import java.util.*;
import java.io.*;

public class Main_G3_1941_소문난칠공주 {
	static char[][] map = new char[5][5];
	static boolean[] selected = new boolean[25];
	static int ans;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] adjMap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
	
		comb(0, 0);	//25명 중 7명을 뽑는다.
		
		System.out.println(ans);
	}
	
	private static int bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[5][5];
		
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		int dist = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			dist++;
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				
				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc] || adjMap[nr][nc] != 1) continue;

				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
		return dist;
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == 7) {
			int sCount = 0;
			adjMap = new int[5][5];
			int r = -1; int c = -1;
			
			//이다솜파가 4명 이상인지 확인한다.
			for(int i = 0; i<25; i++) {
				if(selected[i]) {
					r = i / 5;
					c = i % 5;
					adjMap[r][c] = 1;
					if(map[r][c] == 'S') sCount++;
				}
			}
			
			if(sCount >= 4) {
				//학생들끼리 모두 인접한지 확인한다. -> 어케함?;;;;;
				int dist = bfs(r, c);
				
				if(dist == 7) ans++;
			}
			
			return;
		}
		
		for(int i = start; i<25; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			comb(cnt+1, i+1);
			selected[i] = false;
		}
	}
}
