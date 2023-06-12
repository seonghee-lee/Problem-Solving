package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//DFS
public class Main_S1_2667_단지번호붙이기DFS {
	static int N, total, count;
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
					count = 0;
					dfs(i, j);
					list.add(count);
				}
			}
		}

		Collections.sort(list);
		System.out.println(total);
		for(int l : list) {
			System.out.println(l);
		}
	}
	
	private static void dfs(int r, int c) {
		visited[r][c] = true;
		count++;
		
		int nr, nc;
		for(int i = 0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N
					|| visited[nr][nc] || map[nr][nc] != 1) continue;
			dfs(nr, nc);
			
		}
		
	}
	
}
