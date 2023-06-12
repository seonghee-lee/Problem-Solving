package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로탐색이랑 똑같은 문제인데 왜 깨닫지를 못하니 ㅜㅜ
 * 상태를 갱신하면서 날짜를 세는 문제는: bfs + 날짜(시간, 소요되는거리) 갱신을 map에다가 할 수 있는지 꼭 생각해보기!!!!!
 */
public class Main_G5_7576_토마토 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		int ok = 0;
		int empty = 0;
		int total = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					ok++;
					queue.offer(new int[] { i, j });
					visited[i][j] = true;
				} else if (map[i][j] == -1)
					empty++;
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
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] > max) max = map[i][j];
			}
		}
		return max;
	}

	private static boolean remain() {
		boolean remain = false;
		top:
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 0) {
					remain = true;
					break top;
				}
			}
		}
		return remain;
	}
	
	private static void bfs() {

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == -1)
					continue;

				map[nr][nc] = map[cur[0]][cur[1]] + 1;
				queue.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
			}
		}
	}
}
