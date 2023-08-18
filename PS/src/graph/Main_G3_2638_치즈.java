package graph;

import java.util.*;
import java.io.*;

public class Main_G3_2638_치즈 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited, external;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Queue<CurPos> cheese;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N][M];
		cheese = new ArrayDeque<>();
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)	{
					cheese.offer(new CurPos(i, j));
				}
			}
		}

		external = new boolean[N][M];
		
		int time = 0;
		while(!cheese.isEmpty()) {
			
			visited = new boolean[N][M];
			markExternal();	//외부 공기를 표시한다.
			
			melt();	//외부공기와 맞닿은 치즈를 없앤다.
			
			time++;
		}
		
		System.out.print(time);
	}
	
	private static void markExternal() {
		Queue<CurPos> queue = new ArrayDeque<>();
		queue.offer(new CurPos(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			CurPos cur = queue.poll();
			
			external[cur.r][cur.c] = true; 
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M
						|| visited[nr][nc] || map[nr][nc] == 1) continue;
				
				queue.offer(new CurPos(nr, nc));
				visited[nr][nc] = true;
			}
		}
	}
	
	private static void melt() {
		int size = cheese.size();
		
		for(int q = 0; q<size; q++) {
			CurPos cur = cheese.poll();
			
			int nr, nc;
			int count = 0;
			for(int i = 0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(external[nr][nc]) count++;
			}
			
			if(count < 2) {
				cheese.offer(cur);
			} else {
				map[cur.r][cur.c] = 0;
			}
		}
	}
}

class CurPos {
	int r, c;
	CurPos(int r, int c){
		this.r = r;
		this.c = c;
	}
}
