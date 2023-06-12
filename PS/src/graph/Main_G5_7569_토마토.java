package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_G5_7569_토마토 {
	static int N, M, H;
	static int[][] map[];
	static boolean[][] visited[];
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = {0, 0, 0, 0, -1, 1};
	static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[N][M][H];
		visited = new boolean[N][M][H];

		int ok = 0;
		int empty = 0;
		int total = 0;

		for(int k = 0; k<H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						ok++;
						queue.offer(new int[] { i, j, k});
						visited[i][j][k] = true;
					} else if (map[i][j][k] == -1)
						empty++;
				}
			}
		}

		total = N * M - empty;
		if (ok == total)
			System.out.println("0");
		else {
			bfs();
			// 0인 칸이 남아있을 경우
			if (remain()) {
				System.out.println("-1");
			} else {
				System.out.println(getDay() - 1);
			}
		}
	}
	
	private static int getDay() {
		int max = 0;
		for(int k = 0; k<H; k++) {
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(map[i][j][k] > max) max = map[i][j][k];
				}
			}
		}
		return max;
	}

	private static boolean remain() {
		boolean remain = false;
		top:
		for(int k = 0; k<H; k++) {
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(map[i][j][k] == 0) {
						remain = true;
						break top;
					}
				}
			}
		}
		return remain;
	}
	
	private static void bfs() {

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			int nr, nc, nh;
			for (int i = 0; i < 6; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				nh = cur[2] + dh[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || nh < 0 || nh >= H || visited[nr][nc][nh] || map[nr][nc][nh] == -1)
					continue;

				map[nr][nc][nh] = map[cur[0]][cur[1]][cur[2]] + 1;
				queue.offer(new int[] { nr, nc, nh });
				visited[nr][nc][nh] = true;
			}
		}
	}
}
