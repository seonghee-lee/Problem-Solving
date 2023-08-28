package graph;

import java.util.*;
import java.io.*;

public class Main_G3_2146_다리만들기 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//섬을 넘버링한다.
		visited = new boolean[N][N];
		int num = 2;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					numbering(i, j, num++);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] > 0) continue;
				int island = -1;
				int nr = 0; int nc = 0;
				for(int d = 0; d<4; d++) {
					nr = i + dr[d];
					nc = j + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 0) continue;
					island = map[nr][nc];
					break;
				}
				if(island >= 2) {
					visited = new boolean[N][N];
					int cur = build(i, j, island);
					if(cur != -1 && cur < min) min = cur;
				}
			}
		}
		System.out.println(min);
	}
	
	private static int build(int r, int c, int island) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c, 1});
		visited[r][c] = true;
		int dist = -1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(map[cur[0]][cur[1]] >= 2 && map[cur[0]][cur[1]] != island) {
				dist = cur[2] - 1;
				return dist;
			}
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N
						|| visited[nr][nc] || map[nr][nc] == island) continue;
				
				queue.offer(new int[] {nr, nc, cur[2] + 1});
				visited[nr][nc] = true;
			}
		}
		
		return dist;
	}
	
	private static void numbering(int r, int c, int n) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			map[cur[0]][cur[1]] = n;
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N
						|| visited[nr][nc] || map[nr][nc] == 0) continue;
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
}
