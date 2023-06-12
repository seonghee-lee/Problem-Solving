package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

//BFS
public class Main_S1_2667_단지번호붙이기BFS {
	static int N, total;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					total++;
					bfs(i, j);
				}
			}
		}

		Collections.sort(list);
		System.out.println(total);
		for(int l : list) {
			System.out.println(l);
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		int aptCount = 1;
		
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N 
						|| visited[nr][nc] || map[nr][nc] != 1) continue;
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				aptCount++;
			}
		}
		list.add(aptCount);
	}
}
