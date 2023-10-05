package graph;

import java.util.*;
import java.io.*;

public class Main_G5_22352_항체인식 {
	static int N, M;
	static int[][] before, after, result;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		before = new int[N][M];
		after = new int[N][M];
		result = new int[N][M];
		visited = new boolean[N][M];
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				before[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				after[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int change = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(!visited[i][j]) {
					if(before[i][j] == after[i][j]) go(i, j, before[i][j]);
					else {
						change++;
						go(i, j, after[i][j]);
					}
				}
			}
		}
		
		boolean flag = true;
		top:
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(after[i][j] != result[i][j]) {
					flag = false;
					break top;
				}
			}
		}
		
		if(flag && change <= 1) System.out.println("YES");
		else System.out.println("NO");
	}
	
	private static void go(int r, int c, int num) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curR = cur[0];
			int curC = cur[1];
			result[curR][curC] = num;
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = curR + dr[i];
				nc = curC + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M 
						|| visited[nr][nc] || before[curR][curC] != before[nr][nc]) continue;
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
	}
}
