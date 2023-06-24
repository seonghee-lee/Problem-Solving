package implementation;

import java.util.*;
import java.io.*;

public class Main_G4_2573_빙산 {
	static int N, M, total;
	static int[][] map, memo;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Queue<Ice> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N][M];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					queue.offer(new Ice(i, j, map[i][j]));
				}
			}
		}
		
		int year = 0;
		boolean isSeparate = false;
		while(true) {
			if(queue.isEmpty()) break;
			go();
			melt();
			count();
			if(check()) {
				isSeparate = true;
				if(year > 0) year++;
				break;
			}
			year++;
		}
		
		if(!isSeparate) System.out.println(0);
		else System.out.println(year);
	}
	
	private static void go() {
		memo = new int[N][M];
		int size = queue.size();
		while(size-- > 0) {
			Ice cur = queue.poll();
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] > 0) continue;
				memo[cur.r][cur.c]++;
			}
			
			if(map[cur.r][cur.c] - memo[cur.r][cur.c] > 0) {
				queue.offer(new Ice(cur.r, cur.c, map[cur.r][cur.c] - memo[cur.r][cur.c]));
			}
		}
	}
	
	private static void melt() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				map[i][j] -= memo[i][j];
				if(map[i][j] < 0) map[i][j] = 0;
			}
		}
	}
	
	private static void count() {
		Queue<Ice> searchQueue = new ArrayDeque<>();
		visited = new boolean[N][M];
		
		Ice start = queue.peek();
		if(start == null) return;
		
		searchQueue.offer(new Ice(start.r, start.c, start.height));
		visited[start.r][start.c] = true;
		
		while(!searchQueue.isEmpty()) {
			Ice cur = searchQueue.poll();
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >=M || visited[nr][nc]) continue;
				if(map[nr][nc] > 0) {
					searchQueue.offer(new Ice(nr, nc, 0));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	private static boolean check() {
		int size = queue.size();
		while(size-- > 0) {
			Ice cur = queue.poll();
			if(!visited[cur.r][cur.c]) {
				return true;
			}
			queue.offer(cur);
		}
		return false;
	}
}

class Ice{
	int r, c, height;
	Ice(int r, int c, int height){
		this.r = r;
		this.c = c;
		this.height = height;
	}
}
