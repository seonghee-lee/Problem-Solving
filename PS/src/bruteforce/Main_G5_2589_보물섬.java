package bruteforce;

import java.util.*;
import java.io.*;

public class Main_G5_2589_보물섬 {
	static int N, M, max;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new char[N][M];
		for(int i = 0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		max = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 'L') {
					int result = bfs(i, j);
					max = Math.max(max, result);
				}
			}
		}
		System.out.println(max);
	}
	
	private static int bfs(int r, int c) {
		Queue<Island> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		queue.offer(new Island(r, c));
		visited[r][c] = true;
		int dist = -1;
		
		while(!queue.isEmpty()) {
			dist++;
			for(int q = 0, size = queue.size(); q<size; q++) {
				Island cur = queue.poll();
				int nr, nc;
				for(int d = 0; d<4; d++) {
					nr = cur.r + dr[d];
					nc = cur.c + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 'W') continue;
					visited[nr][nc] = true;
					queue.offer(new Island(nr, nc));
				}
			}
		}
		return dist;
	}
}

class Island{
	int r, c;
	Island(int r, int c){
		this.r = r;
		this.c = c;
	}
}
