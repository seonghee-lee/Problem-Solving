package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 그래프 탐색방법은 모든 점을 탐색하는 것이다.
 * 최단거리 값을 구해야 할 때는 MAP에 값을 새겨가면서 하면 된다!
 *
 */

public class Main_S1_2178_미로탐색 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i<N; i++) {
			String[] str = br.readLine().split("");
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		bfs(0, 0);
		System.out.println(map[N-1][M-1]);
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M 
						|| visited[nr][nc] || map[nr][nc] == 0) {
					continue;
				}
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				map[nr][nc] = map[cur[0]][cur[1]] + 1;
			}
		}
	}

}
