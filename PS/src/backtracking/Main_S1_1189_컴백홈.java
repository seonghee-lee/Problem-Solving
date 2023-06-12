package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 지점 방문처리!!!!!!!!!!!!!!!!
 */

public class Main_S1_1189_컴백홈 {
	static int R, C, K, count;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i = 0; i<R; i++) {
			String[] str = br.readLine().split("");
			for(int j = 0; j<C; j++) {
				map[i][j] = str[j].charAt(0);
			}
		}
		
		visited[R-1][0] = true;
		go(0, R-1, 0);
		System.out.println(count);
	}
	private static void go(int cnt, int r, int c) {
		if(r == 0 && c == C-1) {
			if(cnt == K - 1) {
				count++;
			}
			return;
		}
		
		int nr, nc;
		for(int i = 0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == 'T') continue;
			visited[nr][nc] = true;
			go(cnt+1, nr, nc);
			visited[nr][nc] = false;
		}
	}
}
