package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_18290_NM과K1 {
	static int N, M, K, max, visitCount;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = Integer.MIN_VALUE;
		go(0, 0, 0, 0);
		System.out.println(max);
		
	}
	
	private static void go(int cnt, int r, int c, int sum) {
		
		if(cnt == K) {
			max = Math.max(max, sum);
			return;
		}

		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(visited[i][j]) continue;
				if(!notAdjust(i, j)) continue;
				visited[i][j] = true;
				go(cnt+1, i, j, sum + map[i][j]);
				visited[i][j] = false;
			}
		}
	}
	
	private static boolean inRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
	
	private static boolean notAdjust(int r, int c) {
		for(int i = 0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!inRange(nr, nc)) continue;
			if(visited[nr][nc]) return false;
		}
		return true;
	}
}

